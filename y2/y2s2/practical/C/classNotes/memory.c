#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct student {
  char name[50];
  int year;
  int course;
};

// memcpy
// memset

int main() {
  struct student *dynarray = malloc(sizeof(struct student) * 50);
  struct student dest[50];
  struct student s;
  s.year = 2;
  s.name[1] = 'a';
  s.course = 33;
  printf("Student year %d\n", s.year);
  memcpy(dest, dynarray, 50 * sizeof(struct student));
  memset(dynarray, 0, 50 * sizeof(struct student));
  memset(&s, 0, sizeof(struct student));
  printf("Student year %d\n", s.year);

  for (int i = 0; i < 5; i++) {
    printf("Year %d:%d\n", i + 1, dynarray[i].year);
  }

  if (!memcmp(dynarray, dest, sizeof(struct student) * 50)) {
    printf("Woah woah woah");
  }

  return 0;
}
