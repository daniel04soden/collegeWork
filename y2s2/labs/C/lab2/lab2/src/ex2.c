#include <stdio.h>
#define KEY 2
char encode(char userInput) { 
	return userInput + KEY; 
}

char decode(char userInput) { 
	return userInput - KEY; 
}

void main() {
  char c;
  char choice;
  printf("Would you like to encode or decode(+/-)\n");
  choice = getchar();
  getchar();


  if (choice == '+') {
    printf("Welcome to the encoding process\n");
  while ((c = getchar()) != '\n') {
    if (c >= 'A' && c <= 'Z') {
      c = c +  'a' - 'A';
    }
    c = encode(c);
    printf("%c",c);
  }

  } else if (choice == '-') {
    printf("Welcome to the decoding process\n");
  while ((c = getchar()) != '\n') {
    if (c >= 'A' && c <= 'Z') {
      c = c +  'a' - 'A';
    }
    c = decode(c);
    printf("%c",c);
  }

  } else {
    printf("Try again next time!\n");
  }
}
