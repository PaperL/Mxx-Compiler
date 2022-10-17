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
	sw	s1, 20(sp)
	sw	s2, 16(sp)
	sw	s3, 12(sp)
	sw	s4, 8(sp)
	sw	s5, 4(sp)
	sw	s6, 0(sp)
	lw	s0, 0(a2)
	beqz	s0, .LBB0_3
# %bb.1:
	mv	s3, a1
	mv	s4, a0
	addi	a0, zero, 1
	bne	a1, a0, .LBB0_4
# %bb.2:
	mul	a0, s0, s4
	addi	a0, a0, 4
	call	malloc
	addi	s2, a0, 4
	sw	s0, 0(a0)
	j	.LBB0_8
.LBB0_3:
	mv	s2, zero
	j	.LBB0_8
.LBB0_4:
	addi	a0, zero, 2
                                        # implicit-def: $x18
	blt	s3, a0, .LBB0_8
# %bb.5:
	mv	s5, a2
	slli	a0, s0, 2
	addi	a0, a0, 4
	call	malloc
	addi	s2, a0, 4
	addi	a1, zero, 1
	sw	s0, 0(a0)
	blt	s0, a1, .LBB0_8
# %bb.6:
	mv	s1, zero
	addi	s3, s3, -1
	addi	s6, s5, 4
	mv	s0, s2
.LBB0_7:                                # =>This Inner Loop Header: Depth=1
	mv	a0, s4
	mv	a1, s3
	mv	a2, s6
	call	__NEW_ARRAY
	sw	a0, 0(s0)
	lw	a0, 0(s5)
	addi	s1, s1, 1
	addi	s0, s0, 4
	blt	s1, a0, .LBB0_7
.LBB0_8:
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
.Lfunc_end0:
	.size	__NEW_ARRAY, .Lfunc_end0-__NEW_ARRAY
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
.Lfunc_end1:
	.size	__PRINT, .Lfunc_end1-__PRINT
                                        # -- End function
	.globl	__PRINTLN               # -- Begin function __PRINTLN
	.p2align	1
	.type	__PRINTLN,@function
__PRINTLN:                              # @__PRINTLN
# %bb.0:
	tail	puts
.Lfunc_end2:
	.size	__PRINTLN, .Lfunc_end2-__PRINTLN
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
.Lfunc_end3:
	.size	__PRINT_INT, .Lfunc_end3-__PRINT_INT
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
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	__isoc99_scanf
	lw	a0, 8(sp)
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
	sw	s1, 4(sp)
	sw	s2, 0(sp)
	mv	s2, a0
	addi	a0, zero, 260
	call	malloc
	mv	s1, a0
	addi	s0, a0, 4
	lui	a0, %hi(.L.str.2)
	addi	a1, a0, %lo(.L.str.2)
	mv	a0, s0
	mv	a2, s2
	call	sprintf
	sw	a0, 0(s1)
	mv	a0, s0
	lw	s2, 0(sp)
	lw	s1, 4(sp)
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
	sw	s2, 16(sp)
	sw	s3, 12(sp)
	mv	s2, a1
	mv	s3, a0
	call	strlen
	mv	s0, a0
	mv	a0, s2
	call	strlen
	add	s1, a0, s0
	addi	a0, s1, 5
	call	malloc
	addi	s0, a0, 4
	sw	s1, 0(a0)
	mv	a0, s0
	mv	a1, s3
	call	strcpy
	mv	a0, s0
	mv	a1, s2
	lw	s3, 12(sp)
	lw	s2, 16(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	tail	strcat
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
	call	strcmp
	seqz	a0, a0
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
	call	strcmp
	snez	a0, a0
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
	call	strcmp
	srli	a0, a0, 31
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
	call	strcmp
	sgtz	a0, a0
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
	call	strcmp
	slti	a0, a0, 1
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
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	__STRING_GREATER_OR_EQUAL, .Lfunc_end14-__STRING_GREATER_OR_EQUAL
                                        # -- End function
	.globl	__STRING_SUBSTRING      # -- Begin function __STRING_SUBSTRING
	.p2align	1
	.type	__STRING_SUBSTRING,@function
__STRING_SUBSTRING:                     # @__STRING_SUBSTRING
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	sw	s2, 16(sp)
	sw	s3, 12(sp)
	mv	s3, a1
	mv	s2, a0
	sub	s1, a2, a1
	addi	a0, s1, 5
	call	malloc
	addi	s0, a0, 4
	sw	s1, 0(a0)
	add	a1, s2, s3
	mv	a0, s0
	mv	a2, s1
	call	memcpy
	add	a0, s0, s1
	sb	zero, 0(a0)
	mv	a0, s0
	lw	s3, 12(sp)
	lw	s2, 16(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end15:
	.size	__STRING_SUBSTRING, .Lfunc_end15-__STRING_SUBSTRING
                                        # -- End function
	.globl	__STRING_PARSE_INT      # -- Begin function __STRING_PARSE_INT
	.p2align	1
	.type	__STRING_PARSE_INT,@function
__STRING_PARSE_INT:                     # @__STRING_PARSE_INT
# %bb.0:
	lbu	a4, 0(a0)
	addi	a1, a4, -48
	andi	a1, a1, 255
	mv	a6, zero
	addi	a3, zero, 10
	bltu	a1, a3, .LBB16_8
# %bb.1:                                # %.preheader
	addi	a1, zero, 45
	addi	a7, zero, 9
	j	.LBB16_4
.LBB16_2:                               #   in Loop: Header=BB16_4 Depth=1
	addi	a6, zero, 1
.LBB16_3:                               #   in Loop: Header=BB16_4 Depth=1
	lbu	a4, 1(a0)
	addi	a2, a0, 1
	addi	a0, a4, -48
	andi	a5, a0, 255
	mv	a0, a2
	bgeu	a7, a5, .LBB16_6
.LBB16_4:                               # =>This Inner Loop Header: Depth=1
	andi	a2, a4, 255
	beq	a2, a1, .LBB16_2
# %bb.5:                                #   in Loop: Header=BB16_4 Depth=1
	bnez	a2, .LBB16_3
	j	.LBB16_13
.LBB16_6:
	addi	a0, zero, 9
	bltu	a0, a5, .LBB16_13
# %bb.7:
	mv	a0, a2
.LBB16_8:
	mv	a1, zero
	addi	a0, a0, 1
.LBB16_9:                               # =>This Inner Loop Header: Depth=1
	andi	a2, a4, 255
	lbu	a4, 0(a0)
	mul	a1, a1, a3
	add	a1, a1, a2
	addi	a1, a1, -48
	addi	a2, a4, -48
	andi	a2, a2, 255
	addi	a0, a0, 1
	bltu	a2, a3, .LBB16_9
# %bb.10:
	andi	a0, a6, 255
	beqz	a0, .LBB16_12
.LBB16_11:
	neg	a1, a1
.LBB16_12:
	mv	a0, a1
	ret
.LBB16_13:                              # %.loopexit1
	mv	a1, zero
	andi	a0, a6, 255
	bnez	a0, .LBB16_11
	j	.LBB16_12
.Lfunc_end16:
	.size	__STRING_PARSE_INT, .Lfunc_end16-__STRING_PARSE_INT
                                        # -- End function
	.globl	__STRING_ORD            # -- Begin function __STRING_ORD
	.p2align	1
	.type	__STRING_ORD,@function
__STRING_ORD:                           # @__STRING_ORD
# %bb.0:
	add	a0, a0, a1
	lbu	a0, 0(a0)
	ret
.Lfunc_end17:
	.size	__STRING_ORD, .Lfunc_end17-__STRING_ORD
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
