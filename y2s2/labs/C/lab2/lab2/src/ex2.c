#include <stdio.h>
#define KEY 2

int main() {
  printf("Would you like to encode (+) or decode (-)\n");
  char choice;
  char userInput;
  char res;

  choice = getchar();
  getchar();

  if (choice == '+') {
    char encoded = 0;
    printf("Welcome to encoding program!\n");

    userInput = getchar();

    while (userInput != '\n') {
      if (userInput < 'A' || userInput > 'Z') {
        userInput += 32;
      }
      encoded = userInput + KEY;
      res = encoded;
    }
  } else if (choice == '-') {
    char decoded = 0;
    printf("Welcome to decoding program!\n");

    userInput = getchar();

    if (userInput < 'A' ||
        userInput > 'Z') { // If beyond ‘z’ bring back to start
      // -1 is because ‘z’ + 1 should become ‘a’, not ‘b’
      userInput += 32;
    }
    decoded = userInput - KEY;
    res = decoded;

  } else {
    printf("Can\'t enter anything else dumbass\n");
  }
  printf("%c", res);
  return 0;
}
