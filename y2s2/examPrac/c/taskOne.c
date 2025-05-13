#include <stdio.h>

int main(void){
  char c;
  while ((c=getchar())!='\n') {
    if (c >='a' && c <= 'z') {
      c = c - 'a' + 'A'; // Converts to uppercase
    } 
    printf("%c",c);
  }
}
