
@cd src\main\java

@echo off
IF not exist ../../../classes ( mkdir ../../../classes && echo classes directory created ) 

javac com/test/log/extractor/ExceptionTraceExtractor.java -d ../../../classes

cd ../../../classes


jar cfm ..\ExceptionTraceExtractor.jar ..\manifest.txt com/test/log/extractor/ExceptionTraceExtractor.class

@cd ../
echo ExceptionTraceExtractor.jar created successfully.
@cmd /k
