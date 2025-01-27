#include <stdio.h>
#define KEY 5
int main() {

  char input = 0;   // Stores input chars as they are read
  char encoded = 0; // Stores encoded chars
  char decoded = 0; // Stores decoded chars

  int i = 0; // Used for the while loop

  while (i < 3) {      // Read 3 characters
    input = getchar(); // Read a character

    // Only accept uppercase. If other, will give another chance otherwise
    if (input < 'A' || input > 'Z')
      continue;

    encoded = input + KEY; // Encode by adding key
    if (encoded > 'Z') {   // If beyond ‘z’ bring back to start
      // (encoded - ‘z’) -> number of characters beyond ‘z’
      // Add this to ‘a’
      // -1 is because ‘z’ + 1 should become ‘a’, not ‘b’
      encoded = 'A' + encoded - 'Z' - 1;
    }

    printf("%c", encoded);

    decoded = input - KEY; // Encode by adding key
    if (decoded < 'A') {
      decoded = decoded - 'A' + 'Z' + 1;
    }
    printf("%c", decoded);
    i++; // Processed one character successfully
  }

  printf("\n");
  return 0;
}
