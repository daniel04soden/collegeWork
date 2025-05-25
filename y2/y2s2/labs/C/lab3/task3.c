#include <stdio.h>

int main(){
	char input;
	int i = 0;
	while ((input = getchar())!= '\n') {
		if ((input >= 'A' ) && (input<= 'Z')) {
			input = input + 'a' - 'A';
		}	
		switch (input) {
			case 
			i++;
			break;
		}
	}
	printf("%d\n",i);
	return 0;
}
