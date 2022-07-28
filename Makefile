build:
	bash run/build.bash

semantic: build
	bash run/run.bash --local --semantic

ir: build
	bash run/run.bash --local --ir --ir-comment

run: build
	bash run/run.bash --local --asm-comment -O

test_ir: build
	python3 run/ir_local_judge.py $(TESTCASE)

clean:
	rm -r bin
	rm -r testbin

builtin:
	clang -emit-llvm -O3 -S built_in/built_in.c -o built_in/built_in_riscv_32.ll -I /usr/include --target=riscv32-unknown-elf
	clang -emit-llvm -O3 -S built_in/built_in.c -o built_in/built_in_x86_64.ll -I /usr/include --target=x86_64-pc-linux-gnu
	llc -O3 -o built_in/built_in.s built_in/built_in_riscv_32.ll

.PHONY: build semantic ir run test_ir clean builtin
