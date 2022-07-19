set ff=UNIX
set -e
cat | java -ea -cp lib/antlr-4.9.1-complete.jar:bin Main --local --ir