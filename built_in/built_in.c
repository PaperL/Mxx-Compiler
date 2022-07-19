// For 32-bit
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define PTR_SIZE sizeof(char*)
#define INT_SIZE sizeof(int)

// char *__NEW_ON_HEAP(int size) { return (char *) malloc(size); }
//
// declare i8* @malloc(i32)
//
// define i8* @__NEW_ON_HEAP(i32 %0) {
//   %2 = call i8* @malloc(i32 %0)
//   ret i8* %2
// }

char *__NEW_ARRAY(int size, int dimension, int *arraySize) {
    void *ptr;
    // Dimension mustn't be 0
    if (dimension == 1) {
        ptr = malloc(size * (*arraySize) + INT_SIZE) + INT_SIZE;
        *(int *) (ptr - INT_SIZE) = *arraySize;
    }
    if (dimension > 1) {
    ptr = malloc(PTR_SIZE * (*arraySize) + INT_SIZE) + INT_SIZE;
    *(int *) (ptr - INT_SIZE) = *arraySize;
    for (int i = 0; i < *arraySize; i++)
        ((char **) ptr)[i] = __NEW_ARRAY(size, dimension - 1, arraySize + 1);
    }
    return (char *) ptr;
}

void __PRINT(char *str) { printf("%s", str); }

void __PRINTLN(char *str) { printf("%s\n", str); }

void __PRINT_INT(int val) { printf("%d", val); }

void __PRINTLN_INT(int val) { printf("%d\n", val); }

char *__GET_STRING() {
    char *str = (char *) malloc(256 + INT_SIZE) + INT_SIZE;
    scanf("%255s", str);
    *(int *) (str - INT_SIZE) = strlen(str);
    return str;
}

int __GET_INT() {
    int val;
    scanf("%d", &val);
    return val;
}

char *__TO_STRING(int val) {
    char *str = (char *) malloc(13 + INT_SIZE) + INT_SIZE;
    // -2147483648
    sprintf(str, "%d", val);
    *(int *) (str - INT_SIZE) = strlen(str);
    return str;
}

char *__STRING_ADD(char *str1, char *str2) {
    int len = strlen(str1) + strlen(str2);
    char *str = (char *) malloc(len + 1 + INT_SIZE) + INT_SIZE;
    strcpy(str, str1);
    strcat(str, str2);
    *(int *) (str - INT_SIZE) = len;
    return str;
}

char __STRING_EQUAL(char *str1, char *str2) { return (strcmp(str1, str2) == 0); }

char __STRING_NOT_EQUAL(char *str1, char *str2) { return (strcmp(str1, str2) != 0); }

char __STRING_LESS(char *str1, char *str2) { return (strcmp(str1, str2) < 0); }

char __STRING_GREATER(char *str1, char *str2) { return (strcmp(str1, str2) > 0); }

char __STRING_LESS_OR_EQUAL(char *str1, char *str2) { return (strcmp(str1, str2) <= 0); }

char __STRING_GREATER_OR_EQUAL(char *str1, char *str2) { return (strcmp(str1, str2) >= 0); }