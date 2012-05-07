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

clean:
	rm *.java && rm *.class

apply_patches:
	cp patches/* .

precompile_javacc:
	javacc pascal.jj

build_precompiled_javacc:
	javac -cp . Pascal.java
