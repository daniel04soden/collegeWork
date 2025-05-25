#include <stdio.h>

struct item {
  int quantity;
  int cost;
  char name[20];
};


struct item *mostVal(struct item *inventory,int length){
  struct item *res = NULL;
  int maxValue = 0;
  for (int i =0; i<length; i++) {
    struct item *currentItem = &inventory[i];
    if ((currentItem->cost * currentItem->quantity) > maxValue) {
      maxValue = currentItem->cost * currentItem->quantity;
      res = currentItem;
    } 
  }
  if (res == NULL){
    return NULL;
  }
  return res;
}


int main(void){
struct item inventory[] = {{10, 5, "Apple"}, {2, 50, "Gold Bar"},
                             {5, 10, "Book"}, {0, 100, "Empty Box"}};
    struct item *mostValuable = mostVal(inventory, 4);

      if (mostValuable != NULL) {
          printf("Most valuable item:\n");
          printf("  Name: %s\n", mostValuable->name);
          printf("  Quantity: %d\n", mostValuable->quantity);
          printf("  Cost: %d\n", mostValuable->cost);
          printf("  Total Value: %d\n", mostValuable->quantity * mostValuable->cost);
        } else {
            printf("No items in inventory or no item has a positive value.\n");
          }
}
