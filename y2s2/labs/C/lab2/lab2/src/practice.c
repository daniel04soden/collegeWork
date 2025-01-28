#include <stdio.h>

int main(){
char myChar;

while((myChar = getchar()) != EOF){
	if ((myChar>='a') && (myChar <= 'z')){
		myChar = myChar - 'a' + 'A';
	}

	printf("%c",myChar);


}
return 0;
}
