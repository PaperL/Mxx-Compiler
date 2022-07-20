build:
	bash run/build.bash

test_ir:
	python3 run/ir_local_judge.py $(TESTCASE)

clean:
	rm -r bin

builtin:
	clang -emit-llvm -O3 -S built_in/built_in.c -o built_in/built_in.ll -I /usr/include --target=riscv32-unknown-elf
	llc -O3 -o built_in/built_in.s built_in/built_in.ll

.PHONY: build test_ir clean builtin
