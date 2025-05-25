#include <stdio.h>
#include <stdlib.h>

char *onlyLowercase(const char *input){
  char *res = malloc(sizeof(char)*20);
  int i = 0; // Index for input
  int j = 0; // Index for result
  while (input[i] != '\0') {
    if (input[i] >= 'a' && input[i] <= 'z') {
      res[j] = input[i];
      j++;
    }
    i++;
  }
  res[j] = '\0'; // Null-terminate the result string
  return res;
}

int main(void){
  char *input = "HelloWorld!";
  char *lower = onlyLowercase(input);
  printf("%s",lower);
  return 0;
}
