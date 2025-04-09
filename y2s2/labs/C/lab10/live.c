#include <stdio.h>
/*
**
*Write a function that opens a file and prints the contents.
*The file is a text file containing a single string on each line.

You will need to loop through the file with fscanf until you find the EOF, printing each
string as you go.

The file will take as parameter a file name (const char *) and will return nothing
**
**
*/


void printFileContets(const char *fileName){
    // File
    // make character
    // open file and See if file is null
    // Make buffer
    // while c fscanf buffer is not end of file
    // Print buffer
    // close file
    FILE *file;
    char c;
    char buffer[100];

    if ((file = fopen(fileName,"r"))== NULL){
        printf("Could not open/find file");
    }

    while((c = fscanf(file,"%s",buffer))!= EOF){
       printf("%s\n",buffer);
    }
    fclose(file);
}


int main(int argc, char *argv[]) {
    printFileContets("file.txt");
    return 0;
}
