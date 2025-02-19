#include <stdio.h>

int main(){
	char input;
	int i = 0;
	int occurArrVowels[] = {0,0,0,0,0}; // a,e,i,o,u
	int vowels[] = {}; // a,e,i,o,u
	while ((input = getchar())!= '\n') {
		if ((input >= 'A' ) && (input<= 'Z')) {
			input = input + 'a' - 'A';
		}	
		switch (input) {
			case 'a':	

			case 'e':	
			case 'i':	
			case 'o':	
			case 'u':	
			break;
		}
		i++;
	}
	printf("%d\n",i);
	return 0;
}
