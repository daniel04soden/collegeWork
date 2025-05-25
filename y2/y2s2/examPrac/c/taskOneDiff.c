#include <stdio.h>
int main(void){
  char c;
  while ((c=getchar())!='\n') {
    if (c >='A' && c <= 'Z') {
      c = c - 'A' + 'a';
      // convert is letter = letter - ogcase + newcase
    }
    printf("%c",c);
  }
}
