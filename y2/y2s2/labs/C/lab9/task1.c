#include <stdio.h>

typedef struct {
  char regNumber[9];
  char owner[51];

} carReg;

void readFile() {
  FILE *ptr = fopen("carregdb.dat", "r");
  if (ptr == NULL) {
    perror("Failed to open file");
    return;
  }

  carReg reg;

  while (1) {
    int ret = 0;
    ret = fscanf(ptr, "%8s %50s", reg.regNumber, reg.owner);
    if (ret == EOF) {
      fclose(ptr);
      break;
    }
    printf("Car Owner:%s,\t Reg Number:%s\n", reg.owner, reg.regNumber);
  }
}

int main(int argc, char *argv[]) {
  readFile();
  return 0;
}
