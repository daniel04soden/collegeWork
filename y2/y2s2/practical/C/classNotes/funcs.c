#include <stdio.h>
#include <stdlib.h>

int *clonearray(int *array,int len){
	int *dup = malloc(len*sizeof(int));
	if (dup == NULL) {
		return NULL;
	}
	for (int i =0 ; i<len; i++) {
		dup[i]=array[i];
	}
	return dup;
}

void incrValuesInArr(int *array,int len){ // Possible as arrays passed as address vs the integer swapping last week
	int i;
	for (i=0; i<len;i++) {
		array[i]++;
	}
}

int sumArray(int arr[],int len){
	int sum = 0;
	int i;
	for (i =0; i<len; i++) {
		sum+=arr[i];
	}
	return sum;
}


int main(){

	int arr[5] = {1,2,3,4,5};
	int *p;
	p = &arr[0];
	printf("%d\n",*p);

	printf("%x\n",arr);
	printf("%p\n",arr);
	int sum = sumArray(arr,5);
	incrValuesInArr(arr, 5);
	printf("%d",sumArray(arr, 5));
	int *pp;
	pp =  clonearray(arr,5);
	for (int j =0; j<5; j++) {
		printf("%d\n",pp[j]);
	}
	free(pp);

	int myInt;
	char epicString[24];
	scanf("%d",&myInt);
	printf("%d\n",myInt);
	scanf();
	scanf("%d",&myInt);
	printf("%s\n",epicString); // No need for memory address pointer, already memory address given its an array
	return 0;
}
