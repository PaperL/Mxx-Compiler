set ff=UNIX
set -e
mkdir -p bin
printf "Building...\n"
find ./src -name *.java | javac -d bin -cp /ulib/java/antlr-runtime-4.9.2.jar @/dev/stdin
printf "Program Running...\n"
cat | java -cp /ulib/java/antlr-runtime-4.9.2.jar:./bin Main --debug --local --ir-source-code
clang output.ll
set +e
./a.out
echo $?