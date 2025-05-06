#include "hungry_monkey.h"
#include <stdlib.h>

struct monkey_action move_monkey(int field[][FIELD_WIDTH], void *monkey_state) {
  struct monkey_action retval;
  int final_move;
  int monkey_col;
  for (int j = 0; j < FIELD_WIDTH; j++) {
    if (field[FIELD_HEIGHT - 1][j] == MONKEY_VAL) {
      monkey_col = j;
      break;
    }
  }

  int above = field[FIELD_HEIGHT - 2][monkey_col];
  int left = field[FIELD_HEIGHT - 2][monkey_col - 1];  // Diag left
  int right = field[FIELD_HEIGHT - 2][monkey_col + 1]; // Diag right

  if (monkey_state == NULL) {
    monkey_state = malloc(sizeof(int));
  }

  return retval;
}
