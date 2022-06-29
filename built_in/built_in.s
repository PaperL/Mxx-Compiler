	.text
	.file	"built_in.c"
	.globl	__NEW_ON_HEAP           # -- Begin function __NEW_ON_HEAP
	.p2align	1
	.type	__NEW_ON_HEAP,@function
__NEW_ON_HEAP:                          # @__NEW_ON_HEAP
# %bb.0:
	tail	malloc
.Lfunc_end0:
	.size	__NEW_ON_HEAP, .Lfunc_end0-__NEW_ON_HEAP
                                        # -- End function
	.globl	__NEW_ARRAY             # -- Begin function __NEW_ARRAY
	.p2align	1
	.type	__NEW_ARRAY,@function
__NEW_ARRAY:                            # @__NEW_ARRAY
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	sw	s2, 16(sp)
	sw	s3, 12(sp)
	sw	s4, 8(sp)
	sw	s5, 4(sp)
	sw	s6, 0(sp)
	addi	a3, zero, 1
	mv	s5, a2
	mv	s4, a0
	bne	a1, a3, .LBB1_2
# %bb.1:
	lw	s0, 0(s5)
	mul	a0, s0, s4
	addi	a0, a0, 4
	call	malloc
	addi	s2, a0, 4
	sw	s0, 0(a0)
	j	.LBB1_6
.LBB1_2:
	mv	s3, a1
	addi	a0, zero, 2
                                        # implicit-def: $x18
	blt	a1, a0, .LBB1_6
# %bb.3:
	lw	s0, 0(s5)
	slli	a0, s0, 2
	addi	a0, a0, 4
	call	malloc
	addi	s2, a0, 4
	addi	a1, zero, 1
	sw	s0, 0(a0)
	blt	s0, a1, .LBB1_6
# %bb.4:
	mv	s0, zero
	addi	s3, s3, -1
	addi	s6, s5, 4
	mv	s1, s2
.LBB1_5:                                # =>This Inner Loop Header: Depth=1
	mv	a0, s4
	mv	a1, s3
	mv	a2, s6
	call	__NEW_ARRAY
	sw	a0, 0(s1)
	lw	a0, 0(s5)
	addi	s0, s0, 1
	addi	s1, s1, 4
	blt	s0, a0, .LBB1_5
.LBB1_6:
	mv	a0, s2
	lw	s6, 0(sp)
	lw	s5, 4(sp)
	lw	s4, 8(sp)
	lw	s3, 12(sp)
	lw	s2, 16(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end1:
	.size	__NEW_ARRAY, .Lfunc_end1-__NEW_ARRAY
                                        # -- End function
	.globl	__PRINT                 # -- Begin function __PRINT
	.p2align	1
	.type	__PRINT,@function
__PRINT:                                # @__PRINT
# %bb.0:
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end2:
	.size	__PRINT, .Lfunc_end2-__PRINT
                                        # -- End function
	.globl	__PRINTLN               # -- Begin function __PRINTLN
	.p2align	1
	.type	__PRINTLN,@function
__PRINTLN:                              # @__PRINTLN
# %bb.0:
	tail	puts
.Lfunc_end3:
	.size	__PRINTLN, .Lfunc_end3-__PRINTLN
                                        # -- End function
	.globl	__PRINT_INT             # -- Begin function __PRINT_INT
	.p2align	1
	.type	__PRINT_INT,@function
__PRINT_INT:                            # @__PRINT_INT
# %bb.0:
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end4:
	.size	__PRINT_INT, .Lfunc_end4-__PRINT_INT
                                        # -- End function
	.globl	__PRINTLN_INT           # -- Begin function __PRINTLN_INT
	.p2align	1
	.type	__PRINTLN_INT,@function
__PRINTLN_INT:                          # @__PRINTLN_INT
# %bb.0:
	lui	a1, %hi(.L.str.3)
	addi	a1, a1, %lo(.L.str.3)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end5:
	.size	__PRINTLN_INT, .Lfunc_end5-__PRINTLN_INT
                                        # -- End function
	.globl	__GET_STRING            # -- Begin function __GET_STRING
	.p2align	1
	.type	__GET_STRING,@function
__GET_STRING:                           # @__GET_STRING
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	sw	s1, 4(sp)
	addi	a0, zero, 260
	call	malloc
	mv	s0, a0
	addi	s1, a0, 4
	lui	a0, %hi(.L.str.4)
	addi	a0, a0, %lo(.L.str.4)
	mv	a1, s1
	call	__isoc99_scanf
	mv	a0, s1
	call	strlen
	sw	a0, 0(s0)
	mv	a0, s1
	lw	s1, 4(sp)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	__GET_STRING, .Lfunc_end6-__GET_STRING
                                        # -- End function
	.globl	__GET_INT               # -- Begin function __GET_INT
	.p2align	1
	.type	__GET_INT,@function
__GET_INT:                              # @__GET_INT
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	__GET_INT, .Lfunc_end7-__GET_INT
                                        # -- End function
	.globl	__TO_STRING             # -- Begin function __TO_STRING
	.p2align	1
	.type	__TO_STRING,@function
__TO_STRING:                            # @__TO_STRING
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	sw	s1, 4(sp)
	sw	s2, 0(sp)
	mv	s2, a0
	addi	a0, zero, 17
	call	malloc
	mv	s1, a0
	addi	s0, a0, 4
	lui	a0, %hi(.L.str.2)
	addi	a1, a0, %lo(.L.str.2)
	mv	a0, s0
	mv	a2, s2
	call	sprintf
	mv	a0, s0
	call	strlen
	sw	a0, 0(s1)
	mv	a0, s0
	lw	s2, 0(sp)
	lw	s1, 4(sp)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end8:
	.size	__TO_STRING, .Lfunc_end8-__TO_STRING
                                        # -- End function
	.globl	__STRING_ADD            # -- Begin function __STRING_ADD
	.p2align	1
	.type	__STRING_ADD,@function
__STRING_ADD:                           # @__STRING_ADD
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	sw	s2, 16(sp)
	sw	s3, 12(sp)
	sw	s4, 8(sp)
	mv	s2, a1
	mv	s3, a0
	call	strlen
	mv	s0, a0
	mv	a0, s2
	call	strlen
	add	s4, a0, s0
	addi	a0, s4, 5
	call	malloc
	mv	s0, a0
	addi	s1, a0, 4
	mv	a0, s1
	mv	a1, s3
	call	strcpy
	mv	a0, s1
	mv	a1, s2
	call	strcat
	sw	s4, 0(s0)
	mv	a0, s1
	lw	s4, 8(sp)
	lw	s3, 12(sp)
	lw	s2, 16(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end9:
	.size	__STRING_ADD, .Lfunc_end9-__STRING_ADD
                                        # -- End function
	.globl	__STRING_EQUAL          # -- Begin function __STRING_EQUAL
	.p2align	1
	.type	__STRING_EQUAL,@function
__STRING_EQUAL:                         # @__STRING_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	seqz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end10:
	.size	__STRING_EQUAL, .Lfunc_end10-__STRING_EQUAL
                                        # -- End function
	.globl	__STRING_NOT_EQUAL      # -- Begin function __STRING_NOT_EQUAL
	.p2align	1
	.type	__STRING_NOT_EQUAL,@function
__STRING_NOT_EQUAL:                     # @__STRING_NOT_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	snez	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end11:
	.size	__STRING_NOT_EQUAL, .Lfunc_end11-__STRING_NOT_EQUAL
                                        # -- End function
	.globl	__STRING_LESS           # -- Begin function __STRING_LESS
	.p2align	1
	.type	__STRING_LESS,@function
__STRING_LESS:                          # @__STRING_LESS
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	__STRING_LESS, .Lfunc_end12-__STRING_LESS
                                        # -- End function
	.globl	__STRING_GREATER        # -- Begin function __STRING_GREATER
	.p2align	1
	.type	__STRING_GREATER,@function
__STRING_GREATER:                       # @__STRING_GREATER
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	sgtz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	__STRING_GREATER, .Lfunc_end13-__STRING_GREATER
                                        # -- End function
	.globl	__STRING_LESS_OR_EQUAL  # -- Begin function __STRING_LESS_OR_EQUAL
	.p2align	1
	.type	__STRING_LESS_OR_EQUAL,@function
__STRING_LESS_OR_EQUAL:                 # @__STRING_LESS_OR_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	slti	a0, a0, 1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	__STRING_LESS_OR_EQUAL, .Lfunc_end14-__STRING_LESS_OR_EQUAL
                                        # -- End function
	.globl	__STRING_GREATER_OR_EQUAL # -- Begin function __STRING_GREATER_OR_EQUAL
	.p2align	1
	.type	__STRING_GREATER_OR_EQUAL,@function
__STRING_GREATER_OR_EQUAL:              # @__STRING_GREATER_OR_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end15:
	.size	__STRING_GREATER_OR_EQUAL, .Lfunc_end15-__STRING_GREATER_OR_EQUAL
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object        # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.type	.L.str.4,@object        # @.str.4
.L.str.4:
	.asciz	"%255s"
	.size	.L.str.4, 6

	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
