// For 32-bit
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define PTR_SIZE sizeof(char *)
#define INT_SIZE sizeof(int)

// * BASIC
char *__NEW_ARRAY(int size, int dimension, int *arraySize) {
    if (*arraySize == 0) return NULL;
    void *ptr;
    // Dimension mustn't be 0
    if (dimension == 1) {
        ptr = malloc(size * (*arraySize) + INT_SIZE) + INT_SIZE;
        *(int *)(ptr - INT_SIZE) = *arraySize;
    }
    if (dimension > 1) {
        ptr = malloc(PTR_SIZE * (*arraySize) + INT_SIZE) + INT_SIZE;
        *(int *)(ptr - INT_SIZE) = *arraySize;
        for (int i = 0; i < *arraySize; i++)
            ((char **)ptr)[i] = __NEW_ARRAY(size, dimension - 1, arraySize + 1);
    }
    return (char *)ptr;
}

// * NORMAL
void __PRINT(char *str) { printf("%s", str); }

void __PRINTLN(char *str) { printf("%s\n", str); }

void __PRINT_INT(int val) { printf("%d", val); }

void __PRINTLN_INT(int val) { printf("%d\n", val); }

char *__GET_STRING() {
    char *str = (char *)malloc(256 + INT_SIZE) + INT_SIZE;
    scanf("%255s", str);
    *(int *)(str - INT_SIZE) = strlen(str);
    return str;
}

int __GET_INT() {
    int val;
    scanf("%d", &val);
    return val;
}

char *__TO_STRING(int val) {
    char *str = (char *)malloc(256 + INT_SIZE) + INT_SIZE;
    // -2147483648
    *(int *)(str - INT_SIZE) = sprintf(str, "%d", val);
    return str;
}

// * STRING
char *__STRING_ADD(char *str1, char *str2) {
    int len = strlen(str1) + strlen(str2);
    char *str = (char *)malloc(len + 1 + INT_SIZE) + INT_SIZE;
    *(int *)(str - INT_SIZE) = len;
    strcpy(str, str1);
    strcat(str, str2);
    return str;
}

char __STRING_EQUAL(char *str1, char *str2) {
    return (strcmp(str1, str2) == 0);
}

char __STRING_NOT_EQUAL(char *str1, char *str2) {
    return (strcmp(str1, str2) != 0);
}

char __STRING_LESS(char *str1, char *str2) { return (strcmp(str1, str2) < 0); }

char __STRING_GREATER(char *str1, char *str2) {
    return (strcmp(str1, str2) > 0);
}

char __STRING_LESS_OR_EQUAL(char *str1, char *str2) {
    return (strcmp(str1, str2) <= 0);
}

char __STRING_GREATER_OR_EQUAL(char *str1, char *str2) {
    return (strcmp(str1, str2) >= 0);
}

char *__STRING_SUBSTRING(char *str, int left, int right) {
    int len = right - left;
    char *substr = (char *)malloc(len + 1 + INT_SIZE) + INT_SIZE;
    *(int *)(substr - INT_SIZE) = len;
    strncpy(substr, str + left, len);
    substr[len] = '\0';
    return substr;
}

int __STRING_PARSE_INT(char *str) {
    char sign = 0;
    int k = 0;
    while ((*str < '0' || *str > '9') && *str != '\0') {
        if (*str == '-') sign = 1;
        str++;
    }
    while (*str >= '0' && *str <= '9') {
        k = k * 10 - 48 + *str;
        str++;
    }
    return (sign ? -k : k);
}

int __STRING_ORD(char *str, int pos) { return (int)str[pos]; }