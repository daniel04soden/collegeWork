#include "hungry_monkey.h"
#include <stdlib.h>
#define TINY_VAL -99999


struct monkey_action move_monkey(int field[][FIELD_WIDTH], void *monkey_state) {
    struct monkey_action retval;
    //default
    if (monkey_state == NULL) { // Allocate for state
        retval.move = MOVE_FWD;
        monkey_state = malloc(sizeof(int));
        *(int *)monkey_state = FIELD_WIDTH / 2; // Always starts in the middle
    }

    int monkey_col = *(int *)monkey_state; // easier way to treat state in calculations
    int first_col=monkey_col;
    int row = FIELD_HEIGHT - 1;
    int bestTreats = -1; // min for now
    int dp[FIELD_HEIGHT][FIELD_WIDTH];


    // First row of dp table - top of tree

    for (int i =0; i<FIELD_WIDTH; i++) {
        if (field[0][i] == TREAT_VAL) {
            dp[0][i] = 1;
        } else {
            dp[0][i] = 0;
        }
    }

    for (int i =0; i<FIELD_HEIGHT; i++) {
        for (int j=0 ; j<FIELD_WIDTH; j++) {
            // once i is greater than 0, above is real value, otherwise make it tiny
            int above = (i>0) ? dp[i-1][j]: TINY_VAL; 
            // once we don't hit any corners, left is down one and to the left from the top
            int l = (i > 0 && j > 0) ? dp[i - 1][j - 1] : TINY_VAL; 
            // once we don't hit any corners, right is down one and to the right from the top
            int r = (i > 0 && j < FIELD_WIDTH - 1) ? dp[i - 1][j + 1] : TINY_VAL; 
            int curr = field[i][j];
            int choice;
            if (above > l && above > r) {
                choice = above;
            } else if (l > r) {
                choice = l;
            } else {
                choice = r;
            }
            dp[i][j] = curr + choice;
        } 
    }

    int treats_up = (row > 0) ? dp[row - 1][first_col] : TINY_VAL;
    int treats_left = (row > 0 && first_col > 0) ? dp[row - 1][first_col - 1] : TINY_VAL;
    int treats_right = (row > 0 && first_col < FIELD_WIDTH - 1) ? dp[row - 1][first_col + 1] : TINY_VAL;

    if ((treats_left >= treats_up) && (treats_left >= treats_right)) {
        retval.move = MOVE_LEFT;
        bestTreats = treats_left;
    } else if ((treats_right >= treats_up) && (treats_right >= treats_left)) {
        retval.move = MOVE_RIGHT;
        bestTreats = treats_right;
    } else {
        retval.move = MOVE_FWD;
        bestTreats = treats_up;
    }
    int new_col = first_col + retval.move;
    if (new_col < 0) {
        new_col = 0;
    } else if (new_col >= FIELD_WIDTH) {
        new_col = FIELD_WIDTH - 1;
    }

    *(int *)monkey_state = new_col;
    retval.state = monkey_state;
    return retval;
}
