testDir = testspace
runUtilityDir = run
builtinDir = built_in

build:
	bash $(runUtilityDir)/build.bash

semantic: build
	bash $(runUtilityDir)/run.bash --local --semantic

ir: build
	bash $(runUtilityDir)/run.bash --local --ir --ir-comment

run: build
	bash $(runUtilityDir)/run.bash --local --asm-comment -O

test_ir: build
	python3 $(runUtilityDir)/ir_local_judge.py $(TESTCASE)

clean:
	rm -r bin
	rm -r $(testDir)

builtin:
	clang -emit-llvm -O3 -S $(builtinDir)/built_in.c -o $(builtinDir)/built_in_riscv_32.ll -I /usr/include --target=riscv32-unknown-elf
	clang -emit-llvm -O3 -S $(builtinDir)/built_in.c -o $(builtinDir)/built_in_x86_64.ll -I /usr/include --target=x86_64-pc-linux-gnu
	llc -O3 -o $(builtinDir)/built_in_riscv_32.s $(builtinDir)/built_in_riscv_32.ll

# * For development
clang_ir:
	clang -emit-llvm -S $(testDir)/$(NAME).cpp -o $(testDir)/$(NAME).ll -I /usr/include --target=riscv32

clang_asm: clang_ir
	llc $(testDir)/$(NAME).ll -o $(testDir)/$(NAME).s -I /usr/include -march=riscv32

run_asm: NAME = test
run_asm:
	$(runUtilityDir)/ravel/exe/bin/ravel --input-file=$(testDir)/test.in --output-file=$(testDir)/test.out $(testDir)/test.s $(builtinDir)/built_in_riscv_32.s

test_asm: NAME = test
test_asm: clang_ir clang_asm run_asm

.PHONY: build semantic ir run test_ir clean builtin clang_ir clang_asm run_asm test_asm
