// Author: Daniel Soden
// Purpose: Starter Learning

// Includes

#include <stdbool.h>
#include <stdio.h>

bool isPrime(int num) {
  bool res;
  for (int i = 2; i < num; i++) {
    if (num % i == 0) {
      res = false;
    } else {
      res = true;
    }
  }
  return res;
}

int main() {
  for (int i = 0; i < 10; i++) {
    printf("%d\n", i);
  }
  int number;
  printf("Enter a number\n");
  scanf("%d", &number);

  printf("%b", isPrime(number));

  return 0;
}
