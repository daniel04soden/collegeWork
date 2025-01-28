#include <stdio.h>

int main() {
  int letter;
  while ((letter = getchar()) != EOF) {
    if (letter >= 'a' && letter <= 'z') {
      letter = letter - 'a' + 'A';
    }
    printf("%c", letter);
  }
}
