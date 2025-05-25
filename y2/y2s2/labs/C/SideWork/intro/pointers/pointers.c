// Program for learning basics of pointers
#include <stdio.h>
int main() {
  int num = 32;
  int *ptr = &num;

  printf("%d\n", *ptr);
  printf("%p", &num);
  return 0;
}
