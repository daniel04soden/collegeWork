#include <stdio.h>
int main()
{ 
  int arr[100];
  for (int i = 0; i<100;i++) {
    arr[i] = i;
  }

  for (int i=0;i<100;i++) {
    if (arr[i]%3 == 0 || arr[i] % 2 == 0 || arr[i]%5 == 0) {
      printf("%d ",arr[i]);
    } else {
      ;
    }
  }
  return 0;
}
