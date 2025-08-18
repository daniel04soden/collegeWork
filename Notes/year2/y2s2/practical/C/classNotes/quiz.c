#include <stdio.h>
char array[10] = {0xa, 0xb, 0xc, 0xd};
int *p = &array[0];

int *array = malloc(sizeof(int)*10);

for (int i = 0; i<10;++) {
	printf("%d\n",*array)
}
