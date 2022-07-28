set -e
cat | java -ea -cp third_party/antlr-runtime-4.9.2.jar:bin Main $@