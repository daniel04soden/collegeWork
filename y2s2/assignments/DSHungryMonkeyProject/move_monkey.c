#include "hungry_monkey.h"
#include <stdio.h>
#include <stdlib.h>
#define TINY_VAL 0 // - small value for reference - could be lower value, helps matrix better represented



struct monkey_action move_monkey(int field[][FIELD_WIDTH], void *monkey_state) {
    struct monkey_action retval;  
    //default for first iteration
    if (monkey_state == NULL) { // Allocate for state
        retval.move = MOVE_FWD; // default move incase of failure 
        monkey_state = malloc(sizeof(int));  // allocating memory for the monkeys state
        *(int *)monkey_state = FIELD_WIDTH / 2; // Always starts in the middle
    }

    int monkey_col = *(int *)monkey_state; // column gotten from  casting it to an integer pointer and dereferencing it. 
    int first_col=monkey_col; // the first column for usage soon 
    int row = FIELD_HEIGHT - 1; // row above monkey 
    int dp[FIELD_HEIGHT][FIELD_WIDTH]; // Dynamic programming based matrix to fill


    // First row of dp table - top of tree
    // Fill up with 1 if actual treat or 0 for comparison

    for (int i =0; i<FIELD_WIDTH; i++) {
        if (field[0][i] == TREAT_VAL) {
            dp[0][i] = TREAT_VAL;
        } else {
            dp[0][i] = TINY_VAL;
        }
    }

    for (int i =0; i<FIELD_HEIGHT; i++) {
        for (int j=0 ; j<FIELD_WIDTH; j++) {
            // Once i is greater than 0, above is real value, otherwise make it tiny
            int above = (i>0) ? dp[i-1][j]: TINY_VAL; 
            // Once we don't hit any corners, left is down one and to the left from the top
            int l = (i > 0 && j > 0) ? dp[i - 1][j - 1] : TINY_VAL; 
            // Once we don't hit any corners, right is down one and to the right from the top
            int r = (i > 0 && j < FIELD_WIDTH - 1) ? dp[i - 1][j + 1] : TINY_VAL; 
            int curr = field[i][j];
            int choice;
            if (above > l && above > r) { // prioritise above, allows for monkey to get multiple in a row above it
                choice = above;
            } else if (l > r) {
                choice = l;
            } else {
                choice = r;
            }
            dp[i][j] = curr + choice; // current cell's value (curr) plus the maximum treats reachable from the cell above it
                                      // ie maximum treats collectible to reach each cell.
        } 
    }

    int treats_up = (row > 0) ? dp[row - 1][first_col] : TINY_VAL; // if still within bounds, treats over monkey simply becomes value above in dp matrix - different worth for matrix gen
    // if within bounds, treats left to monkey becomes value above and to left in dp matrix 
    int treats_left = (row > 0 && first_col > 0) ? dp[row - 1][first_col - 1] : TINY_VAL;    
    // if row is greater than 0 and so is the first col value, treats right of  monkey becomes value above and to right in dp matrix;
    int treats_right = (row > 0 && first_col < FIELD_WIDTH - 1) ? dp[row - 1][first_col + 1] : TINY_VAL;

    // Move decisions, depending on in dp matrix, whaat yields more for future moves - can be seen in  output
    // IE we decide on the best decision for the current mvoe to make corresponding to the monkeys pos,
    // and the treat "worth" the most in terms of future chances
    int left_decision = (treats_left >= treats_up) && (treats_left >= treats_right);
    int right_decision = (treats_right >= treats_up) && (treats_right >= treats_left);

    if (left_decision) {
        retval.move = MOVE_LEFT;
    } else if (right_decision) {
        retval.move = MOVE_RIGHT;
    } else {
        retval.move = MOVE_FWD;
    }

    int new_col = first_col + retval.move; // starting point + move

    // Make sure new_col in bounds - 30.87 without check
    if (new_col < 0) {
        new_col = 0;
    } else if (new_col >= FIELD_WIDTH) {
        new_col = FIELD_WIDTH - 1;
    }


    *(int *)monkey_state = new_col; // dereferencing monkey_state and assigning our new column to its new state
    retval.state = monkey_state; // Continuing state
    return retval;
}
