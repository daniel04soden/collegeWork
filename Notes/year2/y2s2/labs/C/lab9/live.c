/*
Live task

Define a struct to represent an inventory item with "name" (20 chars); "cost"
(int); "quantity" (int).

Write a function that returns a pointer to the most valuable item in the
inventory. This is the item with highest cost * quantity.

The function will take as parameters a list of items, and the size of the list
*/

#include <stdio.h>
struct item {
  char name[20];
  int cost;
  int quantity;
};


struct item *findMostVal(struct item *items, int listSize) {
  int mostExpensive = -1;
  struct item *expenSiveItem = NULL;
  for (int i = 0; i < listSize; i++) {
    int currPrice = (items[i].cost) * (items[i].quantity);
    printf("%d\n", currPrice);
    if (currPrice > mostExpensive) {
      mostExpensive = currPrice;
      expenSiveItem = &items[i];
    }
  }
  return expenSiveItem;
}

int main() {
  struct item item1 = {.name = "Sword", .cost = 12, .quantity = 1000};
  struct item item2 = {.name = "Stick", .cost = 20, .quantity = 1};
  struct item item3 = {.name = "Mac", .cost = 2000, .quantity = 1};

  struct item inventory[3];
  inventory[0] = item1;
  inventory[1] = item2;
  inventory[2] = item3;
  struct item *res = findMostVal(inventory, 3);
  printf("%s", res->name);
}
