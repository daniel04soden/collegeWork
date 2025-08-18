#include <stdio.h>
struct item {
  char name[20];
  int quantity;
  int cost;
};

struct item *findMostVal(struct item *inventory, int size) {
  int mostExpensive = 0;
  struct item res;
  struct item *itemPtr = &res;
  for (int i = 0; i < size; i++) {
    int current_cost = inventory[i].cost;
    if (current_cost > mostExpensive) {
      mostExpensive = current_cost;
      *itemPtr = inventory[i];
    }
  }
  return itemPtr;
}

int main(int argc, char *argv[]) {
  struct item item1 = {.name = "Sword", .cost = 12, .quantity = 1000};
  struct item item2 = {.name = "Stick", .cost = 20, .quantity = 1};
  struct item item3 = {.name = "Mac", .cost = 3, .quantity = 1};

  struct item inventory[3];
  inventory[0] = item1;
  inventory[1] = item2;
  inventory[2] = item3;
  struct item *res = findMostVal(inventory, 3);
  printf("%d", res->cost);
  return 0;
}
