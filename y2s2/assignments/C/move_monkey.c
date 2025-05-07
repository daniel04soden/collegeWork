#include "hungry_monkey.h"
#include <stdlib.h>

struct monkey_action move_monkey(int field[][FIELD_WIDTH], void *monkey_state) {
    struct monkey_action retval;

    if (monkey_state == NULL) { // Allocate for state
        monkey_state = malloc(sizeof(int));
        *(int *)monkey_state = FIELD_WIDTH / 2; // Always starts in the middle
    }

    int monkey_col = *(int *)monkey_state; // easier way to treat state in calculations

    int above = field[FIELD_HEIGHT - 2][monkey_col];
    // Checks if we're within the actual field width, then if true, grab field to left diag, otherwise leave as 0
    int left = (monkey_col > 0) ? field[FIELD_HEIGHT - 2][monkey_col - 1] : 0; // ternary to prevent out of bounds
    // Checks if we're within the actual field width, then if true, grab field to right diag, otherwise leave as 0
    int right = (monkey_col < FIELD_WIDTH - 1) ? field[FIELD_HEIGHT - 2][monkey_col + 1] : 0;

    if (above == TREAT_VAL) {
        retval.move = MOVE_FWD; // Treat above
    } else if (left == TREAT_VAL) {
        retval.move = MOVE_LEFT; // Treat to the left
    } else if (right == TREAT_VAL) {
        retval.move = MOVE_RIGHT; // Treat to the right
    } else {
        retval.move = MOVE_FWD; // Default action - if there's nonearound
    }

    *(int *)monkey_state = monkey_col + retval.move; // Update monkey state - convert back to void pointer
    retval.state = monkey_state;
    return retval;
}

