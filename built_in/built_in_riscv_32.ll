; ModuleID = 'built_in.c'
source_filename = "built_in.c"
target datalayout = "e-m:e-p:32:32-i64:64-n32-S128"
target triple = "riscv32-unknown-unknown-elf"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.4 = private unnamed_addr constant [6 x i8] c"%255s\00", align 1

; Function Attrs: noinline nounwind optnone
define dso_local i8* @__NEW_ARRAY(i32 %0, i32 %1, i32* %2) #0 {
  %4 = alloca i8*, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32*, align 4
  %8 = alloca i8*, align 4
  %9 = alloca i32, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store i32* %2, i32** %7, align 4
  %10 = load i32*, i32** %7, align 4
  %11 = load i32, i32* %10, align 4
  %12 = icmp eq i32 %11, 0
  br i1 %12, label %13, label %14

13:                                               ; preds = %3
  store i8* null, i8** %4, align 4
  br label %67

14:                                               ; preds = %3
  %15 = load i32, i32* %6, align 4
  %16 = icmp eq i32 %15, 1
  br i1 %16, label %17, label %30

17:                                               ; preds = %14
  %18 = load i32, i32* %5, align 4
  %19 = load i32*, i32** %7, align 4
  %20 = load i32, i32* %19, align 4
  %21 = mul nsw i32 %18, %20
  %22 = add i32 %21, 4
  %23 = call noalias i8* @malloc(i32 %22) #4
  %24 = getelementptr i8, i8* %23, i32 4
  store i8* %24, i8** %8, align 4
  %25 = load i32*, i32** %7, align 4
  %26 = load i32, i32* %25, align 4
  %27 = load i8*, i8** %8, align 4
  %28 = getelementptr i8, i8* %27, i32 -4
  %29 = bitcast i8* %28 to i32*
  store i32 %26, i32* %29, align 4
  br label %30

30:                                               ; preds = %17, %14
  %31 = load i32, i32* %6, align 4
  %32 = icmp sgt i32 %31, 1
  br i1 %32, label %33, label %65

33:                                               ; preds = %30
  %34 = load i32*, i32** %7, align 4
  %35 = load i32, i32* %34, align 4
  %36 = mul i32 4, %35
  %37 = add i32 %36, 4
  %38 = call noalias i8* @malloc(i32 %37) #4
  %39 = getelementptr i8, i8* %38, i32 4
  store i8* %39, i8** %8, align 4
  %40 = load i32*, i32** %7, align 4
  %41 = load i32, i32* %40, align 4
  %42 = load i8*, i8** %8, align 4
  %43 = getelementptr i8, i8* %42, i32 -4
  %44 = bitcast i8* %43 to i32*
  store i32 %41, i32* %44, align 4
  store i32 0, i32* %9, align 4
  br label %45

45:                                               ; preds = %61, %33
  %46 = load i32, i32* %9, align 4
  %47 = load i32*, i32** %7, align 4
  %48 = load i32, i32* %47, align 4
  %49 = icmp slt i32 %46, %48
  br i1 %49, label %50, label %64

50:                                               ; preds = %45
  %51 = load i32, i32* %5, align 4
  %52 = load i32, i32* %6, align 4
  %53 = sub nsw i32 %52, 1
  %54 = load i32*, i32** %7, align 4
  %55 = getelementptr inbounds i32, i32* %54, i32 1
  %56 = call i8* @__NEW_ARRAY(i32 %51, i32 %53, i32* %55)
  %57 = load i8*, i8** %8, align 4
  %58 = bitcast i8* %57 to i8**
  %59 = load i32, i32* %9, align 4
  %60 = getelementptr inbounds i8*, i8** %58, i32 %59
  store i8* %56, i8** %60, align 4
  br label %61

61:                                               ; preds = %50
  %62 = load i32, i32* %9, align 4
  %63 = add nsw i32 %62, 1
  store i32 %63, i32* %9, align 4
  br label %45

64:                                               ; preds = %45
  br label %65

65:                                               ; preds = %64, %30
  %66 = load i8*, i8** %8, align 4
  store i8* %66, i8** %4, align 4
  br label %67

67:                                               ; preds = %65, %13
  %68 = load i8*, i8** %4, align 4
  ret i8* %68
}

; Function Attrs: nounwind
declare dso_local noalias i8* @malloc(i32) #1

; Function Attrs: noinline nounwind optnone
define dso_local void @__PRINT(i8* %0) #0 {
  %2 = alloca i8*, align 4
  store i8* %0, i8** %2, align 4
  %3 = load i8*, i8** %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8* %3)
  ret void
}

declare dso_local i32 @printf(i8*, ...) #2

; Function Attrs: noinline nounwind optnone
define dso_local void @__PRINTLN(i8* %0) #0 {
  %2 = alloca i8*, align 4
  store i8* %0, i8** %2, align 4
  %3 = load i8*, i8** %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i32 0, i32 0), i8* %3)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @__PRINT_INT(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @__PRINTLN_INT(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i32 0, i32 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @__GET_STRING() #0 {
  %1 = alloca i8*, align 4
  %2 = call noalias i8* @malloc(i32 260) #4
  %3 = getelementptr inbounds i8, i8* %2, i32 4
  store i8* %3, i8** %1, align 4
  %4 = load i8*, i8** %1, align 4
  %5 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.4, i32 0, i32 0), i8* %4)
  %6 = load i8*, i8** %1, align 4
  %7 = call i32 @strlen(i8* %6) #5
  %8 = load i8*, i8** %1, align 4
  %9 = getelementptr inbounds i8, i8* %8, i32 -4
  %10 = bitcast i8* %9 to i32*
  store i32 %7, i32* %10, align 4
  %11 = load i8*, i8** %1, align 4
  ret i8* %11
}

declare dso_local i32 @__isoc99_scanf(i8*, ...) #2

; Function Attrs: nounwind readonly
declare dso_local i32 @strlen(i8*) #3

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__GET_INT() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @__TO_STRING(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i8*, align 4
  store i32 %0, i32* %2, align 4
  %4 = call noalias i8* @malloc(i32 260) #4
  %5 = getelementptr inbounds i8, i8* %4, i32 4
  store i8* %5, i8** %3, align 4
  %6 = load i8*, i8** %3, align 4
  %7 = load i32, i32* %2, align 4
  %8 = call i32 (i8*, i8*, ...) @sprintf(i8* %6, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %7) #4
  %9 = load i8*, i8** %3, align 4
  %10 = getelementptr inbounds i8, i8* %9, i32 -4
  %11 = bitcast i8* %10 to i32*
  store i32 %8, i32* %11, align 4
  %12 = load i8*, i8** %3, align 4
  ret i8* %12
}

; Function Attrs: nounwind
declare dso_local i32 @sprintf(i8*, i8*, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local i8* @__STRING_ADD(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  %5 = alloca i32, align 4
  %6 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %7 = load i8*, i8** %3, align 4
  %8 = call i32 @strlen(i8* %7) #5
  %9 = load i8*, i8** %4, align 4
  %10 = call i32 @strlen(i8* %9) #5
  %11 = add i32 %8, %10
  store i32 %11, i32* %5, align 4
  %12 = load i32, i32* %5, align 4
  %13 = add nsw i32 %12, 1
  %14 = add i32 %13, 4
  %15 = call noalias i8* @malloc(i32 %14) #4
  %16 = getelementptr inbounds i8, i8* %15, i32 4
  store i8* %16, i8** %6, align 4
  %17 = load i32, i32* %5, align 4
  %18 = load i8*, i8** %6, align 4
  %19 = getelementptr inbounds i8, i8* %18, i32 -4
  %20 = bitcast i8* %19 to i32*
  store i32 %17, i32* %20, align 4
  %21 = load i8*, i8** %6, align 4
  %22 = load i8*, i8** %3, align 4
  %23 = call i8* @strcpy(i8* %21, i8* %22) #4
  %24 = load i8*, i8** %6, align 4
  %25 = load i8*, i8** %4, align 4
  %26 = call i8* @strcat(i8* %24, i8* %25) #4
  %27 = load i8*, i8** %6, align 4
  ret i8* %27
}

; Function Attrs: nounwind
declare dso_local i8* @strcpy(i8*, i8*) #1

; Function Attrs: nounwind
declare dso_local i8* @strcat(i8*, i8*) #1

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @__STRING_EQUAL(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp eq i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: nounwind readonly
declare dso_local i32 @strcmp(i8*, i8*) #3

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @__STRING_NOT_EQUAL(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp ne i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @__STRING_LESS(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp slt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @__STRING_GREATER(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp sgt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @__STRING_LESS_OR_EQUAL(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp sle i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @__STRING_GREATER_OR_EQUAL(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* %5, i8* %6) #5
  %8 = icmp sge i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @__STRING_SUBSTRING(i8* %0, i32 %1, i32 %2) #0 {
  %4 = alloca i8*, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca i8*, align 4
  store i8* %0, i8** %4, align 4
  store i32 %1, i32* %5, align 4
  store i32 %2, i32* %6, align 4
  %9 = load i32, i32* %6, align 4
  %10 = load i32, i32* %5, align 4
  %11 = sub nsw i32 %9, %10
  store i32 %11, i32* %7, align 4
  %12 = load i32, i32* %7, align 4
  %13 = add nsw i32 %12, 1
  %14 = add i32 %13, 4
  %15 = call noalias i8* @malloc(i32 %14) #4
  %16 = getelementptr inbounds i8, i8* %15, i32 4
  store i8* %16, i8** %8, align 4
  %17 = load i32, i32* %7, align 4
  %18 = load i8*, i8** %8, align 4
  %19 = getelementptr inbounds i8, i8* %18, i32 -4
  %20 = bitcast i8* %19 to i32*
  store i32 %17, i32* %20, align 4
  %21 = load i8*, i8** %8, align 4
  %22 = load i8*, i8** %4, align 4
  %23 = load i32, i32* %5, align 4
  %24 = getelementptr inbounds i8, i8* %22, i32 %23
  %25 = load i32, i32* %7, align 4
  %26 = call i8* @strncpy(i8* %21, i8* %24, i32 %25) #4
  %27 = load i8*, i8** %8, align 4
  %28 = load i32, i32* %7, align 4
  %29 = getelementptr inbounds i8, i8* %27, i32 %28
  store i8 0, i8* %29, align 1
  %30 = load i8*, i8** %8, align 4
  ret i8* %30
}

; Function Attrs: nounwind
declare dso_local i8* @strncpy(i8*, i8*, i32) #1

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__STRING_PARSE_INT(i8* %0) #0 {
  %2 = alloca i8*, align 4
  %3 = alloca i8, align 1
  %4 = alloca i32, align 4
  store i8* %0, i8** %2, align 4
  store i8 0, i8* %3, align 1
  store i32 0, i32* %4, align 4
  br label %5

5:                                                ; preds = %28, %1
  %6 = load i8*, i8** %2, align 4
  %7 = load i8, i8* %6, align 1
  %8 = zext i8 %7 to i32
  %9 = icmp slt i32 %8, 48
  br i1 %9, label %15, label %10

10:                                               ; preds = %5
  %11 = load i8*, i8** %2, align 4
  %12 = load i8, i8* %11, align 1
  %13 = zext i8 %12 to i32
  %14 = icmp sgt i32 %13, 57
  br i1 %14, label %15, label %20

15:                                               ; preds = %10, %5
  %16 = load i8*, i8** %2, align 4
  %17 = load i8, i8* %16, align 1
  %18 = zext i8 %17 to i32
  %19 = icmp ne i32 %18, 0
  br label %20

20:                                               ; preds = %15, %10
  %21 = phi i1 [ false, %10 ], [ %19, %15 ]
  br i1 %21, label %22, label %31

22:                                               ; preds = %20
  %23 = load i8*, i8** %2, align 4
  %24 = load i8, i8* %23, align 1
  %25 = zext i8 %24 to i32
  %26 = icmp eq i32 %25, 45
  br i1 %26, label %27, label %28

27:                                               ; preds = %22
  store i8 1, i8* %3, align 1
  br label %28

28:                                               ; preds = %27, %22
  %29 = load i8*, i8** %2, align 4
  %30 = getelementptr inbounds i8, i8* %29, i32 1
  store i8* %30, i8** %2, align 4
  br label %5

31:                                               ; preds = %20
  br label %32

32:                                               ; preds = %44, %31
  %33 = load i8*, i8** %2, align 4
  %34 = load i8, i8* %33, align 1
  %35 = zext i8 %34 to i32
  %36 = icmp sge i32 %35, 48
  br i1 %36, label %37, label %42

37:                                               ; preds = %32
  %38 = load i8*, i8** %2, align 4
  %39 = load i8, i8* %38, align 1
  %40 = zext i8 %39 to i32
  %41 = icmp sle i32 %40, 57
  br label %42

42:                                               ; preds = %37, %32
  %43 = phi i1 [ false, %32 ], [ %41, %37 ]
  br i1 %43, label %44, label %54

44:                                               ; preds = %42
  %45 = load i32, i32* %4, align 4
  %46 = mul nsw i32 %45, 10
  %47 = sub nsw i32 %46, 48
  %48 = load i8*, i8** %2, align 4
  %49 = load i8, i8* %48, align 1
  %50 = zext i8 %49 to i32
  %51 = add nsw i32 %47, %50
  store i32 %51, i32* %4, align 4
  %52 = load i8*, i8** %2, align 4
  %53 = getelementptr inbounds i8, i8* %52, i32 1
  store i8* %53, i8** %2, align 4
  br label %32

54:                                               ; preds = %42
  %55 = load i8, i8* %3, align 1
  %56 = zext i8 %55 to i32
  %57 = icmp ne i32 %56, 0
  br i1 %57, label %58, label %61

58:                                               ; preds = %54
  %59 = load i32, i32* %4, align 4
  %60 = sub nsw i32 0, %59
  br label %63

61:                                               ; preds = %54
  %62 = load i32, i32* %4, align 4
  br label %63

63:                                               ; preds = %61, %58
  %64 = phi i32 [ %60, %58 ], [ %62, %61 ]
  ret i32 %64
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__STRING_ORD(i8* %0, i32 %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i32, align 4
  store i8* %0, i8** %3, align 4
  store i32 %1, i32* %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i32, i32* %4, align 4
  %7 = getelementptr inbounds i8, i8* %5, i32 %6
  %8 = load i8, i8* %7, align 1
  %9 = zext i8 %8 to i32
  ret i32 %9
}

attributes #0 = { noinline nounwind optnone "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind }
attributes #5 = { nounwind readonly }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 1, !"target-abi", !"ilp32"}
!2 = !{!"clang version 10.0.0-4ubuntu1 "}
