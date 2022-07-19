build:
	bash run/build.bash

test_ir:
	python3 run/ir_local_judge.py $(TESTCASE)

clean:
	rm -r bin

.PHONY: build test_ir clean
