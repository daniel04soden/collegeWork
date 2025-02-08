#include <stdio.h>

int main(){
	char c;
	char vowels[] = {'a','e','i','o','u'};
    char temp_array[30];     
		char user_input;      
		int i = 0;

    while (1){ 
        user_input = (char) getchar(); 
        if (user_input == '\n') break;  // if we get to the end of the input stop 
        temp_array[i] = user_input; 
        i++; 
    } 

return 0;
}
