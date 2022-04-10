	.text
	.file	"built_in.c"
	.globl	__NEW_ON_HEAP           # -- Begin function __NEW_ON_HEAP
	.p2align	4, 0x90
	.type	__NEW_ON_HEAP,@function
__NEW_ON_HEAP:                          # @__NEW_ON_HEAP
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$8, %esp
	movl	8(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	%ecx, (%esp)
	movl	%eax, -4(%ebp)          # 4-byte Spill
	calll	malloc
	addl	$8, %esp
	popl	%ebp
	retl
.Lfunc_end0:
	.size	__NEW_ON_HEAP, .Lfunc_end0-__NEW_ON_HEAP
                                        # -- End function
	.globl	__NEW_ARRAY             # -- Begin function __NEW_ARRAY
	.p2align	4, 0x90
	.type	__NEW_ARRAY,@function
__NEW_ARRAY:                            # @__NEW_ARRAY
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	16(%ebp), %eax
	movl	12(%ebp), %ecx
	movl	8(%ebp), %edx
	cmpl	$1, 12(%ebp)
	jne	.LBB1_2
# %bb.1:
	movl	8(%ebp), %eax
	movl	16(%ebp), %ecx
	imull	(%ecx), %eax
	addl	$4, %eax
	movl	%eax, (%esp)
	calll	malloc
	addl	$4, %eax
	movl	%eax, -4(%ebp)
	movl	16(%ebp), %eax
	movl	(%eax), %eax
	movl	-4(%ebp), %ecx
	movl	%eax, -4(%ecx)
.LBB1_2:
	cmpl	$1, 12(%ebp)
	jle	.LBB1_8
# %bb.3:
	movl	16(%ebp), %eax
	movl	(%eax), %eax
	shll	$2, %eax
	addl	$4, %eax
	movl	%eax, (%esp)
	calll	malloc
	addl	$4, %eax
	movl	%eax, -4(%ebp)
	movl	16(%ebp), %eax
	movl	(%eax), %eax
	movl	-4(%ebp), %ecx
	movl	%eax, -4(%ecx)
	movl	$0, -8(%ebp)
.LBB1_4:                                # =>This Inner Loop Header: Depth=1
	movl	-8(%ebp), %eax
	movl	16(%ebp), %ecx
	cmpl	(%ecx), %eax
	jge	.LBB1_7
# %bb.5:                                #   in Loop: Header=BB1_4 Depth=1
	movl	8(%ebp), %eax
	movl	12(%ebp), %ecx
	subl	$1, %ecx
	movl	16(%ebp), %edx
	addl	$4, %edx
	movl	%eax, (%esp)
	movl	%ecx, 4(%esp)
	movl	%edx, 8(%esp)
	calll	__NEW_ARRAY
	movl	-4(%ebp), %ecx
	movl	-8(%ebp), %edx
	movl	%eax, (%ecx,%edx,4)
# %bb.6:                                #   in Loop: Header=BB1_4 Depth=1
	movl	-8(%ebp), %eax
	addl	$1, %eax
	movl	%eax, -8(%ebp)
	jmp	.LBB1_4
.LBB1_7:
	jmp	.LBB1_8
.LBB1_8:
	movl	-4(%ebp), %eax
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end1:
	.size	__NEW_ARRAY, .Lfunc_end1-__NEW_ARRAY
                                        # -- End function
	.globl	__PRINT                 # -- Begin function __PRINT
	.p2align	4, 0x90
	.type	__PRINT,@function
__PRINT:                                # @__PRINT
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	8(%ebp), %eax
	movl	8(%ebp), %ecx
	leal	.L.str, %edx
	movl	%edx, (%esp)
	movl	%ecx, 4(%esp)
	movl	%eax, -4(%ebp)          # 4-byte Spill
	calll	printf
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end2:
	.size	__PRINT, .Lfunc_end2-__PRINT
                                        # -- End function
	.globl	__PRINTLN               # -- Begin function __PRINTLN
	.p2align	4, 0x90
	.type	__PRINTLN,@function
__PRINTLN:                              # @__PRINTLN
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	8(%ebp), %eax
	movl	8(%ebp), %ecx
	leal	.L.str.1, %edx
	movl	%edx, (%esp)
	movl	%ecx, 4(%esp)
	movl	%eax, -4(%ebp)          # 4-byte Spill
	calll	printf
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end3:
	.size	__PRINTLN, .Lfunc_end3-__PRINTLN
                                        # -- End function
	.globl	__PRINT_INT             # -- Begin function __PRINT_INT
	.p2align	4, 0x90
	.type	__PRINT_INT,@function
__PRINT_INT:                            # @__PRINT_INT
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	8(%ebp), %eax
	movl	8(%ebp), %ecx
	leal	.L.str.2, %edx
	movl	%edx, (%esp)
	movl	%ecx, 4(%esp)
	movl	%eax, -4(%ebp)          # 4-byte Spill
	calll	printf
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end4:
	.size	__PRINT_INT, .Lfunc_end4-__PRINT_INT
                                        # -- End function
	.globl	__PRINTLN_INT           # -- Begin function __PRINTLN_INT
	.p2align	4, 0x90
	.type	__PRINTLN_INT,@function
__PRINTLN_INT:                          # @__PRINTLN_INT
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	8(%ebp), %eax
	movl	8(%ebp), %ecx
	leal	.L.str.3, %edx
	movl	%edx, (%esp)
	movl	%ecx, 4(%esp)
	movl	%eax, -4(%ebp)          # 4-byte Spill
	calll	printf
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end5:
	.size	__PRINTLN_INT, .Lfunc_end5-__PRINTLN_INT
                                        # -- End function
	.globl	__GET_STRING            # -- Begin function __GET_STRING
	.p2align	4, 0x90
	.type	__GET_STRING,@function
__GET_STRING:                           # @__GET_STRING
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	$260, (%esp)            # imm = 0x104
	calll	malloc
	addl	$4, %eax
	movl	%eax, -4(%ebp)
	movl	-4(%ebp), %eax
	leal	.L.str.4, %ecx
	movl	%ecx, (%esp)
	movl	%eax, 4(%esp)
	calll	__isoc99_scanf
	movl	-4(%ebp), %ecx
	movl	%esp, %edx
	movl	%ecx, (%edx)
	movl	%eax, -8(%ebp)          # 4-byte Spill
	calll	strlen
	movl	-4(%ebp), %ecx
	movl	%eax, -4(%ecx)
	movl	-4(%ebp), %eax
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end6:
	.size	__GET_STRING, .Lfunc_end6-__GET_STRING
                                        # -- End function
	.globl	__GET_INT               # -- Begin function __GET_INT
	.p2align	4, 0x90
	.type	__GET_INT,@function
__GET_INT:                              # @__GET_INT
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	leal	.L.str.2, %eax
	movl	%eax, (%esp)
	leal	-4(%ebp), %eax
	movl	%eax, 4(%esp)
	calll	__isoc99_scanf
	movl	-4(%ebp), %ecx
	movl	%eax, -8(%ebp)          # 4-byte Spill
	movl	%ecx, %eax
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end7:
	.size	__GET_INT, .Lfunc_end7-__GET_INT
                                        # -- End function
	.globl	__TO_STRING             # -- Begin function __TO_STRING
	.p2align	4, 0x90
	.type	__TO_STRING,@function
__TO_STRING:                            # @__TO_STRING
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	8(%ebp), %eax
	movl	$17, (%esp)
	movl	%eax, -8(%ebp)          # 4-byte Spill
	calll	malloc
	addl	$4, %eax
	movl	%eax, -4(%ebp)
	movl	-4(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	%eax, (%esp)
	leal	.L.str.2, %eax
	movl	%eax, 4(%esp)
	movl	%ecx, 8(%esp)
	calll	sprintf
	movl	-4(%ebp), %ecx
	movl	%esp, %edx
	movl	%ecx, (%edx)
	movl	%eax, -12(%ebp)         # 4-byte Spill
	calll	strlen
	movl	-4(%ebp), %ecx
	movl	%eax, -4(%ecx)
	movl	-4(%ebp), %eax
	addl	$24, %esp
	popl	%ebp
	retl
.Lfunc_end8:
	.size	__TO_STRING, .Lfunc_end8-__TO_STRING
                                        # -- End function
	.globl	__STRING_ADD            # -- Begin function __STRING_ADD
	.p2align	4, 0x90
	.type	__STRING_ADD,@function
__STRING_ADD:                           # @__STRING_ADD
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%esi
	subl	$36, %esp
	movl	12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	8(%ebp), %edx
	movl	%esp, %esi
	movl	%edx, (%esi)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	strlen
	movl	12(%ebp), %ecx
	movl	%esp, %edx
	movl	%ecx, (%edx)
	movl	%eax, -24(%ebp)         # 4-byte Spill
	calll	strlen
	movl	-24(%ebp), %ecx         # 4-byte Reload
	addl	%eax, %ecx
	movl	%ecx, -8(%ebp)
	movl	-8(%ebp), %eax
	addl	$5, %eax
	movl	%eax, (%esp)
	calll	malloc
	addl	$4, %eax
	movl	%eax, -12(%ebp)
	movl	-12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	%esp, %edx
	movl	%ecx, 4(%edx)
	movl	%eax, (%edx)
	calll	strcpy
	movl	-12(%ebp), %ecx
	movl	12(%ebp), %edx
	movl	%ecx, (%esp)
	movl	%edx, 4(%esp)
	movl	%eax, -28(%ebp)         # 4-byte Spill
	calll	strcat
	movl	-8(%ebp), %ecx
	movl	-12(%ebp), %edx
	movl	%ecx, -4(%edx)
	movl	-12(%ebp), %ecx
	movl	%eax, -32(%ebp)         # 4-byte Spill
	movl	%ecx, %eax
	addl	$36, %esp
	popl	%esi
	popl	%ebp
	retl
.Lfunc_end9:
	.size	__STRING_ADD, .Lfunc_end9-__STRING_ADD
                                        # -- End function
	.globl	__STRING_EQUAL          # -- Begin function __STRING_EQUAL
	.p2align	4, 0x90
	.type	__STRING_EQUAL,@function
__STRING_EQUAL:                         # @__STRING_EQUAL
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$28, %esp
	movl	12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	8(%ebp), %edx
	movl	12(%ebp), %esi
	movl	%esp, %edi
	movl	%esi, 4(%edi)
	movl	%edx, (%edi)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	strcmp
	cmpl	$0, %eax
	sete	%bl
	andb	$1, %bl
	movzbl	%bl, %eax
                                        # kill: def $al killed $al killed $eax
	movsbl	%al, %eax
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	retl
.Lfunc_end10:
	.size	__STRING_EQUAL, .Lfunc_end10-__STRING_EQUAL
                                        # -- End function
	.globl	__STRING_NOT_EQUAL      # -- Begin function __STRING_NOT_EQUAL
	.p2align	4, 0x90
	.type	__STRING_NOT_EQUAL,@function
__STRING_NOT_EQUAL:                     # @__STRING_NOT_EQUAL
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$28, %esp
	movl	12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	8(%ebp), %edx
	movl	12(%ebp), %esi
	movl	%esp, %edi
	movl	%esi, 4(%edi)
	movl	%edx, (%edi)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	strcmp
	cmpl	$0, %eax
	setne	%bl
	andb	$1, %bl
	movzbl	%bl, %eax
                                        # kill: def $al killed $al killed $eax
	movsbl	%al, %eax
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	retl
.Lfunc_end11:
	.size	__STRING_NOT_EQUAL, .Lfunc_end11-__STRING_NOT_EQUAL
                                        # -- End function
	.globl	__STRING_LESS           # -- Begin function __STRING_LESS
	.p2align	4, 0x90
	.type	__STRING_LESS,@function
__STRING_LESS:                          # @__STRING_LESS
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$28, %esp
	movl	12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	8(%ebp), %edx
	movl	12(%ebp), %esi
	movl	%esp, %edi
	movl	%esi, 4(%edi)
	movl	%edx, (%edi)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	strcmp
	cmpl	$0, %eax
	setl	%bl
	andb	$1, %bl
	movzbl	%bl, %eax
                                        # kill: def $al killed $al killed $eax
	movsbl	%al, %eax
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	retl
.Lfunc_end12:
	.size	__STRING_LESS, .Lfunc_end12-__STRING_LESS
                                        # -- End function
	.globl	__STRING_GREATER        # -- Begin function __STRING_GREATER
	.p2align	4, 0x90
	.type	__STRING_GREATER,@function
__STRING_GREATER:                       # @__STRING_GREATER
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$28, %esp
	movl	12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	8(%ebp), %edx
	movl	12(%ebp), %esi
	movl	%esp, %edi
	movl	%esi, 4(%edi)
	movl	%edx, (%edi)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	strcmp
	cmpl	$0, %eax
	setg	%bl
	andb	$1, %bl
	movzbl	%bl, %eax
                                        # kill: def $al killed $al killed $eax
	movsbl	%al, %eax
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	retl
.Lfunc_end13:
	.size	__STRING_GREATER, .Lfunc_end13-__STRING_GREATER
                                        # -- End function
	.globl	__STRING_LESS_OR_EQUAL  # -- Begin function __STRING_LESS_OR_EQUAL
	.p2align	4, 0x90
	.type	__STRING_LESS_OR_EQUAL,@function
__STRING_LESS_OR_EQUAL:                 # @__STRING_LESS_OR_EQUAL
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$28, %esp
	movl	12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	8(%ebp), %edx
	movl	12(%ebp), %esi
	movl	%esp, %edi
	movl	%esi, 4(%edi)
	movl	%edx, (%edi)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	strcmp
	cmpl	$0, %eax
	setle	%bl
	andb	$1, %bl
	movzbl	%bl, %eax
                                        # kill: def $al killed $al killed $eax
	movsbl	%al, %eax
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	retl
.Lfunc_end14:
	.size	__STRING_LESS_OR_EQUAL, .Lfunc_end14-__STRING_LESS_OR_EQUAL
                                        # -- End function
	.globl	__STRING_GREATER_OR_EQUAL # -- Begin function __STRING_GREATER_OR_EQUAL
	.p2align	4, 0x90
	.type	__STRING_GREATER_OR_EQUAL,@function
__STRING_GREATER_OR_EQUAL:              # @__STRING_GREATER_OR_EQUAL
# %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$28, %esp
	movl	12(%ebp), %eax
	movl	8(%ebp), %ecx
	movl	8(%ebp), %edx
	movl	12(%ebp), %esi
	movl	%esp, %edi
	movl	%esi, 4(%edi)
	movl	%edx, (%edi)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	strcmp
	cmpl	$0, %eax
	setge	%bl
	andb	$1, %bl
	movzbl	%bl, %eax
                                        # kill: def $al killed $al killed $eax
	movsbl	%al, %eax
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	retl
.Lfunc_end15:
	.size	__STRING_GREATER_OR_EQUAL, .Lfunc_end15-__STRING_GREATER_OR_EQUAL
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
	.addrsig
	.addrsig_sym malloc
	.addrsig_sym __NEW_ARRAY
	.addrsig_sym printf
	.addrsig_sym __isoc99_scanf
	.addrsig_sym strlen
	.addrsig_sym sprintf
	.addrsig_sym strcpy
	.addrsig_sym strcat
	.addrsig_sym strcmp
