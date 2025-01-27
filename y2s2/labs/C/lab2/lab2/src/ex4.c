#include <stdio.h>

int main() {
  char c;
  int valid = 0;

  while (valid) {
    printf("Enter a lowercase character:\n");
    if (scanf("%c", &c) >= 'a' && scanf("%c", &c) <= 'z') {
      c = c - 32;
      break;
    } else {
      ;
    }
  }

  printf("%c\n", c);
}
