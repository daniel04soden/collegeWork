#include <stdio.h>
#include <stdlib.h>
void array_test() {
  int array[20];
  // What is the meaning of the following, are they valid or not, do they raise
  // errors?
  printf("%d\n", array[0]);  // There is nothing allocated here so can we even
                             // access this memory?
  printf("%d\n", array[20]); // Same deal
  printf("%d\n", array[-1]); // Wild card in c?
}
// Identify bugs
void array_sum() {
  int array[20];
  int sum = 0;
  int i = 0;
  for (; i < 20;) { // equiv to a while (i<20)
    array[i++] = random() % 100;
  }
  for (i = 0; i < 20; i++)
    sum += array[i];
  printf("Sum is %d\n", sum);
}
void char_test() {
  char c1, c2, c3;
  // What are these assignments doing?
  c1 = random() % 26 + 'a'; // Random lowercase
  c2 = random() % 26 + 'A';
  c3 = random() % 9 + '0';
  c1 = c1 + 'A' - 'a'; // Converts lowercase to uppercase
  //-------------------
  // What is the outcome of these?
  c1 = 'b';
  c1++;
  c2 = 'B';
  c2++;
  c3 = '3';
  c3++;
  c2 = 'Z';
  c2++; // Next char in ASCII table after 'Z'
}
// In the following, user keyboard input is "a<Enter>b"
// What is the output?
void getchar_test() {
  printf("%c\n", getchar()); // prints 'a'
  printf("%c\n", getchar()); // prints '\n'
}
void scanf_getchar() {
  int value = 0;
  scanf("%d", &value);
  printf("%c", getchar()); // what is the output here?
}
void printf_tests() {
  const char *string = "Hello %d %c";
  int intvar = 1;
  char c = 'a';
  // printf(const char *fmtstring, ...)
  // Explain each of the following, valid/invalid, syntax errors?
  printf(string); // This is ok
  printf(intvar); // Syntax err, type mismatch.
  printf(c);      // Type mismatch
  printf(&c);     // Could be ok, because &c is a char *
  //
  printf("%s %d %c\n", string, intvar, c);
  printf("%s %d %c\n", string, intvar); // Problem, fewer args than fmtspec
  printf("%d %c\n", c, intvar);         // Correct.
}
void pointers_intro() {
  int v = 1;
  int *p;
  p = &v;
  // Explain each of the following, valid/invalid, syntax errors?
  *p = *p + 1; // v = 2
  p = *p + 1;  // pointer is equal to address 2. WRONG
  *p = p + 1;  // v = &v+4 (4B, because p is int pointer)
  p = p + 1;   // Pointer arithmetic, same as p++
  //--------------------------------
  int myarray[10];
  int *p = malloc(10 * sizeof(int)); // Space for 10 ints
  // Explain each of the following, valid/invalid, syntax errors?
  myarray = p;     // Syntax error. Cannot assign to array
  &myarray[0] = p; // Assigning something to a constant. 1 = p. Synt err
}
void more_pointers() {
  int array[10];
  int *pointer;
  int i;
  for (i = 0; i < 10; i++)
    array[i] = i;
  // Explain below
  pointer = &array[2];
  printf("%d\n", pointer);
  printf("%d\n", *pointer);
  printf("%d\n", &pointer);
  pointer++;
}
void memory_allocation() {
  // Which of the following correctly declares an array of 5 strings?
  char strings[5][100];      // Store 5 strings of up to 99 chars OK
  char *strings[5];          // Array of 5 pointers to char. OK
  char *strings = malloc(5); // Array of 5 chars NOT OK
  char **strings = malloc(5 * sizeof(char));   // Same as above NOT OK
  char **strings = malloc(5 * sizeof(char *)); // Array of 5 pointers to char
  // Allocate an array of integers of user-specified length
  int length;
  scanf("%d", &length);
  // Discuss the following
  int array[] = malloc(length * sizeof(int)); // Incorrect, can't assign to arr
  int *array = malloc(length);                // Not assigning enough
  int *array = malloc(sizeof(length));       // Equiv to sizeof(int). Not enough
  int *array = malloc(sizeof(int) * length); // CORRECT
}
void scanf_test() {
  int v;
  char c;
  // Explain below
  scanf("%d", v);  // doesnt work not accessing memory of integer
  scanf("%c", &c); // works okay
  scanf("%c", &v); // works but takes in corresponding character addreess
  scanf("%d", &c); // doesnt work
  // Explain below
  short *array = malloc(10 * sizeof(short));
  for (int i = 0; i < 10; i++)
    scanf("%d", &array[i]); // assigns the memory address of the array i to the
                            // user input, up to short max so - 32767
}
// Pass by value
//---------------------------------------------------------------
int str_search(char *string, char c) {
  int idx = 0;
  while (*string != '\0' && *string != c) {
    string++;
    idx++;
  }
  return idx;
}
void caller() {
  char *string = "hello";
  printf("%d", str_search(string, 'l'));
  printf("%s", string);
}
//---------------------------------------------------------------
void create_array(int *array, int size) {
  array = malloc(size * sizeof(int));
  for (int i = 0; i < size; i++)
    array[i] = random() % 100;
}
void caller() {
  int *array;
  create_array(array, 10);
  for (int i = 0; i < 10; i++)
    printf("%d ", array[i]);
}
//---------------------------------------------------------------
struct module {
  char id[10];
  int result;
};
struct personal {
  char address[50];
  char email[50];
  char telephone[15];
};
struct student {
  char name[50];
  int year;
  struct personal details;
  struct module *modules;
};
void struct_test() {
  struct student s;
  // Which of the following is correct?
  s.modules = malloc(10 * sizeof(struct module));
  s.modules = malloc(10 * sizeof(struct module *));
  // Which of the following is correct or not
  printf("%s", s.name);
  printf("%s", s.details.address);
  printf("%s", s.details->address);
  printf("%s", s->details.address);
  printf("%s", s->details->address);
  //
  struct student s;
  printf("%d", s.module->result);
  printf("%d", s->module.result);
  printf("%d", s->module->result);
  printf("%d", s.module->result);
  free(s.modules);
  // Explain below
  s.modules = malloc(sizeof(struct module) * 10);
  struct modules *cursor = s.modules;
  printf("%s %d\n", cursor->id, cursor->result);
  cursor++;
  printf("%s %d\n", cursor->id, cursor->result);
  cursor++;
  printf("%s %d\n", cursor->id, cursor->result);
}
//---------------------------------------------------------------
//--- FILES -----------------------------------------------------
void file_basics() {
  FILE *f;
  int intvar;
  char charvar;
  char *string = malloc(10);
  f = fopen("myfile", "r");
  fprintf(f, "%d %s\n", intvar, string);
  fclose(f);
  f = fopen("myfile", "w");
  fscanf(f, "%d %s", &intvar, string);
  fclose(f);
}
void more_files() {
  FILE *f1, *f2;
  f1 = fopen("srcfile", "r");
  f2 = fopen("dstfile", "w");
  char c;
  while ((c = fgetc(f1)) != EOF)
    fputc(c, f2);
  fclose(f2);
  fclose(f1);
}

void conversionGetchar() {
  char letter;
  while ((letter = getchar()) != '\n') {
    if (letter >= 'a' && letter <= 'z') {
      letter = letter - 'a' + 'A';
    }
    printf("%c", letter);
  }
}
