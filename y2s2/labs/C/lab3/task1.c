#include <stdio.h> 
#include <stdbool.h>


int main() 
{ 
    char temp_array[30]; // a temporary array that is long enough to hold user input 
    char userInput;  // variable to store each character the user enters 
    int i = 0;

    while (true){ 
        userInput = (char) getchar(); 
				bool isAlpha = (userInput >= 'a' && userInput <= 'z') || (userInput >= 'A' && userInput <= 'Z');
				bool isNumeric = (userInput >= '0' && userInput <='9');
				if (userInput == '\n') break;   
				if ((isAlpha || isNumeric)){
					if (i>=30 ) {
						printf("Max length reached chill out\n");
						break;	
					}
					temp_array[i] = userInput; 
					i++; 
				}else{
					continue;	
				}
    } 
    // the value of i represents the length of the user input 
    // to create a reflected string we need twice the length 
    char duplicatedArrayThree[i*3 + 1]; // plus 1 to accommodate the \0 at the end of the string 
    for (int j=0; j<i; j++){ 
        duplicatedArrayThree[j] = temp_array[j]; 
        duplicatedArrayThree[i*3-1-j] = temp_array[j]; 
    } 
    duplicatedArrayThree[i*3] = '\0'; // need to add NULL for this to be a valid string 
    printf("The reflected string is: %s\n", duplicatedArrayThree);
    return 0; 
} 
