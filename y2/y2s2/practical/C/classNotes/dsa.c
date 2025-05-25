#include <stdio.h>
#include <stdlib.h>

// Linked list

struct node{
  int val;
  struct node *next;

  // End of list is when next is null
  // Doubly - struct node *last;
  // for moving in the list cursor = cursor->next
  // Next is null is the end of the list
};

struct node *create_node(int val){
  struct node *newnode = malloc(sizeof(struct node));
  newnode -> val = val;
  newnode -> next = NULL;
  return newnode;
}

struct node *scan_list(struct node *head, int val){
  struct node *cursor;
  cursor = head;

  while (cursor!=NULL){
    if (cursor->val == val) {
      return cursor;
    }
    cursor = cursor -> next;
  }
  return NULL;
}


void add_value(struct node *listhead, int val){
  struct node *cursor = create_node(val);

  while (cursor -> next !=NULL) {
    cursor = cursor->next; 
  }

  cursor -> next = cursor;
};


void insert_node(struct node *pred, struct node *newNode){
  newNode -> next = pred -> next;
  pred -> next = newNode;
}

void remove_node(struct node **listhead, struct node *toremove){
  struct node *cursor = *listhead;
  // listhead = 0xabcd to remove = 0xabcd 
  if (toremove == *listhead) {
    *listhead = toremove->next;
  }else{
    while(cursor->next !=toremove){
      cursor = cursor-> next;
    }
    cursor->next= toremove-> next;
  }
  free(toremove);
}

int main(int argc, char *argv[])
{
  
  int a = 10, b = 20;
  struct node *myList = create_node(1);
  remove_node(&myList, myList);
  return 0;
}
