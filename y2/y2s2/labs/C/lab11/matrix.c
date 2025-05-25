#include <stdio.h>
#include <stdlib.h>

/**
 * Generate a matrix of 20 columns and given number of rows.
 * Values are stored in the provided memory address
 */

/*
void printFile(char * fileName){
    FILE *f;

    int where = sizeof(int);

    if ((f = fopen(fileName,"rb"))== NULL){
        perror("Could not open/find file");
        return;
    }

    fseek(f,-where,SEEK_END);
    fread(&where,sizeof(int),1,f);
    printf("%d",where);
    fseek(f,0,SEEK_SET);
    carreg *x = malloc(sizeof(carreg)*where);
    fread(x,sizeof(carreg),where,f);
    for (int i =0; i<where; i++) {
       printf("Car reg: %s, Owner Name: %s\n",x[i].regnum,x[i].owner);
    }
    fclose(f);
}
*/


void read_matrix_from_file(int (*matrix)[20], char* fileName){
		FILE *f;
    if ((f = fopen(fileName, "rb")) == NULL){
        perror("fopen: failed");
        exit(1);
    }
		int rows;
		int columns;
		int temp = 0;
		fread(&rows,sizeof(int),1,f);
		fread(&columns,sizeof(int),1,f);
    for (int i=0;i<rows;i++){
        for (int j=0;j<columns;j++){
				fread(&temp,sizeof(int),1,f);
				matrix[i][j] = temp;
        printf("%2d ", matrix[i][j]);
        }
		printf("\n");
    }
}

void rotate_cw(int (*matrix)[20], int (*rotated)[20]){
		FILE *f;
    if ((f = fopen(fileName, "rb")) == NULL){
        perror("fopen: failed");
        exit(1);
    }
		int rows;
		int columns;
		int temp = 0;
		fread(&rows,sizeof(int),1,f);
		fread(&columns,sizeof(int),1,f);
    for (int i=0;i<rows;i++){
        for (int j=0;j<columns;j++){
				fread(&temp,sizeof(int),1,f);
				matrix[i][j] = temp;
        printf("%2d ", matrix[i][j]);
        }
		printf("\n");
    }

}

void gen_matrix(int (*matrix)[20], int num_rows){
    if (matrix == NULL) return;

    for (int i=0;i<num_rows;i++){
        for (int j=0;j<20;j++){
            matrix[i][j] = random()%100;
            printf("%2d ", matrix[i][j]);
        }
        printf("\n");
    }

}

int main(){
    FILE *f;

    if ((f = fopen("matrix.bin", "w")) == NULL){
        perror("fopen:");
        exit(1);
    }

    int num_rows = 20;
    int num_cols = 20;
    int (*matrix)[20] = malloc(sizeof(int)*20*num_rows);

    gen_matrix(matrix, num_rows);
    // Write the matrix to file in binary format
    // Start with the number of rows and columns
    fwrite(&num_cols, sizeof(int), 1, f);
    fwrite(&num_rows, sizeof(int), 1, f);

    // Write the matrix
    fwrite(matrix, sizeof(int)*20, num_rows, f);
		
    fclose(f);
		printf("Next epic part mr soden\n");

    int (*matrix_two)[20] = malloc(sizeof(int)*20*num_rows);
    int (*rotate_two)[20] = malloc(sizeof(int)*20*num_rows);
		read_matrix_from_file(matrix_two,"matrix.bin");
	

		return 0;
}
