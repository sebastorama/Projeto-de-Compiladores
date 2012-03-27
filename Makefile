all:
	javacc pascal.jj
	javac -cp . Pascal.java

lex_test1:
	echo "========== Lexical Test 1 - Should pass: ===========\n\n\n"
	java -cp . Pascal < examples/lexical/correct1.pas
	echo "========== End of Lexical Test 1         ===========\n\n"
lex_test2:
	echo "Lexical Test 2 - Should pass:"
lex_test3:
	echo "Lexical Test 3 - Should give an error:"

clean:
	rm *.java || rm *.class
