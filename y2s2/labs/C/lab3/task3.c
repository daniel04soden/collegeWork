#include <stdio.h>

int main(){
	int sum = 0;
	char input = 0; 
	int i = 0;
	for(input=getchar(); (input != '\n') && (i < 10); i++){ 
		input -= 0;
		sum += input;
	}
	printf("Sum: %d\n", sum); 
	return 0; 

}
