set ff=UNIX
set -e
mkdir -p bin
find ./src -name *.java | javac -d bin -cp third_party/antlr-runtime-4.9.2.jar @/dev/stdin