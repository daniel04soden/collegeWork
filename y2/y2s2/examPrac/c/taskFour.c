#include <stdio.h>
#include <stdlib.h>

char *only_lowercase(const char *input){
  char *lowerCaseStr = malloc(sizeof(char*)*20);
  int i = 0; 
  int j = 0; 
    while (input[i]!='\0') {
    if (input[i] >='a' && input[i] <= 'z') {
      lowerCaseStr[j] = input[i];
      j++;
    }
    i++;
  }
  lowerCaseStr[j] = '\0';
  return lowerCaseStr;
}

int main(void){
  char *string = "HelloWorld!";
  char *lowerVer = only_lowercase(string);
  printf("%s",lowerVer);
  free(lowerVer);
}
