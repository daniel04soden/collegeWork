#include <stdio.h>

int main(int argc, char *argv[]) { 
	// Main point here is that characters and integers are interchangeable.
	// We can utilize this fact to work with different characters and keep input in ranges.
	char c = 'c';
	int myInt = 99;
	// Using ascii for working with user input
	printf("%d\n",myInt); // Difference of lower to uppercase is 32
	printf("%c\n",myInt); // Difference of lower to uppercase is 32

	int i =0;

	for(;i<128;i++){
	printf("%d\t --> %c\n",i,i);
	}

	char d = 'd';
	d++;
	printf("\n%d\n%c\n",d,d);
	char myChar = 'C';

	if ((myChar>= 'A') && (myChar<='Z')){
		int offset = myChar - 'a';
		char myUpper = 'A' + offset;
		printf("\n%c\n%c\n",myChar,myUpper);
	}
	if ((myChar>= 'a') && (myChar<='z')){
		int offset = myChar - 'A';
		char myLower = 'a' + offset;
		printf("\n%c\n%c\n",myChar,myLower);
	}
	return 0; 

}

