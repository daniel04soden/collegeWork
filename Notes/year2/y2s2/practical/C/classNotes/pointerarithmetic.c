#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
struct test {
  int a, b;
};

struct list {};
struct compli {
  int s;
  char *sp;
  struct list *list;
  double value;
};

struct node {
  void *value;
  struct node *
      next; // Linux kernel - linked list implemented in ds not in struct itself
};

unsigned long findOffSt(void *field, void *base) {
  return field - base;
} // getting the offset between two fields in a struct

int getStrLen(char *s) {
  char *t = s;
  while (*s)
    s++;
  return t - s;
}

void rotMatri(int (*m)[5], int rows) {}

int main(int argc, char *argv[]) {
  struct test fixed[10];
  struct test *p = fixed;
  int offset = 1;

  for (int i = 0; i < 10; i++) {
    printf("%p\n", p);
    p = (void *)p + offset;
  }

  struct compli *s1 = malloc(sizeof(struct compli));
  struct compli *s2 = malloc(sizeof(struct compli));
  s1->list = malloc(sizeof(struct list));
  unsigned long list_offst = offsetof(struct compli, list);
  struct compli *s = (void *)&s2->list - list_offst;
  printf("%f\n", s->value);

  int matrix[10][5];
  int array[10];
  int *a = malloc(sizeof((int)*10));
  int (*m)[5] = malloc((sizeof((int)*5)*10);
  
	
  void *p = rotMatri(matrix, 10);
  printf("%p",p);
  return 0;
}
