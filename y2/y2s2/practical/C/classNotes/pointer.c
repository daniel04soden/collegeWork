#include <stdio.h>
#include <stdlib.h>
int main() {
  char *strings[10];

  int i = 0;

  for (int i = 0; i < 5; i++) {
    strings[i] = malloc(10);
    scanf("%s\n", strings[i]);
  }
  for (int j = 0; j < 5; j++) {
    printf("%s\n", strings[i]);
    free(strings[j]);
  }
  return 0;
}
