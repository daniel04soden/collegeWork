#include <stdio.h> 
#include <stdlib.h>

/**
 * Question 1:
 * Write a function that read characters from the user using getchar up to the
 * newline. The function will print out only the digits.
 */
void onlyDigits(){
  char c;
  while((c = getchar())!='\n'){
    if ((c >= '0') && (c <= '9')) {
      printf("%c",c);
    }
  }
}
/**
 * Question 2:
 * Write a function that copies a string (C string, 0 terminated).
 * The function takes as input a string. Allocates memory (allocate for 20 chars)
 * and copies the characters over. Then it returns the copy.
 * Remember to add the 0 add the end of the copy so it is a valid C string.
 */

char *copy_str(char *c){
  char *new_str = malloc(sizeof(char)*20);
  int i = 0;
  while (c[i] != '\0') {
    new_str[i] = c[i];
    i++;
  }
  new_str[i+1] = '\0';
  return new_str;
}

/**
 * Question 3:
 * Write a function that concatenates two integer arrays.
 * The function will take as parameters the two arrays and their lengths.
 * The function will allocate memory for the result array and return it.
 * The function will pass back the length of the result through a pointer
 * parameter.
 */

int *concat(int *arr1,int *arr2, int len1, int len2){
  int newArrLen = len1 + len2;
  int *newArr = malloc(sizeof(int)*newArrLen);
  for (int i = 0; i<len1;i++) {
    newArr[i] = arr1[i];
  }

  for (int j = len1+1 ; j<newArrLen ;j++) {
    newArr[j] = arr2[j];
  }

  return newArr;
}


/**
 * Question 4:
 * Write a function that interleaves a list of integer arrays.
 * The function will take as parameters the list of lists (int **), the number
 * of lists, an array of integers (int *) representing the lengths of the arrays.
 * The function will allocate memory for the result and return it.
 * The function will pass back the length of the result through a pointer
 * parameter.
 */

int* interleave(int **lists, int count,int *sizes){
  int* res;
  for (int i = 0; i<count; i++) {
    for (int j = 0; j<*sizes; j++) {
      res[i] = lists[i][j];
    }     
  }

  return res;
}

/**
 * Question 5:
 * Define a point structure that contains x and y integer coordinates.
 * Write a function that calculates and returns the middle of a line defined
 * by a start and end point. The function will take the parameters as pointers.
 * It will allocate memory for the result structure, and return it.
 * The middle of a line is a point whose x and y coordinates are the average
 * of the x and y coordinates of the start and end of the line.
 */

struct Point{
  int x;
  int y;
};

struct Point *calcMiddle(struct Point *start, struct Point *end){
  struct Point *res = malloc(sizeof(struct Point));
  res->x = (start->x + end->x)/2;
  res->y = (start->y + end->y)/2;

  return res;
}


int main(){
  onlyDigits();
}

