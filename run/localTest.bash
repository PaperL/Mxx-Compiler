set ff=UNIX
set -e

mkdir -p bin

printf "Building...\n"
find ./src -name *.java | javac -d bin -cp third_party/antlr-runtime-4.9.2.jar @/dev/stdin

printf "Program Running...\n"
cat | java -cp third_party/antlr-runtime-4.9.2.jar:./bin Main --debug --local --ir --ir-source-code

clang -o output.out built_in/built_in.ll output.ll

set +e
./output.out
echo -e "\nReturn Code:$?"