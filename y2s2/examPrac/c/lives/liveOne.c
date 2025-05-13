#include <stdio.h>

int main(){
  char c;
  while ((c=getchar())!= '\n') {
    if (c >= 'a' && c<= 'z' ) {
      c = c + 'A' - 'a';
    } 
  printf("%c",c);
  }
  return 0;
}
