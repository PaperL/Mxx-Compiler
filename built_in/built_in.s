	.text
	.file	"built_in.c"
	.globl	__NEW_ARRAY             # -- Begin function __NEW_ARRAY
	.p2align	1
	.type	__NEW_ARRAY,@function
__NEW_ARRAY:                            # @__NEW_ARRAY
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	addi	s0, sp, 32
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sw	a2, -20(s0)
	lw	a0, -16(s0)
	addi	a1, zero, 1
	bne	a0, a1, .LBB0_2
	j	.LBB0_1
.LBB0_1:
	lw	a0, -12(s0)
	lw	a1, -20(s0)
	lw	a1, 0(a1)
	mul	a0, a0, a1
	addi	a0, a0, 4
	call	malloc
	addi	a0, a0, 4
	sw	a0, -24(s0)
	lw	a0, -20(s0)
	lw	a0, 0(a0)
	lw	a1, -24(s0)
	sw	a0, -4(a1)
	j	.LBB0_2
.LBB0_2:
	lw	a0, -16(s0)
	addi	a1, zero, 2
	blt	a0, a1, .LBB0_8
	j	.LBB0_3
.LBB0_3:
	lw	a0, -20(s0)
	lw	a0, 0(a0)
	slli	a0, a0, 2
	addi	a0, a0, 4
	call	malloc
	addi	a0, a0, 4
	sw	a0, -24(s0)
	lw	a0, -20(s0)
	lw	a0, 0(a0)
	lw	a1, -24(s0)
	sw	a0, -4(a1)
	sw	zero, -28(s0)
	j	.LBB0_4
.LBB0_4:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -28(s0)
	lw	a1, -20(s0)
	lw	a1, 0(a1)
	bge	a0, a1, .LBB0_7
	j	.LBB0_5
.LBB0_5:                                #   in Loop: Header=BB0_4 Depth=1
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	addi	a1, a1, -1
	lw	a2, -20(s0)
	addi	a2, a2, 4
	call	__NEW_ARRAY
	lw	a1, -24(s0)
	lw	a2, -28(s0)
	slli	a2, a2, 2
	add	a1, a1, a2
	sw	a0, 0(a1)
	j	.LBB0_6
.LBB0_6:                                #   in Loop: Header=BB0_4 Depth=1
	lw	a0, -28(s0)
	addi	a0, a0, 1
	sw	a0, -28(s0)
	j	.LBB0_4
.LBB0_7:
	j	.LBB0_8
.LBB0_8:
	lw	a0, -24(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end0:
	.size	__NEW_ARRAY, .Lfunc_end0-__NEW_ARRAY
                                        # -- End function
	.globl	__PRINT                 # -- Begin function __PRINT
	.p2align	1
	.type	__PRINT,@function
__PRINT:                                # @__PRINT
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	__PRINT, .Lfunc_end1-__PRINT
                                        # -- End function
	.globl	__PRINTLN               # -- Begin function __PRINTLN
	.p2align	1
	.type	__PRINTLN,@function
__PRINTLN:                              # @__PRINTLN
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	__PRINTLN, .Lfunc_end2-__PRINTLN
                                        # -- End function
	.globl	__PRINT_INT             # -- Begin function __PRINT_INT
	.p2align	1
	.type	__PRINT_INT,@function
__PRINT_INT:                            # @__PRINT_INT
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	__PRINT_INT, .Lfunc_end3-__PRINT_INT
                                        # -- End function
	.globl	__PRINTLN_INT           # -- Begin function __PRINTLN_INT
	.p2align	1
	.type	__PRINTLN_INT,@function
__PRINTLN_INT:                          # @__PRINTLN_INT
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	__PRINTLN_INT, .Lfunc_end4-__PRINTLN_INT
                                        # -- End function
	.globl	__GET_STRING            # -- Begin function __GET_STRING
	.p2align	1
	.type	__GET_STRING,@function
__GET_STRING:                           # @__GET_STRING
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	addi	a0, zero, 260
	call	malloc
	addi	a0, a0, 4
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.4)
	addi	a0, a0, %lo(.L.str.4)
	call	__isoc99_scanf
	lw	a0, -12(s0)
	call	strlen
	lw	a1, -12(s0)
	sw	a0, -4(a1)
	lw	a0, -12(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	__GET_STRING, .Lfunc_end5-__GET_STRING
                                        # -- End function
	.globl	__GET_INT               # -- Begin function __GET_INT
	.p2align	1
	.type	__GET_INT,@function
__GET_INT:                              # @__GET_INT
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, s0, -12
	call	__isoc99_scanf
	lw	a0, -12(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	__GET_INT, .Lfunc_end6-__GET_INT
                                        # -- End function
	.globl	__TO_STRING             # -- Begin function __TO_STRING
	.p2align	1
	.type	__TO_STRING,@function
__TO_STRING:                            # @__TO_STRING
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	addi	a0, zero, 17
	call	malloc
	addi	a0, a0, 4
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	lw	a2, -12(s0)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	call	sprintf
	lw	a0, -16(s0)
	call	strlen
	lw	a1, -16(s0)
	sw	a0, -4(a1)
	lw	a0, -16(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	__TO_STRING, .Lfunc_end7-__TO_STRING
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
	addi	s0, sp, 32
	sw	a0, -16(s0)
	sw	a1, -20(s0)
	lw	a0, -16(s0)
	call	strlen
	mv	s1, a0
	lw	a0, -20(s0)
	call	strlen
	add	a0, s1, a0
	sw	a0, -24(s0)
	lw	a0, -24(s0)
	addi	a0, a0, 5
	call	malloc
	addi	a0, a0, 4
	sw	a0, -28(s0)
	lw	a0, -28(s0)
	lw	a1, -16(s0)
	call	strcpy
	lw	a0, -28(s0)
	lw	a1, -20(s0)
	call	strcat
	lw	a0, -24(s0)
	lw	a1, -28(s0)
	sw	a0, -4(a1)
	lw	a0, -28(s0)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end8:
	.size	__STRING_ADD, .Lfunc_end8-__STRING_ADD
                                        # -- End function
	.globl	__STRING_EQUAL          # -- Begin function __STRING_EQUAL
	.p2align	1
	.type	__STRING_EQUAL,@function
__STRING_EQUAL:                         # @__STRING_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	seqz	a0, a0
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end9:
	.size	__STRING_EQUAL, .Lfunc_end9-__STRING_EQUAL
                                        # -- End function
	.globl	__STRING_NOT_EQUAL      # -- Begin function __STRING_NOT_EQUAL
	.p2align	1
	.type	__STRING_NOT_EQUAL,@function
__STRING_NOT_EQUAL:                     # @__STRING_NOT_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	snez	a0, a0
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end10:
	.size	__STRING_NOT_EQUAL, .Lfunc_end10-__STRING_NOT_EQUAL
                                        # -- End function
	.globl	__STRING_LESS           # -- Begin function __STRING_LESS
	.p2align	1
	.type	__STRING_LESS,@function
__STRING_LESS:                          # @__STRING_LESS
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	srli	a0, a0, 31
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end11:
	.size	__STRING_LESS, .Lfunc_end11-__STRING_LESS
                                        # -- End function
	.globl	__STRING_GREATER        # -- Begin function __STRING_GREATER
	.p2align	1
	.type	__STRING_GREATER,@function
__STRING_GREATER:                       # @__STRING_GREATER
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	sgtz	a0, a0
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	__STRING_GREATER, .Lfunc_end12-__STRING_GREATER
                                        # -- End function
	.globl	__STRING_LESS_OR_EQUAL  # -- Begin function __STRING_LESS_OR_EQUAL
	.p2align	1
	.type	__STRING_LESS_OR_EQUAL,@function
__STRING_LESS_OR_EQUAL:                 # @__STRING_LESS_OR_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	slti	a0, a0, 1
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	__STRING_LESS_OR_EQUAL, .Lfunc_end13-__STRING_LESS_OR_EQUAL
                                        # -- End function
	.globl	__STRING_GREATER_OR_EQUAL # -- Begin function __STRING_GREATER_OR_EQUAL
	.p2align	1
	.type	__STRING_GREATER_OR_EQUAL,@function
__STRING_GREATER_OR_EQUAL:              # @__STRING_GREATER_OR_EQUAL
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	__STRING_GREATER_OR_EQUAL, .Lfunc_end14-__STRING_GREATER_OR_EQUAL
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

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
