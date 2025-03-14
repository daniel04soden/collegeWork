#include <stdlib.h>

int* adding2dArr(int numArr,int **matrix, int *sizeOfArr,int *resultLen){
  for (int i = 0; i<*sizeOfArr; i++) {
    if (sizeOfArr[i]>*resultLen) {
      *resultLen = sizeOfArr[i]; 
    }else{
      continue;
    }
  }
  int *resultArr = malloc(sizeof(int)**resultLen);
  
  int k = 0;
  int numToAdd = 0;
  for (int i = 0; i < *resultLen; i++) {
    numToAdd = 0;
    for (int j = 0; j < numArr;j++) 
      if (i < sizeOfArr[j]) {
        continue;
      }else{
        numToAdd = matrix[i][j];
        k++;
      }
     resultArr[k] = numToAdd;
    }

  return resultArr;
}


int main(){
  
  return 0;
}
