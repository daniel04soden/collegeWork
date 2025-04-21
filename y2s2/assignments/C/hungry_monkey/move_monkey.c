#include "hungry_monkey.h"
#include <stdint.h>
#include <stdlib.h>
#include <string.h>
#define MOVE_LEFT -1
#define MOVE_RIGHT 1
#define MOVE_FWD 0

struct monkey_action move_monkey(int field[][FIELD_WIDTH], void *monkey_state) {
  // Initialising state and actions
  struct monkey_action retval;
  int monkey_pos;
  if (monkey_state == NULL) {
    monkey_state = malloc(sizeof(int)); // TODO get state from this to index
    retval.state = monkey_state;
  }
    monkey_pos = (int)(uintptr_t)(monkey_state);
  
  
  
  return retval;
};

