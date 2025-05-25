#include <string.h>
#include <stdlib.h>
#include "hungry_monkey.h"
#include "config_seccomp.h"

#undef srand

const char chars[] = {0, '1', '2'};

struct prng_s{
    unsigned char *numbers;
    int nxt_number;
    int size;
};

/**
 * Gets the next number from the PRNG
 * If the PRNG reaches the end, wrapped is set to 1 and 
 * we start the chain again.
 */
static unsigned char get_nxt_random(struct prng_s *prng, int *wrapped){
    unsigned char num = 0;
    *wrapped = 0;
    if (prng->nxt_number == prng->size){
        prng->nxt_number = 0;
        *wrapped = 1;
    }
    num = prng->numbers[prng->nxt_number];
    prng->nxt_number++;
    return num;
}


/**
 * Move the rows of the field down.
 * Regenerate the top row.
 * On the bottom row see if the monkey gets any treat.
 *
 * Returns:
 * 1 if the monkey gets a treat
 * 0 otherwise.
 */
static int update_field(int field[][FIELD_WIDTH], int monkey_col, struct prng_s *prng){
    int i,j;
    int dice;
    int ret = 0;

    // Start by shifting the rows down
    for (i=FIELD_HEIGHT-1;i>=1;i--)
        for (j=0;j<FIELD_WIDTH;j++)
            field[i][j] = field[i-1][j];

    // Update the first row
    for (j=0;j<FIELD_WIDTH;j++){
        int wrapped = 0;
        // Generate a new treat with probability P_NEW_TREAT
        // Roll the dice
        dice = get_nxt_random(prng, &wrapped);
        if (wrapped){
            fprintf(stderr, "Wrapped\n");
        }
        if (dice < P_NEW_TREAT) field[0][j] = TREAT_VAL;
        else field[0][j] = 0;
    }

    // Does the monkey get a treat?
    if (field[FIELD_HEIGHT-1][monkey_col] != 0){
        ret = 1;
    }

    // Show the monkey
    field[FIELD_HEIGHT-1][monkey_col] = MONKEY_VAL;
    return ret;
}

void main(){
    int monkey_col;
    void *monkey_state = NULL;
    struct monkey_action nxt_action;
    char position[20] = "";
    int step;
    int treats_eaten;

    int *dummies_score[4];
    int *other_dummies_score[4];

    for (int i=0;i<4;i++){
        dummies_score[i] = malloc(sizeof(int)*FIELD_HEIGHT*FIELD_WIDTH);
        *dummies_score[i] = i%2;
    }
    for (int i=0;i<4;i++){
        other_dummies_score[i] = malloc(sizeof(int)*FIELD_HEIGHT*FIELD_WIDTH);
        *other_dummies_score[i] = i%2;
    }
    int *test_run = dummies_score[2];
    int *score_sum = other_dummies_score[2];
    int (*user_field)[FIELD_WIDTH] = malloc(sizeof(int)*FIELD_HEIGHT*FIELD_WIDTH);

    int num_steps = 100+FIELD_HEIGHT;
    // hold random numbers < 100
    struct prng_s prng;
    prng.size = num_steps*FIELD_WIDTH;
    prng.numbers = malloc(sizeof(unsigned char)*prng.size);

    int *dummies[4];
    int *other_dummies[4];

    for (int i=0;i<4;i++){
        dummies[i] = malloc(sizeof(int)*FIELD_HEIGHT*FIELD_WIDTH);
        *dummies[i] = i%2;
    }
    for (int i=0;i<4;i++){
        other_dummies[i] = malloc(sizeof(int)*FIELD_HEIGHT*FIELD_WIDTH);
        *other_dummies[i] = i%2;
    }
    int (*field)[FIELD_WIDTH] = (int(*)[FIELD_WIDTH])other_dummies[2];

    configure_seccomp();

    while (*test_run < 100){
        // Reset state
        treats_eaten = 0;
        step = 0;
        monkey_col = FIELD_WIDTH >> 1;
        monkey_state = NULL;

        // Single run. Initialise PRNG
        srand(1234+*test_run);
        prng.nxt_number = 0;
        for (int i=0;i<prng.size;i++){
            prng.numbers[i] = random()%100;
        }

        // Initially the field is empty
        memset(field, 0, sizeof(int)*FIELD_WIDTH*FIELD_HEIGHT);

        while (step < 100+FIELD_HEIGHT){
            treats_eaten += update_field(field, monkey_col, &prng);
            // Give the user a copy of the field
            memcpy(user_field, field, sizeof(int)*FIELD_WIDTH*FIELD_HEIGHT);
            nxt_action = move_monkey(user_field, monkey_state);
            // Check the canary
            if (*dummies[2]){
                printf("Avg score: -1000000\n");
                return;
            }
            // Clear current monkey position
            field[FIELD_HEIGHT-1][monkey_col] = 0;

            monkey_state = nxt_action.state;
            if (nxt_action.move > 0){
                monkey_col ++;
            }else if (nxt_action.move < 0){
                monkey_col --;
            }
            if (monkey_col < 0) monkey_col = 0;
            if (monkey_col >= FIELD_WIDTH) monkey_col = FIELD_WIDTH-1;

            step++;
        }

        // Add score to total
        (*score_sum) += treats_eaten;

        (*test_run)++;
    }

    printf("Avg score: %0.2f\n", ((float)(*score_sum))/100);
    free(prng.numbers);
    for (int i=0;i<4;i++){
        free(other_dummies[i]);
        free(other_dummies_score[i]);
        free(dummies[i]);
        free(dummies_score[i]);
    }
    free(user_field);
}
