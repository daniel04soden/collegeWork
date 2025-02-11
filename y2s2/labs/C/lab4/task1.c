#include <stdio.h>

char increment(char *a){
  if (*a >= 'a' && *a <= 'z') {
      *a = *a - 'a' + 'A';
  }else if(*a >= 'A' && *a <= 'Z'){
    *a = *a + 'a' - 'A';
	}
  return *a;
}


int main(){
    char myA= 'a';
    char mya= 'A';
    char *myCharA = &myA;
    char *myChara = &mya;

	printf("%c\n",increment(myCharA));
	printf("%c\n",increment(myChara));
	return 0;
}
