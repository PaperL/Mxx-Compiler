; ModuleID = 'built_in/built_in.c'
source_filename = "built_in/built_in.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.4 = private unnamed_addr constant [6 x i8] c"%255s\00", align 1

; Function Attrs: nofree nounwind uwtable
define dso_local noalias i8* @__NEW_ARRAY(i32 %0, i32 %1, i32* nocapture %2) local_unnamed_addr #0 {
  %4 = load i32, i32* %2, align 4, !tbaa !2
  %5 = icmp eq i32 %4, 0
  br i1 %5, label %37, label %6

6:                                                ; preds = %3
  %7 = icmp eq i32 %1, 1
  br i1 %7, label %8, label %15

8:                                                ; preds = %6
  %9 = mul nsw i32 %4, %0
  %10 = sext i32 %9 to i64
  %11 = add nsw i64 %10, 4
  %12 = tail call noalias i8* @malloc(i64 %11) #9
  %13 = getelementptr i8, i8* %12, i64 4
  %14 = bitcast i8* %12 to i32*
  store i32 %4, i32* %14, align 4, !tbaa !2
  br label %37

15:                                               ; preds = %6
  %16 = icmp sgt i32 %1, 1
  br i1 %16, label %17, label %37

17:                                               ; preds = %15
  %18 = sext i32 %4 to i64
  %19 = shl nsw i64 %18, 3
  %20 = or i64 %19, 4
  %21 = tail call noalias i8* @malloc(i64 %20) #9
  %22 = getelementptr i8, i8* %21, i64 4
  %23 = bitcast i8* %21 to i32*
  store i32 %4, i32* %23, align 4, !tbaa !2
  %24 = icmp sgt i32 %4, 0
  br i1 %24, label %25, label %37

25:                                               ; preds = %17
  %26 = add nsw i32 %1, -1
  %27 = getelementptr inbounds i32, i32* %2, i64 1
  %28 = bitcast i8* %22 to i8**
  br label %29

29:                                               ; preds = %25, %29
  %30 = phi i64 [ 0, %25 ], [ %33, %29 ]
  %31 = tail call i8* @__NEW_ARRAY(i32 %0, i32 %26, i32* nonnull %27)
  %32 = getelementptr inbounds i8*, i8** %28, i64 %30
  store i8* %31, i8** %32, align 8, !tbaa !6
  %33 = add nuw nsw i64 %30, 1
  %34 = load i32, i32* %2, align 4, !tbaa !2
  %35 = sext i32 %34 to i64
  %36 = icmp slt i64 %33, %35
  br i1 %36, label %29, label %37

37:                                               ; preds = %29, %17, %8, %15, %3
  %38 = phi i8* [ null, %3 ], [ undef, %15 ], [ %13, %8 ], [ %22, %17 ], [ %22, %29 ]
  ret i8* %38
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.start.p0i8(i64 immarg, i8* nocapture) #1

; Function Attrs: nofree nounwind
declare dso_local noalias i8* @malloc(i64) local_unnamed_addr #2

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.end.p0i8(i64 immarg, i8* nocapture) #1

; Function Attrs: nofree nounwind uwtable
define dso_local void @__PRINT(i8* %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %0)
  ret void
}

; Function Attrs: nofree nounwind
declare dso_local i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #2

; Function Attrs: nofree nounwind uwtable
define dso_local void @__PRINTLN(i8* nocapture readonly %0) local_unnamed_addr #0 {
  %2 = tail call i32 @puts(i8* nonnull dereferenceable(1) %0)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local void @__PRINT_INT(i32 %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %0)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local void @__PRINTLN_INT(i32 %0) local_unnamed_addr #0 {
  %2 = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %0)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local nonnull i8* @__GET_STRING() local_unnamed_addr #0 {
  %1 = tail call noalias dereferenceable_or_null(260) i8* @malloc(i64 260) #9
  %2 = getelementptr inbounds i8, i8* %1, i64 4
  %3 = tail call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.4, i64 0, i64 0), i8* nonnull %2)
  %4 = tail call i64 @strlen(i8* nonnull %2) #10
  %5 = trunc i64 %4 to i32
  %6 = bitcast i8* %1 to i32*
  store i32 %5, i32* %6, align 4, !tbaa !2
  ret i8* %2
}

; Function Attrs: nofree nounwind
declare dso_local i32 @__isoc99_scanf(i8* nocapture readonly, ...) local_unnamed_addr #2

; Function Attrs: argmemonly nofree nounwind readonly
declare dso_local i64 @strlen(i8* nocapture) local_unnamed_addr #3

; Function Attrs: nounwind uwtable
define dso_local i32 @__GET_INT() local_unnamed_addr #4 {
  %1 = alloca i32, align 4
  %2 = bitcast i32* %1 to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %2) #9
  %3 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* nonnull %1)
  %4 = load i32, i32* %1, align 4, !tbaa !2
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %2) #9
  ret i32 %4
}

; Function Attrs: nofree nounwind uwtable
define dso_local noalias nonnull i8* @__TO_STRING(i32 %0) local_unnamed_addr #0 {
  %2 = tail call noalias dereferenceable_or_null(260) i8* @malloc(i64 260) #9
  %3 = getelementptr inbounds i8, i8* %2, i64 4
  %4 = tail call i32 (i8*, i8*, ...) @sprintf(i8* nonnull %3, i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %0) #9
  %5 = bitcast i8* %2 to i32*
  store i32 %4, i32* %5, align 4, !tbaa !2
  ret i8* %3
}

; Function Attrs: nofree nounwind
declare dso_local i32 @sprintf(i8* noalias nocapture, i8* nocapture readonly, ...) local_unnamed_addr #2

; Function Attrs: nofree nounwind uwtable
define dso_local nonnull i8* @__STRING_ADD(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #0 {
  %3 = tail call i64 @strlen(i8* nonnull dereferenceable(1) %0) #10
  %4 = tail call i64 @strlen(i8* nonnull dereferenceable(1) %1) #10
  %5 = add i64 %4, %3
  %6 = trunc i64 %5 to i32
  %7 = shl i64 %5, 32
  %8 = add i64 %7, 4294967296
  %9 = ashr exact i64 %8, 32
  %10 = add nsw i64 %9, 4
  %11 = tail call noalias i8* @malloc(i64 %10) #9
  %12 = getelementptr inbounds i8, i8* %11, i64 4
  %13 = bitcast i8* %11 to i32*
  store i32 %6, i32* %13, align 4, !tbaa !2
  %14 = tail call i8* @strcpy(i8* nonnull %12, i8* nonnull dereferenceable(1) %0) #9
  %15 = tail call i8* @strcat(i8* nonnull %12, i8* nonnull dereferenceable(1) %1) #9
  ret i8* %12
}

; Function Attrs: nofree nounwind
declare dso_local i8* @strcpy(i8* noalias returned, i8* noalias nocapture readonly) local_unnamed_addr #2

; Function Attrs: nofree nounwind
declare dso_local i8* @strcat(i8* returned, i8* nocapture readonly) local_unnamed_addr #2

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @__STRING_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #5 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp eq i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nofree nounwind readonly
declare dso_local i32 @strcmp(i8* nocapture, i8* nocapture) local_unnamed_addr #6

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @__STRING_NOT_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #5 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp ne i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @__STRING_LESS(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #5 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = lshr i32 %3, 31
  %5 = trunc i32 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @__STRING_GREATER(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #5 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp sgt i32 %3, 0
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @__STRING_LESS_OR_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #5 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = icmp slt i32 %3, 1
  %5 = zext i1 %4 to i8
  ret i8 %5
}

; Function Attrs: nounwind readonly uwtable
define dso_local signext i8 @__STRING_GREATER_OR_EQUAL(i8* nocapture readonly %0, i8* nocapture readonly %1) local_unnamed_addr #5 {
  %3 = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %0, i8* nonnull dereferenceable(1) %1) #10
  %4 = lshr i32 %3, 31
  %5 = trunc i32 %4 to i8
  %6 = xor i8 %5, 1
  ret i8 %6
}

; Function Attrs: nounwind uwtable
define dso_local noalias nonnull i8* @__STRING_SUBSTRING(i8* nocapture readonly %0, i32 %1, i32 %2) local_unnamed_addr #4 {
  %4 = sub nsw i32 %2, %1
  %5 = sext i32 %4 to i64
  %6 = add nsw i64 %5, 5
  %7 = tail call noalias i8* @malloc(i64 %6) #9
  %8 = getelementptr inbounds i8, i8* %7, i64 4
  %9 = bitcast i8* %7 to i32*
  store i32 %4, i32* %9, align 4, !tbaa !2
  %10 = sext i32 %1 to i64
  %11 = getelementptr inbounds i8, i8* %0, i64 %10
  tail call void @llvm.memcpy.p0i8.p0i8.i64(i8* nonnull align 1 %8, i8* align 1 %11, i64 %5, i1 false)
  %12 = getelementptr inbounds i8, i8* %8, i64 %5
  store i8 0, i8* %12, align 1, !tbaa !8
  ret i8* %8
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #1

; Function Attrs: norecurse nounwind readonly uwtable
define dso_local i32 @__STRING_PARSE_INT(i8* nocapture readonly %0) local_unnamed_addr #7 {
  %2 = load i8, i8* %0, align 1, !tbaa !8
  %3 = add i8 %2, -48
  %4 = icmp ugt i8 %3, 9
  br i1 %4, label %5, label %19

5:                                                ; preds = %1, %10
  %6 = phi i8 [ %13, %10 ], [ %2, %1 ]
  %7 = phi i8 [ %11, %10 ], [ 0, %1 ]
  %8 = phi i8* [ %12, %10 ], [ %0, %1 ]
  switch i8 %6, label %10 [
    i8 0, label %35
    i8 45, label %9
  ]

9:                                                ; preds = %5
  br label %10

10:                                               ; preds = %5, %9
  %11 = phi i8 [ 1, %9 ], [ %7, %5 ]
  %12 = getelementptr inbounds i8, i8* %8, i64 1
  %13 = load i8, i8* %12, align 1, !tbaa !8
  %14 = add i8 %13, -48
  %15 = icmp ugt i8 %14, 9
  br i1 %15, label %5, label %16

16:                                               ; preds = %10
  %17 = add nsw i8 %13, -48
  %18 = icmp ult i8 %17, 10
  br i1 %18, label %19, label %35

19:                                               ; preds = %1, %16
  %20 = phi i8 [ %11, %16 ], [ 0, %1 ]
  %21 = phi i8* [ %12, %16 ], [ %0, %1 ]
  %22 = phi i8 [ %13, %16 ], [ %2, %1 ]
  br label %23

23:                                               ; preds = %19, %23
  %24 = phi i8 [ %32, %23 ], [ %22, %19 ]
  %25 = phi i32 [ %30, %23 ], [ 0, %19 ]
  %26 = phi i8* [ %31, %23 ], [ %21, %19 ]
  %27 = zext i8 %24 to i32
  %28 = mul nsw i32 %25, 10
  %29 = add nsw i32 %28, -48
  %30 = add nsw i32 %29, %27
  %31 = getelementptr inbounds i8, i8* %26, i64 1
  %32 = load i8, i8* %31, align 1, !tbaa !8
  %33 = add i8 %32, -48
  %34 = icmp ult i8 %33, 10
  br i1 %34, label %23, label %35

35:                                               ; preds = %5, %23, %16
  %36 = phi i8 [ %11, %16 ], [ %20, %23 ], [ %7, %5 ]
  %37 = phi i32 [ 0, %16 ], [ %30, %23 ], [ 0, %5 ]
  %38 = icmp eq i8 %36, 0
  %39 = sub nsw i32 0, %37
  %40 = select i1 %38, i32 %37, i32 %39
  ret i32 %40
}

; Function Attrs: norecurse nounwind readonly uwtable
define dso_local i32 @__STRING_ORD(i8* nocapture readonly %0, i32 %1) local_unnamed_addr #7 {
  %3 = sext i32 %1 to i64
  %4 = getelementptr inbounds i8, i8* %0, i64 %3
  %5 = load i8, i8* %4, align 1, !tbaa !8
  %6 = sext i8 %5 to i32
  ret i32 %6
}

; Function Attrs: nofree nounwind
declare i32 @puts(i8* nocapture readonly) local_unnamed_addr #8

attributes #0 = { nofree nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { argmemonly nounwind willreturn }
attributes #2 = { nofree nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { argmemonly nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { nounwind readonly uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #6 = { nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #7 = { norecurse nounwind readonly uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #8 = { nofree nounwind }
attributes #9 = { nounwind }
attributes #10 = { nounwind readonly }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
!2 = !{!3, !3, i64 0}
!3 = !{!"int", !4, i64 0}
!4 = !{!"omnipotent char", !5, i64 0}
!5 = !{!"Simple C/C++ TBAA"}
!6 = !{!7, !7, i64 0}
!7 = !{!"any pointer", !4, i64 0}
!8 = !{!4, !4, i64 0}
