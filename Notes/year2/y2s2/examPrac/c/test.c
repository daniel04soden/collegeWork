#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int str_search(char *string, char c) {
  int idx = 0;
  while (*string != '\0' && *string != c) {
    string++;
    idx++;
  }
  return idx;
}
void caller() {}
/*
void more_pointers() {
  int array[10]; // array of 10 ints
  int *pointer;// unalloc pointer
  int i; // i declared for loop
  for (i = 0; i < 10; i++)
    array[i] = i; // values at i become i so 0-9
  // Explain below
  pointer = &array[2]; // deref pointer should be 2
  // printf("%d\n", pointer);
  printf("%d\n", *pointer);
  // printf("%d\n", &pointer);
  pointer++;
  printf("%d\n", *pointer);
}
*/

/*
void pointers_intro() {
  int v = 1;
  int *p;
  p = &v; // valid pointer
  *p = *p + 1;  // correct way, pointer arithmetic
  printf("%d\n",v);
  int myarray[10];
  int *x = malloc(10 * sizeof(int));
  // Explain each of the following, valid/invalid, syntax errors?
  // myarray = x; // Error, cannot assign pointer to be the array
  myarray[0] = *x; // Needs to be dereferenced first  - only alloced, default 0
val printf("%d\n",myarray[0]);
}
*/

int main(void) {
  char *string = "hello";
  printf("%d", str_search(string, 'l'));
  printf("%s", string);
  return 0;
}
