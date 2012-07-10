all: precompile_javacc compile_st compile_rs compile_cw apply_patches build_precompiled_javacc

case_1:
	java -cp . Pascal < examples/correct/case_1.pas > generated_code/case_1.MEPA
	cat generated_code/case_1.MEPA
case_2:
	java -cp . Pascal < examples/correct/case_2.pas > generated_code/case_2.MEPA
	cat generated_code/case_2.MEPA
case_all: case_1 case_2

semantic_error_1:
	- java -cp . Pascal < examples/semantic_errors/semantic_error_1.pas
semantic_error_2:
	- java -cp . Pascal < examples/semantic_errors/semantic_error_2.pas
semantic_error_3:
	- java -cp . Pascal < examples/semantic_errors/semantic_error_3.pas
semantic_error_4:
	- java -cp . Pascal < examples/semantic_errors/semantic_error_4.pas
semantic_error_all: semantic_error_1 semantic_error_2 semantic_error_3 semantic_error_4

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

all_tests: tests case_all semantic_error_all sintatical_error_all lexical_error_1

clean:
	rm *.java && rm *.class

apply_patches:
	cp patches/* .

precompile_javacc:
	javacc pascal.jj

build_precompiled_javacc:
	javac -cp . Pascal.java

build_rs:
	javac -cp . RS.java


test_st:
	javac -cp . Type.java
	javac -cp . Symbol.java
	javac -cp . -Xlint SymbolTable.java
	javac -cp /usr/local/jar/junit4.9b2/junit-4.9b2.jar:. SymbolTableTest.java
	java -cp /usr/local/jar/junit4.9b2/junit-4.9b2.jar:. org.junit.runner.JUnitCore SymbolTableTest | color-junit

compile_st:
	javac -cp . Type.java
	javac -cp . Symbol.java
	javac -cp . -Xlint SymbolTable.java
compile_rs:
	javac -cp . RS.java

compile_cw:
	javac -cp . CodeWriter.java

test_8_4:
	java -cp . Pascal < examples/correct/8_4.pas > generated_code/8_4.MEPA
	cat generated_code/8_4.MEPA

test_8_5:
	java -cp . Pascal < examples/correct/8_5.pas > generated_code/8_5.MEPA
	cat generated_code/8_5.MEPA

test_8_9:
	java -cp . Pascal < examples/correct/8_9.pas > generated_code/8_9.MEPA
	cat generated_code/8_9.MEPA

test_8_11:
	java -cp . Pascal < examples/correct/8_11.pas > generated_code/8_11.MEPA
	cat generated_code/8_11.MEPA

test_8_13:
	java -cp . Pascal < examples/correct/8_13.pas > generated_code/8_13.MEPA
	cat generated_code/8_13.MEPA

test_8_15:
	java -cp . Pascal < examples/correct/8_15.pas > generated_code/8_15.MEPA
	cat generated_code/8_15.MEPA

test_8_21:
	java -cp . Pascal < examples/correct/8_21.pas > generated_code/8_21.MEPA
	cat generated_code/8_21.MEPA

tests: test_8_4 test_8_5 test_8_9 test_8_11 test_8_13 test_8_15 test_8_21
