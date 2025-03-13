#include <stdio.h>
struct student{
  char name[10];
  int year;
  int course;
};

void main(){
  FILE *data;

if ((data = fopen("test.bin","r")) == NULL) return;

  struct student students[100];

  int i =0;

  while (1) {
    int readbytes = 0;
    int index = -1;
    printf("Index to be read:");
    scanf("%d",&index);
    if (index < 0) {
      break;
    }
    
    fseek(data,index*sizeof(struct student), SEEK_SET);
    read_items  = fread(&);
    /*
    readbytes = fread(&students[i],
                      sizeof(struct student),
                      1,
                      data);
    if (readbytes !=sizeof(struct student)){
      if (feof(data)){
        printf("End of file\n");
        break;
      }
    }
    */

  }



}
