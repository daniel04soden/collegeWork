#include <stdio.h>
// File
// make character
// open file and See if file is null
// Make buffer
// while c fscanf buffer is not end of file
// Print buffer
// close file


void printFileContets(const char *fileName){
    FILE *f;
    char c;

    if((f = fopen(fileName,"r"))){
        printf("Null");
    }

    char buffer[100];

    while((c = fscanf(f, "%s",buffer))!=EOF){
        printf("%c",c);
    }

    fclose(f);


}


int main(int argc, char *argv[]) {

    return 0;
}
