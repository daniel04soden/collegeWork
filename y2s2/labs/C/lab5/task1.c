#include <stdio.h>
#include <stdlib.h>

/**
 * Determines the length of a NULL-terminated string.
 *
 * Returns the number of characters in the string.
 */
int string_length(const char *string){
    int i=0;
    while (string[i] != '\0') i++;
    return i;
}

void copy_string(const char *src, char *dst){
  for (int i = 0; src[i] != '\0'; i++) {
        dst[i] = src[i];
    }
}

/**
 * Print a list of strings and their lengths
 *
 * Parameters:
 * strings -- array of pointers to char, that is, strings.
 *            Note that the pointers are constant, which means that we are not
 *            allowed to change the strings inside the function.
 * num_strings -- number of strings in array.
 */
void print_strings(const char *strings[], int num_strings){
    for (int i=0;i<num_strings;i++){
        printf("[%2d] %s (%d)\n", i, strings[i], string_length(strings[i]));
    }
}

void find_longest(const char *strings[], int num_strings){
    char *curr_longest = "";
    int longest = 0;
    for (int i=0;i<num_strings;i++){
     if (string_length(strings[i]) > longest){
        curr_longest = strings[i];
        longest = string_length(curr_longest);
     }else{
      continue;
    }
  }
  printf("%s",curr_longest);
  free(curr_longest);
}

int main(){
    // Array of 5 pointers (addresses) to chars. Will hold max 5 strings.
  // Comments are notes from board
    int num_strings = 5;
    // scanf("%s", strings[i]);
    char *strings[num_strings];
    // char **strings = malloc(num_strings*sizeof(char*));

    for (int i=0;i<num_strings;i++){
        // Read a new string at strings[i]
        // However, must first allocate memory for it!
        char tmp[20];
        scanf("%s", tmp);
        int len = string_length(tmp);
        strings[i] = malloc((len+1)*sizeof(char));
        copy_string(tmp, strings[i]);
        // for (int j =0; j<tmplen; j++) {
          // strings[i][j] = tmp[j];
          // strings[i][j] = ?;
      
        // }
}
    // We have to cast strings to a const for the function, otherwise we get
    // a warning.
    // Note that we have const char **, so a pointer to a pointer.
    // This is because strings in array (so a pointer) of pointers.
    print_strings((const char **)strings, 5);
    find_longest((const char**)strings,num_strings);
    free(*strings);
  return 0;
}
