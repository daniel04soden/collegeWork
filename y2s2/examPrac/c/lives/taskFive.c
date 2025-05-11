#include <stdio.h>
#include <stdlib.h>

struct item {
  char name[20];
  int cost;
  int quantity;
}

struct item *findMostValuable(struct item *inventory,int size){
  int maxValue = 0;
  struct item *res = NULL; 
 for (int i=0 ; i<size; i++) {
  int currentValue = inventory[i].cost*inventory[i].quantity;
  if (currentValue > maxValue) {
    maxValue = currentValue;
    res = &inventory[i];
  }
 } 
  return res;
}


int main(void){

}
