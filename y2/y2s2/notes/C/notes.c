// Notes in C lectures
// Epic author daniel soden
// I think this lecturer loves me
#define MYBOOL 0
#include <stdio.h>
int main(int argc, char*argv[]){
  struct testStruct{};
	char input[30];
	int num = 25;
	float the = 3.5;
	double samethe = 2.5;
	int myArr[10];
	char string[25] = "hello you";
	// while (myBool){
		// printf("%d",myBool);
	
	// }
	printf("gimme some input\n");
	printf("%s\n",string);
	scanf("%s",input);
	printf("%s\n",input);
	
	// printf("Hello", myBool, "therre",myBool2);
	// int printf(<formatstring>, list of arguments)
	if(MYBOOL){
		printf("This is true");
	}else{
		printf("This is false");
	}
  int j = 0;
  do{
    printf("testing a do while loop %d\n",j);
    j+=2;
  
  }while(j<10);
	return 0;
}
