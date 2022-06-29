; ModuleID = 'built_in.c'
source_filename = "built_in.c"
target datalayout = "e-m:e-p:32:32-i64:64-n32-S128"
target triple = "riscv32-unknown-unknown-elf"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.4 = private unnamed_addr constant [6 x i8] c"%255s\00", align 1

; Function Attrs: nofree nounwind
define dso_local noalias i8* @__NEW_ON_HEAP(i32 %0) local_unnamed_addr #0 {
  %2 = tail call noalias i8* @malloc(i32 %0) #9
  ret i8* %2
}

; Function Attrs: nofree nounwind
declare dso_local noalias i8* @malloc(i32) local_unnamed_addr #1

; Function Attrs: nofree nounwind
define dso_local noalias i8* @__NEW_ARRAY(i32 %0, i32 %1, i32* nocapture %2) local_unnamed_addr #0 {
  %4 = icmp eq i32 %1, 1
  br i1 %4, label %5, label %12

5:                                                ; preds = %3
  %6 = load i32, i32* %2, align 4, !tbaa !3
  %7 = mul nsw i32 %6, %0
  %8 = add i32 %7, 4
  %9 = tail call noalias i8* @malloc(i32 %8) #9
  %10 = getelementptr i8, i8* %9, i32 4
  %11 = bitcast i8* %9 to i32*
  store i32 %6, i32* %11, align 4, !tbaa !3
  br label %33

12:                                               ; preds = %3
  %13 = icmp sgt i32 %1, 1
  br i1 %13, label %14, label %33

14:                                               ; preds = %12
  %15 = load i32, i32* %2, align 4, !tbaa !3
  %16 = shl i32 %15, 2
  %17 = add i32 %16, 4
  %18 = tail call noalias i8* @malloc(i32 %17) #9
  %19 = getelementptr i8, i8* %18, i32 4
  %20 = bitcast i8* %18 to i32*
  store i32 %15, i32* %20, align 4, !tbaa !3
  %21 = icmp sgt i32 %15, 0
  br i1 %21, label %22, label %33

22:                                               ; preds = %14
  %23 = add nsw i32 %1, -1
  %24 = getelementptr inbounds i32, i32* %2, i32 1
  %25 = bitcast i8* %19 to i8**
  br label %26

26:                                               ; preds = %22, %26
  %27 = phi i32 [ 0, %22 ], [ %30, %26 ]
  %28 = tail call i8* @__NEW_ARRAY(i32 %0, i32 %23, i32* nonnull %24)
  %29 = getelementptr inbounds i8*, i8** %25, i32 %27
  store i8* %28, i8** %29, align 4, !tbaa !7
  %30 = add nuw nsw i32 %27, 1
  %31 = load i32, i32* %2, align 4, !tbaa !3
  %32 = icmp slt i32 %30, %31
  br i1 %32, label %26, label %33

33:                                               ; preds = %26, %14, %5, %12
  %34 = phi i8* [ undef, %12 ], [ %10, %5 ], [ %19, %14 ], [ %19, %26 ]
  ret i8* %34
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.start.p0i8(i64 immarg, i8* nocapture) #2

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.end.p0i8(i64 immarg, i8* nocapture) #2

; Function Attrs: nofree nounwind
define dso_local void @__PRINT(i8* %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8* %0)
  ret void
}

; Function Attrs: nofree nounwind
declare dso_local i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nofree nounwind
define dso_local void @__PRINTLN(i8* nocapture readonly %0) local_unnamed_addr #0 {
  %2 = tail call i32 @puts(i8* nonnull dereferenceable(1) %0)
  ret void
}

; Function Attrs: nofree nounwind
define dso_local void @__PRINT_INT(i32 %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %0)
  ret void
}

; Function Attrs: nofree nounwind
define dso_local void @__PRINTLN_INT(i32 %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i32 0, i32 0), i32 %0)
  ret void
}

; Function Attrs: nounwind
define dso_local nonnull i8* @__GET_STRING() local_unnamed_addr #3 {
  %1 = tail call noalias dereferenceable_or_null(260) i8* @malloc(i32 260) #9
  %2 = getelementptr inbounds i8, i8* %1, i32 4
  %3 = tail call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.4, i32 0, i32 0), i8* nonnull %2) #9
  %4 = tail call i32 @strlen(i8* nonnull %2) #10
  %5 = bitcast i8* %1 to i32*
  store i32 %4, i32* %5, align 4, !tbaa !3
  ret i8* %2
}

declare dso_local i32 @__isoc99_scanf(i8*, ...) local_unnamed_addr #4

; Function Attrs: argmemonly nofree nounwind readonly
declare dso_local i32 @strlen(i8* nocapture) local_unnamed_addr #5

; Function Attrs: nounwind
define dso_local i32 @__GET_INT() local_unnamed_addr #3 {
  %1 = alloca i32, align 4
  %2 = bitcast i32* %1 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %2) #9
  %3 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32* nonnull %1) #9
  %4 = load i32, i32* %1, align 4, !tbaa !3
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %2) #9
  ret i32 %4
}

; Function Attrs: nofree nounwind
define dso_local noalias nonnull i8* @__TO_STRING(i32 %0) local_unnamed_addr #0 {
  %2 = tail call noalias dereferenceable_or_null(17) i8* @malloc(i32 17) #9
  %3 = getelementptr inbounds i8, i8* %2, i32 4
  %4 = tail call i32 (i8*, i8*, ...) @sprintf(i8* nonnull %3, i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %0) #9
  %5 = tail call i32 @strlen(i8* nonnull %3) #10
  %6 = bitcast i8* %2 to i32*
  store i32 %5, i32* %6, align 4, !tbaa !3
  ret i8* %3
}

; Function Attrs: nofree nounwind
declare dso_local i32 @sprintf(i8* noalias nocapture, i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nofree nounwind
define dso_local nonnull i8* @__STRING_ADD(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #0 {
  %3 = tail call i32 @strlen(i8* nonnull dereferenceable(1) %0) #10
  %4 = tail call i32 @strlen(i8* nonnull dereferenceable(1) %1) #10
  %5 = add i32 %4, %3
  %6 = add i32 %5, 5
  %7 = tail call noalias i8* @malloc(i32 %6) #9
  %8 = getelementptr inbounds i8, i8* %7, i32 4
  %9 = tail call i8* @strcpy(i8* nonnull %8, i8* nonnull dereferenceable(1) %0) #9
  %10 = tail call i8* @strcat(i8* nonnull %8, i8* nonnull dereferenceable(1) %1) #9
  %11 = bitcast i8* %7 to i32*
  store i32 %5, i32* %11, align 4, !tbaa !3
  ret i8* %8
}

; Function Attrs: nofree nounwind
declare dso_local i8* @strcpy(i8* noalias returned, i8* noalias nocapture readonly) local_unnamed_addr #1

; Function Attrs: nofree nounwind
declare dso_local i8* @strcat(i8* returned, i8* nocapture readonly) local_unnamed_addr #1

; Function Attrs: nounwind readonly
define dso_local zeroext i8 @__STRING_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #6 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp eq i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nofree nounwind readonly
declare dso_local i32 @strcmp(i8* nocapture, i8* nocapture) local_unnamed_addr #7

; Function Attrs: nounwind readonly
define dso_local zeroext i8 @__STRING_NOT_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #6 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp ne i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly
define dso_local zeroext i8 @__STRING_LESS(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #6 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = lshr i32 %3, 31
  %5 = trunc i32 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly
define dso_local zeroext i8 @__STRING_GREATER(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #6 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp sgt i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly
define dso_local zeroext i8 @__STRING_LESS_OR_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #6 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp slt i32 %3, 1
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly
define dso_local zeroext i8 @__STRING_GREATER_OR_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #6 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = lshr i32 %3, 31
  %5 = trunc i32 %4 to i8
  %6 = xor i8 %5, 1
  ret i8 %6
}

; Function Attrs: nofree nounwind
declare i32 @puts(i8* nocapture readonly) local_unnamed_addr #8

attributes #0 = { nofree nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nofree nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { argmemonly nounwind willreturn }
attributes #3 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { argmemonly nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #6 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #7 = { nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m,+relax" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #8 = { nofree nounwind }
attributes #9 = { nounwind }
attributes #10 = { nounwind readonly }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 1, !"target-abi", !"ilp32"}
!2 = !{!"clang version 10.0.0-4ubuntu1 "}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
!7 = !{!8, !8, i64 0}
!8 = !{!"any pointer", !5, i64 0}
