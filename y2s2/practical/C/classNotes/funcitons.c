#include <stdio.h>

void swap(int *addr1, int *addr2){
	*addr1 = *addr1 ^ *addr2;
	*addr2 = *addr1 ^ *addr2;
	*addr1 = *addr1 ^ *addr2;
}

int main(int argc, char *argv[])
{
	
	int a = 10;
	int *myptr;
	char character = 'c';
	char *mycharptr = &character;
	myptr = &a;
	int *newptr = 0;

	printf("%p\n",newptr);

	printf("a=%d\n",a);
	printf("char=%c\n",character);
	printf("------------------\n");
	*myptr = *myptr+1;
	printf("a=%d\n",a);
	*mycharptr = 'g';
	printf("char=%c\n",character);

	// Pointers on Pointers	
	printf("Address of a: %p\n",&a);
	// Pointers: Variables that store memory addresses
	int *myptr2 = &a;  // Memory Address of A 
	printf("%p\n",myptr2);
	printf("%ld\n",sizeof(myptr2)); // Output is 8 bytes as it is running on a 64 bit machine
	
	// XOR swap test 
	
	int z = 25;
	int y = 23;
	printf("z=%d\n",z);
	printf("y=%d\n",y);
	
	int *zptr = &z;
	int *yptr = &y;

	swap(zptr,yptr);
	printf("z=%d\n",z);
	printf("y=%d\n",y);
	return 0;
}
