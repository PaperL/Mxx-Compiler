# Written by RainyMemory
#!python3

import sys
import os
import time

test_cases_dir = 'run/testcase/codegen/'
excluded_test_cases = [] # ["foo.mx"]
builtin_path = "./built_in/built_in_x86_64.ll"
bin_path = "./testbin/"
fail_num_limit = -1
errInfo = True # Any runtime CLI output
outputToScreen = False # This lead to all testpoints being 'Wrong Answer' because ouput is redirected to stdout
calculate_score = False
# When test_codegen && use_llvm is true, the output should be a .ll file, and we will use llc to
# compile it into asm. You can test the correctness of your IR-gen with this.
use_llvm = True
llc_cmd = 'llc'

color_red = "\033[0;31m"
color_yellow = "\033[1;33m"
color_green = "\033[0;32m"
color_none = "\033[0m"


def collect_test_cases():
    test_cases = []
    if len(sys.argv) > 1:
        for f in os.listdir(test_cases_dir):
            i = f.find(sys.argv[1])
            if os.path.splitext(f)[1] == '.mx' and i != -1 and f[i+len(sys.argv[1])]=='.':
                test_cases.append(f)
    else:
        for f in os.listdir(test_cases_dir):
            if os.path.splitext(f)[1] == '.mx':
                test_cases.append(f)
    print("Test cases number:" + color_green + str(len(test_cases)) + color_none)
    for s in excluded_test_cases:
        if s in test_cases:
            test_cases.remove(s)
    test_cases.sort()
    return test_cases


def parse_test_case(test_case_path):
    with open(test_case_path, 'r') as f:
        lines = f.read().split('\n')
    src_start_idx = lines.index('*/', lines.index('/*')) + 1
    src_text = '\n'.join(lines[src_start_idx:])

    input_start_idx = lines.index('=== input ===') + 1
    input_end_idx = lines.index('=== end ===', input_start_idx)
    input_text = '\n'.join(lines[input_start_idx:input_end_idx])

    output_start_idx = lines.index('=== output ===') + 1
    output_end_idx = lines.index('=== end ===', output_start_idx)
    output_text = '\n'.join(lines[output_start_idx:output_end_idx])

    exit_code = 0
    for line in lines:
        p = line.find('ExitCode: ')
        if p != -1:
            exit_code = int(line[p+10:])

    return src_text, input_text, output_text, exit_code


def main():

    print('Project Directory:' + color_green)
    os.system('pwd')
    input(color_none + 'Ensure it is the project root directory and press enter to continue...')
    os.system('mkdir -p testbin')

    print('Collecting Test Cases...')
    test_cases = collect_test_cases()
    #     os.system('clang -S -emit-llvm builtin/builtin.c -o builtin/builtin.ll')
    os.system('cp {} ./testbin/b.ll'.format(builtin_path))
    total = 0
    passed = 0
    fail_num = 0
    max_len = max(len(i) for i in test_cases)
    max_len += 5
    for t in test_cases:
        if fail_num_limit != -1 and fail_num >= fail_num_limit:
            exit(1)
        total += 1
        src_text, input_text, output_text, exit_code = parse_test_case(test_cases_dir + t)
        with open('./testbin/test.mx', 'w') as f:
            f.write(src_text)
        with open('./testbin/test.in', 'w') as f:
            f.write(input_text)
        with open('./testbin/test.ans', 'w') as f:
            f.write(output_text)

        print(color_yellow + t + color_none + ':', end='')
        for i in range(len(t), max_len):
            print(end=' ')
        start = time.time()
        if os.system('bash run/ir.bash < ./testbin/test.mx' + ('' if errInfo else ' > /dev/null 2>&1')):
            print(color_red + "Compilation Failed" + color_none)
            fail_num += 1
            continue
        os.system('mv output.ll testbin/test.ll')
        print('(T=%.2fs)' % (time.time() - start), end=" ")
        if os.system('clang ./testbin/test.ll ./testbin/b.ll -o ./testbin/a.out -Wno-override-module' + ('' if errInfo else ' > /dev/null 2>&1')):
            print(color_red + "Link Error" + color_none)
            fail_num += 1
            continue
        ret = os.system('./testbin/a.out < ./testbin/test.in' + ('' if outputToScreen else ' > ./testbin/test.out')) >> 8
        if ret != exit_code:
            # print('ret: ' + str(ret))
            print(color_red + "Runtime Error" + color_none)
            fail_num += 1
            continue
        if os.system('diff -B -b ./testbin/test.out ./testbin/test.ans > ./testbin/diff.out'):
            print(color_red + "Wrong Answer" + color_none)
            fail_num += 1
            continue
        passed += 1
        print(color_green + "Accepted" + color_none)

    print("total {}, passed {}, ratio {}".format(total, passed, passed / total))


if __name__ == '__main__':
    main()