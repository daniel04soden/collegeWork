// Author: Daniel Soden

#include <stdio.h>
float convertToCelsius(float fahr) { return (5.0 / 9.0) * (fahr - 32.0); }

float convertToFahr(float celc) { return 32.0 + celc * (9.0 / 5.0); }

int main() {
  float c = convertToCelsius(25.2);
  printf("%f\n", c);
  float f = convertToFahr(c);
  printf("%f\n", f);
  float g = convertToCelsius(f);
  printf("%f\n", g);
  short int i = 0;
  c = 25.3;

  while (i < 3) {
    printf("%f\n", c);
    c = (convertToFahr(c));
    printf("%f\n", c);
    c = convertToCelsius(c);
  }

  return 0;
}
