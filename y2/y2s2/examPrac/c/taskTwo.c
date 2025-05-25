#include <stdio.h>
void orderNums(int *a, int *b){
  if (!(a<=b)) {
    *a = *a^*b;
    *b = *a^*b;
    *a = *a^*b;
  }
}

int main(void){
  int a = 2;
  int b = 1;
  orderNums(&a,&b);
  printf("a: %d",a);
  printf("b: %d",b);
}
