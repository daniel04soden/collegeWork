#include <stdio.h>
#include <string.h>

// Call stack

void fun1(char *s) {
  int x = strlen(s);
  x = x + 1;
  printf("%s %d\n", s, x);
  s[0] = 'G';
}

void fun2(int v) {
  int x;
  char string[10];
  v++;
  x = v;
  scanf("%s", string);
  fun1(string);
}

int main() {
  fun2(2);

  printf("\nDone\n");
  return 0;
}
