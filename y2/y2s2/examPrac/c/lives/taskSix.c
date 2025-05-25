#include <stdio.h>

void readFile(const char *fileName){
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



int main(void){
  readFile("test.txt");
  return 0;
}
