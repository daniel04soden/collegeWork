#include <stdio.h>


int main(void){
  char buff[50];
  char c;
  int i = 0;

  while ((c=getchar())!='\n') {
      buff[i] = c;
      i++;
  }
  for (int j = i; j>=0; j--) {
    printf("%c",buff[j]); 
  }
  

  return 0;
}
