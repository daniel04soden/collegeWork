#include <stdio.h> 
#include <stdbool.h>


int main() 
{ 
    char temp_array[30]; // a temporary array that is long enough to hold user input 
    char user_input;  // variable to store each character the user enters 
    int i = 0;

    while (true){ 
        user_input = (char) getchar(); 
				bool isAlpha = (user_input >= 'a' && user_input <= 'z') || (user_input >= 'A' && user_input <= 'Z');
				bool isNumeric = (user_input >= '0' && user_input <='9');
				if (user_input == '\n') break;   
				if ((isAlpha && isNumeric)){
					temp_array[i] = user_input; 
					i++; 
				}else{
					continue;	
				}
    } 
    // the value of i represents the length of the user input 
    // to create a reflected string we need twice the length 
    char reflected_array[i*2 + 1]; // plus 1 to accommodate the \0 at the end of the string 
    for (int j=0; j<i; j++){ 
        reflected_array[j] = temp_array[j]; 
        reflected_array[i*2-1-j] = temp_array[j]; 
    } 
    reflected_array[i*2] = '\0'; // need to add NULL for this to be a valid string 
    printf("The reflected string is: %s\n", reflected_array);
    return 0; 
} 
