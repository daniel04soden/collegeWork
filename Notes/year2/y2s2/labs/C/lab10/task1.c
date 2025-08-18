#include <stdio.h>
#include <stdlib.h>

typedef struct carreg{
    char regnum[9];
    char owner[51];
}carreg;

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

void insertEle(char *fileName,int idx){
    FILE *f;

    if ((f = fopen(fileName,"wb"))== NULL){
        perror("Could not open/find file");
        return;
    }



}


int main(int argc, char *argv[]) {
    printf("Um guys \n");
    printFile("carregdb.bin");
    return 0;
}
