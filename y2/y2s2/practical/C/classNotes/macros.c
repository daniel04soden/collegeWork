#include <stdio.h> // Copies all code/functions from stdio file - works for onw files too
#include <stdlib.h> // Copies all code/functions from stdio file - works for onw files too
#include "preprocessortest.h"

#define OBJECT 10
#define MAX_SIZE 100
#define newline printf("\n")
#define random rand
#define MAX(A,B)  ((A)>(B)?(A):(B)) // Be careful with operators, surround brackets
#define helloworld(NAME) printf("fuck you %s\n", #NAME); // Surround as string ??
#define defcar(name) struct car car_##name     = {#name};
#define printcar(name) printf("%s \n", car ##name.manuf);
#define DEBUG INFO

struct car{
    char manuf[10];
};

int main(int argc, char *argv[]) {
    int array[MAX_SIZE];
    struct test t;
    t.a = 2;
    t.b = 9;
    myFun(t);
    printf("hello");
    newline;
    printf("world");
    int x = random()%100;
    newline;
    int y = MAX(2, 200);
    printf("%d",x);
    newline;
    printf("%d",y);
    newline;
    helloworld("Daniel");
    defcar(Epic);
    #if DEBUG == WARN
    printf(">>>DEBUG VALUE\n");
    #elif DEBUG == INFO
    printf(">>>INFO");
    #endif

    return 0;
}
