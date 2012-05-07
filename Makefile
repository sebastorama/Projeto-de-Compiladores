all: precompile_javacc apply_patches build_precompiled_javacc

correct_1:
	java -cp . Pascal < examples/correct/correct_1.pas
correct_2:
	java -cp . Pascal < examples/correct/correct_2.pas
correct_3:
	java -cp . Pascal < examples/correct/correct_3.pas
correct_4:
	java -cp . Pascal < examples/correct/correct_4.pas
correct_all: correct_1 correct_2 correct_3 correct_4

sintatical_error_1:
	- java -cp . Pascal < examples/sintatical_errors/sintatical_error_1.pas
sintatical_error_2:
	- java -cp . Pascal < examples/sintatical_errors/sintatical_error_2.pas
sintatical_error_3:
	- java -cp . Pascal < examples/sintatical_errors/sintatical_error_3.pas

sintatical_error_all: sintatical_error_1 sintatical_error_2 sintatical_error_3

lexical_error_1:
	- java -cp . Pascal < examples/lexical_errors/lexical_error_1.pas
lexical_error_2:
	- java -cp . Pascal < examples/lexical_errors/lexical_error_2.pas

all_tests: correct_all sintatical_error_all lexical_error_1

clean:
	rm *.java && rm *.class

apply_patches:
	cp patches/* .

precompile_javacc:
	javacc pascal.jj

build_precompiled_javacc:
	javac -cp . Pascal.java
