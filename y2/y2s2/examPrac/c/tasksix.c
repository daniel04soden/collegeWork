#include <stdio.h>

void read_file(const char *fileName){
  FILE *f;
  if ((f=fopen(fileName,"r"))==NULL){
    printf("Null");
  }
  char c;
  char buffer[100];
  while ((c=fscanf(f,"%s" ,buffer))!=EOF) {
    printf("%s\n",buffer); 
  }
  printf("\n");
  fclose(f);
}


int main() {
  read_file("test.txt");
  return 0;
}
