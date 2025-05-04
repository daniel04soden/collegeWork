#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#define MEM 10000

/*
Write a function that allocates memory, similar to malloc. It should work as
follows:

declare a large (e.g. 10000) global array of chars, this will be underlying
memory you will need to keep track of which memory is allocated or still
available: declare a struct that represents a memory allocation; it should have
a start address, a start index in the large array, end index, and size convert
this into a linked list so that you can store a dynamic list of memory
allocations when a memory allocation request is received you need to find
available memory for it scan the linked list of allocations and find the first
available space create a new structure to represent the allocation and insert it
into the list at the right location if you cannot find a region sufficiently
large to satisfy the request, return NULL you have to make sure that the list is
maintained in order of allocation
 * */
struct trackAlloc {
  void *start_addr;
  int end_index;
  int start_index;
  int size;
  struct trackAlloc *next;
};

char memory[1000];

struct trackAlloc *createAlloc(void *startAddr, int startIdx, int endIdx,
                               int size) {
  struct trackAlloc *newAlloc = malloc(sizeof(struct trackAlloc));
  newAlloc->start_addr = startAddr;
  newAlloc->end_index = endIdx;
  newAlloc->end_index = endIdx;
  newAlloc->size = size;
  newAlloc->next = NULL;

  return newAlloc;
}

void insert(struct trackAlloc *head, struct trackAlloc *newMem) {
  if (head == NULL) {
    head = newMem;
  } else if (head->next == NULL && head->size > 1000) {
    head->next = newMem;
    newMem->start_index = head->end_index + 1;
    newMem->end_index = newMem->size / 8 + newMem->start_index;
  }
  while (head->next != NULL) {
  }
}

void myAlloc(int size) {}

// create
// insert

int main(void) { return 0; }
