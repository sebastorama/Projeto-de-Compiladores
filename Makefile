all: precompile_javacc apply_patches build_precompiled_javacc

lex_test1:
	java -cp . Pascal < examples/lexical/correct1.pas
lex_test2:
	java -cp . Pascal < examples/lexical/correct2.pas
lex_test3:
	java -cp . Pascal < examples/lexical/correct3.pas
lex_test4:
	java -cp . Pascal < examples/lexical/incorrect1.pas

lex_all: lex_test1 lex_test2 lex_test3 lex_test4

clean:
	rm *.java && rm *.class

apply_patches:
	cp patches/* .

precompile_javacc:
	javacc pascal.jj

build_precompiled_javacc:
	javac -cp . Pascal.java
