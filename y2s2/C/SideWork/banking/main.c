// Author: Daniel Soden
// Purpose: Learning C in a project context

#include <stdbool.h>
#include <stdio.h>

// Constants
#define MAX_TRANSACTIONS = 10000.00;

// Predefined structs

struct user {
  char name[20];
  int id;
  int age;
  double currentBal;
  double todayTransAction;
};

struct withdrawal {
  struct user withdrawingUser;
  double amountWithdrawn;
  int withdrawalId;
};

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
  int choice;
  displayMenu();
  scanf("%d", &choice);

  return 0;
}
