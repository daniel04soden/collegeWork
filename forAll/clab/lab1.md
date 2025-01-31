Lab 2

This lab is graded, you need to attend in person to submit your solution and have it graded.

    the grading will be done in the lab; if you leave the lab without having your work graded it will not be counted
    you can have your work graded whenever you are ready, however grading will start 30 minutes before the end of the lab to ensure everyone is evaluated
    as part of the grading, a live coding session will be done on a machine that is not connected to the Internet.

Exercise 1 (Practice)

Take a look at the following code and make sure you understand how it works. You can use this code to learn more about processing ASCII values in C and practising your coding skills. 

Get a 3 digit pin and encode it as alphabetic characters by adding a key (offset) to each character:

``` c
#include <stdio.h>

void main(){

    char input = 0; // Stores input chars as they are read
    char encoded = 0; // Stores encoded chars

    int key = 5; // The key (offset) added to each char
    int i=0;     // Used for the while loop

    while (i<3){ // Read 3 characters
        input = getchar(); // Read a character
        
        // Only accept lowercase. If other, ignore the character
        if (input < 'a' || input > 'z') continue;

        encoded = input + key; // Encode by adding key
        if (encoded > 'z'){ // If beyond ‘z’ bring back to start
            // (encoded - ‘z’) -> number of characters beyond ‘z’
            // Add this to ‘a’
            // -1 is because ‘z’ + 1 should become ‘a’, not ‘b’
            encoded = 'a' + encoded - 'z' - 1;
        }

        printf("%c", encoded);
        i++; // Processed one character successfully
    }

    printf("\n");
}

```
Example output:

    If input is “abc” output should be “fgh”
    If input is “xyz” output should be “cde”

Modify the code to

    Process only uppercase letters
    Perform decoding: the key should be subtracted from the input, and characters going below ‘A’ should be rolled back to ‘Z’ so that the first character below ‘A’ becomes ‘Z’. Therefore, for an input of “CDE” you should get output “XYZ”.

 
Exercise 2

Implement a generic encoder/decoder:

    Reads the direction as a single character, ‘+’ means encoding (adding key to input), ‘-’ means decoding (subtracting key from input).
        The user will be asked to provide the direction.
    Reads the user input up to the newline character.
    The key is preset (as was done in Task 1).
    Supports both uppercase and lowercase letters. The letters should all be converted to lowercase before being encoded/decoded, so you must detect the case of the input before proceeding.
    As the input is read it will be encoded/decoded, depending on the provided direction.
        Make sure you don’t process the newline character!
        The processed character will be printed.

Encoding/decoding should function as explained.

 
Exercise 3 (Debug - group work)

There is a problem with the below code, can you work together to fix it?

``` c
#include <stdio.h>

/*
* Encrypt a word through letter repetition.
* You are given a base word and a hidden word.
* In the base word each letter will be repeated a number of times
* equal to the offset of the corresponding hidden letter in the alphabet + 1.
* For example, to represent an 'a' you repeat the underlying letter 'a'-'a'+1,
* so once.
*
* Example run:
* base string: howareyou
* input: abcde
* output: hhooowwwwaaaaarrrrrr
*/
void main(){

   char base_string[] = "howareyou";
   char input = 0;
   int offset = 0;
   char idx = 0;
    int i;

   while (1){
       input = getchar();
       offset = input - 'a';
       for (i=0;i<offset;i++){
           printf("%c", base_string[idx]);
       }
   }
   printf("\n");
}
``` 
Exercise 4 (Live demonstration)

As part of this lab each student needs to demonstrate the following skill: read lower case characters and convert to upper case.

This demo will be conducted on the teacher's machine, as part of the grading.
