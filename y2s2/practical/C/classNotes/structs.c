#include <stdio.h>
#include <stdlib.h>

typedef struct colour{
  int r;
  int g;
  int b;
}colour;

typedef struct point{
  int x;
  int y;
  char *name;
  colour color;
}Point; // structs are pass by value rather than pass by reference 

void copy_struct(Point *src, Point *dest);

int main(){
  // Actually using the struct
  Point p;
  p.x = 1;
  p.y = 2;
  p.name = "the";
  Point * structptr = &p;

  // Better way of defining
  Point g = {1,2,"g"};
  
  // Even better way imo
  Point c  = {
    .x = 1,
    .y = 2,
    .name = "c",
    .color.r = 234,
    .color.g = 234,
    .color.b = 234
  };
  Point * doubleStructPtr = &c;
  printf("%d\n",structptr->x); // Better more widely used version
  printf("%d\n",(*structptr).x); // Standard cumbersome version
  scanf("%d\n",&p.x); // .x has presedence over &
  printf("%d\n",(*structptr).x); // Standard cumbersome version
  scanf("%d\n",&c.color.g); // .x has presedence over &
  printf("%d\n",doubleStructPtr->color.g); // Better more widely used version
  return 0;
}
