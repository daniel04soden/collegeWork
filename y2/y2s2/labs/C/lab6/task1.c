#include <stdio.h>
#include <stdlib.h> // For random
void interleave(int *first, int *first_length, int *second, int *second_length, int *return_array, int *result_length){
    int i=0,j=0;
    while(i < *result_length){
        if (i < *first_length && i < *second_length ) {
           return_array[j] = first[i];
           return_array[j+1] = second[i];
           j+=2;
        }else if(i >= *first_length) {
            return_array[j] = second[i];
            j++;
        }else{
            return_array[j] = first[i];
            j++;
        }
        i++;
    }
}



int main(){
  int *first, *second;
  // Declare the interleaved result array

  srandom(1234); // Set the PRNG seed for repeatability
  int firstlen = 10+random()%10;
  int secondlen = 10+random()%10;

  first = malloc(sizeof(int)*firstlen);
  second = malloc(sizeof(int)*secondlen);
  int result_length = firstlen + secondlen;

  // Allocate memory for the interleaved result array

  int *newArr = malloc(sizeof(int)*result_length);

  for (int i=0;i<firstlen;i++) first[i] = random()%100;
  for (int i=0;i<secondlen;i++) second[i] = random()%100;
  // Print first and second arrays
  for (int i=0;i<firstlen;i++) printf("%d ",first[i]);
  printf("\n");
  for (int i=0;i<secondlen;i++) printf("%d ",second[i]);
  printf("\n");
  // Call interleave function
  interleave(first,&firstlen,second,&secondlen,newArr,&result_length);
  // Print interleaved array
  for (int i=0;i<result_length;i++) printf("%d ",newArr[i]);
  printf("\n");
  return 0;
}
