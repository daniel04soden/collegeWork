#include <stdbool.h>
#include <stdio.h>
#include <string.h>

// Predefined structs

struct user {
  char name[20];
  int id;
  int age;
  double currentBal;
  double todayTransAction;
  double currentCash;
};

struct user createUser(char _name[], int _age, double _currentBal) {
  struct user newUser;
  strcpy(newUser.name, _name);
  newUser.age = _age;
  newUser.id = 1;
  newUser.currentBal = _currentBal;
  newUser.todayTransAction = 0;
  newUser.currentCash = 0;

  return newUser;
}

void listUserInfo(struct user givenUser) {

  printf("Name: %s\n", givenUser.name);
  printf("ID: %d\n", givenUser.id);
  printf("Age: %d\n", givenUser.age);
  printf("Age: %f\n", givenUser.currentBal);
  printf("Age: %f\n", givenUser.todayTransAction);
}
