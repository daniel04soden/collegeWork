#include <stdio.h>
int main(){
  char c;
  char storage[50];
  int i =0;
  while ((c=getchar())!='\n') {
    storage[i] = c;
    i++;
  }

  for (int j = i-1; j >= 0 ; j--) {
    printf("%c",storage[j]);
  }
  
  return 0;
}
