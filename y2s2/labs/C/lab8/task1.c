#include <stdio.h>
#include <stdlib.h>

void print_matrix(int matrix[][5], int rows, int cols){
    int i,j;

    for (i=0;i<rows;i++){
        for (j=0;j<cols;j++){
            printf("%2d ", matrix[i][j]);
        }
        printf("\n");
    }
}

void print_matrix_bycolumn(int matrix[][5], int rows, int cols){
    int i,j;
    for (i=0; i<cols; i++) {
      for (j = 0; j<rows; j++) {
        printf("%2d ",matrix[j][i]);
      } 
      printf("\n");
    }

}


void rotation(int matrix[][5], int rows, int cols){};

void rotationCounter(int matrix[][5], int rows, int cols){};

int main(){
    int matrix[5][5];
    int i,j;

    srandom(1);
    for (i=0;i<5;i++)
        for (j=0;j<5;j++)
            matrix[i][j] = random()%20;
    printf("Printing row by row\n");
    print_matrix(matrix, 5, 5);
    printf("Printing column by column\n");
    print_matrix_bycolumn(matrix, 5, 5);
  return 0;
}
