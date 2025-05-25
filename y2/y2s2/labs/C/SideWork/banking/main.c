// Author: Daniel Soden
// Purpose: Learning C in a project context

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "users.c"


// Constants
#define MAX_TRANSACTIONS = 10000.00; // A person can only make $10,000 worth of transactions a day

struct deposit {
  struct user depositUser;
  double amountDeposited;
  int depositId;
};

// Displaying menu contents

void displayMenu() {
  printf(
      "1. Create account\n2. Deposit Money\n3. Withdraw Money\n4. Exit\n"); // One
                                                                            // at
                                                                            // at
                                                                            // any
                                                                            // runtime
                                                                            // currently
}

int main(void) {
  bool running = true;
  while (running) {

    int choice;
    displayMenu();
    scanf("%d", &choice);

    switch (choice) {
    case 1:
      printf("\nAccount Creation");
      printf("\n---------------------------\n");


      // Taking in user details
      bool valid = false;
      while (!valid) {
          int length = 20;
          char *name = malloc(length * sizeof *name);
          printf("Enter your name:\n");
          scanf("%s", &*name);

          if (length <= 20){
          valid = true;
          printf("Hello %s",name);
          free(name);
          }
      }






      break;

    case 2:
      printf("\nDeposit Money");
      printf("\n---------------------------\n");
      break;

    case 3:

      printf("\nWithdraw Money");
      printf("\n---------------------------\n");
      break;

    case 4:
      running = false;
      break;

    default:
      printf("Enter a real option bro\n");
    }
  }

  return 0;
}
