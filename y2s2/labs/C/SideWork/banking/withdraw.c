#include "users.c"
#include <stdio.h>
#include <time.h>

// Predefined structs

struct withdrawal {
  struct user withdrawingUser;
  double amountWithdrawn;
  int withdrawalId;
};

struct withdrawal* makeWithdrawal(struct user _withdrawingUser,
                                 double _amountWithdrawn) {
                                     struct withdrawal newWithdrawal;

                                     if (_withdrawingUser.todayTransAction >= 10000){
                                         printf("Max transactions achieved for the day, please come back tomorrow");
                                         return NULL;
                                     }else{


                                     }

                                     return newWithdrawal;
                                 }
