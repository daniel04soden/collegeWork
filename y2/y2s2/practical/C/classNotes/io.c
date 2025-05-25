#include <stdio.h>

int main(){
    FILE *myFile;
     // w, write, r read, a append
    // myFile = fopen("test.txt","a");  w, write, r read, a append
    // myFile = fopen("test.txt","r");  w, write, r read, a append
    int val;
    if ((myFile = fopen("test.txt","w"))==NULL) {
       printf("Error file not found");
    }else{
        while (1) {
            int retval;
            retval = fscanf(myFile, "%d ", &val);
           if (retval == EOF) break;
        }

        printf("%d ",val);
        // fprintf(myFile,"Hello world %s\n","testing testing 123");
    }
    return 0;
}
