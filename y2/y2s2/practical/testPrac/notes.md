# C programming examples 

## Making a pointer

### Standard pointer

```c
int x = 1;
int *ptr = &x;
printf("%p",*ptr);
```

### String pointer

```c 
    char string[20] = "What am i saying!!!";
    char *ptr = string; 
```

### Array pointer

```c
int arr[5] = {1,2,4,6,3};
int *ptr = arr;
```

### Struct pointer
```c
typedef struct Point{
    int x;
    int y;
}Point;

Point *test;
test->x = 2;
test->y = 4;
```

## Allocating memory 

### For a string

```c
char c;
*c = malloc(sizeof(char)*20);
```

### For an interger
```c
int x;
*x = malloc(sizeof(int)*20);
```




