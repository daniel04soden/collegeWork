#include <stdio.h>

int main(){
    int letter;
    printf("Enter a lower case letter\n");

    while ((letter = getchar()) !=EOF) {
        if (letter >= 'a' && letter <= 'z'){
            letter = letter - 'a' + 'A';
        }
        printf("%c",letter);
    }

}