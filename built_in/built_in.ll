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
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32*, align 4
  %7 = alloca i8*, align 4
  %8 = alloca i32, align 4
  store i32 %0, i32* %4, align 4
  store i32 %1, i32* %5, align 4
  store i32* %2, i32** %6, align 4
  %9 = load i32, i32* %5, align 4
  %10 = icmp eq i32 %9, 1
  br i1 %10, label %11, label %24

11:                                               ; preds = %3
  %12 = load i32, i32* %4, align 4
  %13 = load i32*, i32** %6, align 4
  %14 = load i32, i32* %13, align 4
  %15 = mul nsw i32 %12, %14
  %16 = add i32 %15, 4
  %17 = call noalias i8* @malloc(i32 %16) #4
  %18 = getelementptr i8, i8* %17, i32 4
  store i8* %18, i8** %7, align 4
  %19 = load i32*, i32** %6, align 4
  %20 = load i32, i32* %19, align 4
  %21 = load i8*, i8** %7, align 4
  %22 = getelementptr i8, i8* %21, i32 -4
  %23 = bitcast i8* %22 to i32*
  store i32 %20, i32* %23, align 4
  br label %24

24:                                               ; preds = %11, %3
  %25 = load i32, i32* %5, align 4
  %26 = icmp sgt i32 %25, 1
  br i1 %26, label %27, label %59

27:                                               ; preds = %24
  %28 = load i32*, i32** %6, align 4
  %29 = load i32, i32* %28, align 4
  %30 = mul i32 4, %29
  %31 = add i32 %30, 4
  %32 = call noalias i8* @malloc(i32 %31) #4
  %33 = getelementptr i8, i8* %32, i32 4
  store i8* %33, i8** %7, align 4
  %34 = load i32*, i32** %6, align 4
  %35 = load i32, i32* %34, align 4
  %36 = load i8*, i8** %7, align 4
  %37 = getelementptr i8, i8* %36, i32 -4
  %38 = bitcast i8* %37 to i32*
  store i32 %35, i32* %38, align 4
  store i32 0, i32* %8, align 4
  br label %39

39:                                               ; preds = %55, %27
  %40 = load i32, i32* %8, align 4
  %41 = load i32*, i32** %6, align 4
  %42 = load i32, i32* %41, align 4
  %43 = icmp slt i32 %40, %42
  br i1 %43, label %44, label %58

44:                                               ; preds = %39
  %45 = load i32, i32* %4, align 4
  %46 = load i32, i32* %5, align 4
  %47 = sub nsw i32 %46, 1
  %48 = load i32*, i32** %6, align 4
  %49 = getelementptr inbounds i32, i32* %48, i32 1
  %50 = call i8* @__NEW_ARRAY(i32 %45, i32 %47, i32* %49)
  %51 = load i8*, i8** %7, align 4
  %52 = bitcast i8* %51 to i8**
  %53 = load i32, i32* %8, align 4
  %54 = getelementptr inbounds i8*, i8** %52, i32 %53
  store i8* %50, i8** %54, align 4
  br label %55

55:                                               ; preds = %44
  %56 = load i32, i32* %8, align 4
  %57 = add nsw i32 %56, 1
  store i32 %57, i32* %8, align 4
  br label %39

58:                                               ; preds = %39
  br label %59

59:                                               ; preds = %58, %24
  %60 = load i8*, i8** %7, align 4
  ret i8* %60
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
  %4 = call noalias i8* @malloc(i32 17) #4
  %5 = getelementptr inbounds i8, i8* %4, i32 4
  store i8* %5, i8** %3, align 4
  %6 = load i8*, i8** %3, align 4
  %7 = load i32, i32* %2, align 4
  %8 = call i32 (i8*, i8*, ...) @sprintf(i8* %6, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %7) #4
  %9 = load i8*, i8** %3, align 4
  %10 = call i32 @strlen(i8* %9) #5
  %11 = load i8*, i8** %3, align 4
  %12 = getelementptr inbounds i8, i8* %11, i32 -4
  %13 = bitcast i8* %12 to i32*
  store i32 %10, i32* %13, align 4
  %14 = load i8*, i8** %3, align 4
  ret i8* %14
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
  %17 = load i8*, i8** %6, align 4
  %18 = load i8*, i8** %3, align 4
  %19 = call i8* @strcpy(i8* %17, i8* %18) #4
  %20 = load i8*, i8** %6, align 4
  %21 = load i8*, i8** %4, align 4
  %22 = call i8* @strcat(i8* %20, i8* %21) #4
  %23 = load i32, i32* %5, align 4
  %24 = load i8*, i8** %6, align 4
  %25 = getelementptr inbounds i8, i8* %24, i32 -4
  %26 = bitcast i8* %25 to i32*
  store i32 %23, i32* %26, align 4
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
