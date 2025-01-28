#include <stdio.h>

void main(){
char c = 0;
while((c = getchar()) != EOF){

if ((c >= 'a')&&( c<='z')){
c = c - 'a' + 'A';

}
printf("%c",c);
}

}
