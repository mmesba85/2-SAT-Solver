all:
	javac src/*.java -d tests

clean:
	rm -rf tests/*.class