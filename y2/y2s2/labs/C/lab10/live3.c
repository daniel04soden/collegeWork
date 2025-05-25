#include <stdio.h>


void printFileContets(const char *fileName){

    FILE *f;
    char c;
    char buffer[100];

    if ((f = fopen(fileName,"r"))==NULL){
        printf("Error file not found");
        return;
    }


    while((c=fscanf(f,"%s",buffer)!=EOF)){
       printf("%s\n",buffer);
    }
    fclose(f);

}

int main(int argc, char *argv[]) {
    printFileContets("file.txt");
    return 0;
}
