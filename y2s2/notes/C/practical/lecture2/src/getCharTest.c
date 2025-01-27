#include <stdio.h>

int main() {
  char myDigit = 0;
  char stringy[10];
  int counter = 0;
  while ((myDigit != '\n') && (counter < 10)) {
    myDigit = getchar();
    if (myDigit == '\n') {
      break;
    }
    if ((myDigit >= 'a') && ((myDigit <= 'z'))) {
      myDigit = myDigit - 'a' + 'A';
    }
    stringy[counter] = myDigit;
    counter++;
  }
  printf("%s\n", stringy);
  return 0;
}
