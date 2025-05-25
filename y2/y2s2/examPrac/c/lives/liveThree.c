#include <stdio.h>
void order_numbers(int *a, int *b){
  if (*a > *b){
   *a = *a ^ *b; 
   *b = *a ^ *b; 
   *a = *a ^ *b; 
  }
}


int main(){
  int a = 1;
  int b = 2;
  printf("a %d\n",a);
  printf("b %d\n",b);
  order_numbers(&a,&b);
  printf("a %d\n",a);
  printf("b %d\n",b);
  order_numbers(&b,&a);
  printf("a %d\n",a);
  printf("b %d\n",b);



  return 0;
}
