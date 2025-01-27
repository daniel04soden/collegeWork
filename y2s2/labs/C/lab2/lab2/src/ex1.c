#include <stdio.h>

int main() {

  char input = 0;   // Stores input chars as they are read
  char encoded = 0; // Stores encoded chars

  int key = 5; // The key (offset) added to each char
  int i = 0;   // Used for the while loop

  while (i < 3) {      // Read 3 characters
    input = getchar(); // Read a character

    // Only accept lowercase. If other, ignore the character
    if (input < 'a' || input > 'z')
      continue;

    encoded = input + key; // Encode by adding key
    if (encoded > 'z') {   // If beyond ‘z’ bring back to start
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
