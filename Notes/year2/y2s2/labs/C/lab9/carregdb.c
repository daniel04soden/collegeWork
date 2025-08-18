#include <stdio.h>
#include <stdlib.h>

struct carreg {
  char regnum[9];
  char owner[51];
};

struct carreg *gen_db(int size) {
  struct carreg *db = malloc(size * sizeof(struct carreg));
  srand(1234);
  for (int i = 0; i < size; i++) {
    db[i].regnum[0] = '0' + rand() % 10;
    db[i].regnum[1] = '0' + rand() % 10;
    db[i].regnum[2] = 'A' + rand() % ('Z' - 'A');
    db[i].regnum[3] = 'A' + rand() % ('Z' - 'A');
    db[i].regnum[4] = '0' + rand() % 10;
    db[i].regnum[5] = '0' + rand() % 10;
    db[i].regnum[6] = '0' + rand() % 10;
    db[i].regnum[7] = '0' + rand() % 10;
    db[i].regnum[8] = 0;
    int j = 0;
    for (; j < 10 + rand() % 40; j++)
      db[i].owner[j] = 'a' + rand() % ('z' - 'a');
    db[i].owner[j] = 0;
  }
  return db;
}

void main() {
  FILE *f;
  if ((f = fopen("carregdb.dat", "w")) == NULL) {
    fprintf(stderr, "Error opening file for writing\n");
    return;
  }

  struct carreg *db = gen_db(20);
  for (int i = 0; i < 20; i++)
    fprintf(f, "%s %-50s\n", db[i].regnum, db[i].owner);
  fclose(f);
}
