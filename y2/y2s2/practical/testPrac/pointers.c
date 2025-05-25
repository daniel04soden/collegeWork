#include <stdio.h>
int main(void){
  int m = 100;
  int *x = &m;

  double helloThere = 23.33;
  double *woahItsHelloThere = &helloThere;
  
  char test = 'a';
  char *myChartwo = &test;

  char **whatIsThis = &myChartwo;

  printf("%d",*x);
  return 0;
}
