### Requirements 

1. Java 8 (1.8.0_77)
2. Maven 3.3.9 

### Basic Instructions


#### Code Sample 1: Input Analyzer

**1. Compile and Execute with Maven**

The following commands will compile the code and execute the main method in the class code.sample.QuickRun.

Use the following command to have run the InputAnalyzer with command line arguments.   The analyzer requires three pieces of data.  The first, an integer, to indicate the type of sample code to run (1 = InputAnalyzer).  The second is the input file path, and the third is the search string for the input.  Only the first value indicating the sample code type is required at the command line.  If no additional arguments are passed at the command line, then the analyzer will use the default values set in sample.properties.  A test file (referenced in below command) has been provided with the project. The following two commands would yield the same result.

Base command that will use property file default values

`mvn compile exec:java -Dexec.mainClass=code.sample.QuickRun -Dexec.args="1"`

Command where all three arguments are entered on command line (Note that a space is used to separate each arg)

`mvn compile exec:java -Dexec.mainClass=code.sample.QuickRun -Dexec.args="1 resources/test-input.txt foo"`

To use another file and search term, simply create a text file and then use that file path and search term for the command line arguments. 

`mvn compile exec:java -Dexec.mainClass=code.sample.QuickRun -Dexec.args="1 your-input.txt your-search-term"`

or update the property file values and then use the base command 

*Note the mvn compile command will generate class files under the projects /target folder, but a jar file will not be created and test cases will not be executed*

**2. Execute with Java**

Run the following commands to clean the project and then package the code into a jar file.  

`mvn clean`

then 

`mvn package`

*Note the package command will run any test cases and then create a jar file.  Once the jar is create, execute one of the following commands.  As mentioned above you can also run the command with your own path and search term.*

Execute the main() method with command line arguments

`java -cp target/code-sample-1.0.jar code.sample.QuickRun "1" "resources/test-input.txt" "foo"`

Using your own path and search terms

`java -cp target/code-sample-1.0.jar code.sample.QuickRun -Dexec.args="1" "your-input.txt" "your-search-term"`

Execute the main() method using properties

`java -cp target/code-sample-1.0.jar code.sample.QuickRun "1"`


#### Code Sample 2: File Scanner

**1. Compile and Execute with Maven**

The following commands will compile the code and execute the main method in the class code42.sample.QuickRun.

Use the following command to run the FileScanner with command line arguments.  The scanner requires two pieces of data.  The first will indicate the type of sample code to run (2 = File Scanner ).  The second argument is the directory path to scan.  Only the first value indicating the sample code type is required at the command line.  If no additional arguments are passed at the command line, then the scanner will use the default directory set in sample.properties.  

The following two commands would yield the same result…

Base command that will use property file default values

`mvn compile exec:java -Dexec.mainClass=code.sample.QuickRun -Dexec.args="2"`

Command where both arguments are entered on command line (Note that a space is used to separate each arg)

`mvn compile exec:java -Dexec.mainClass=code.sample.QuickRun -Dexec.args="2 target"`

To use another directory, replace your path here with the path of choice.

`mvn compile exec:java -Dexec.mainClass=code.sample.QuickRun -Dexec.args="2 your_path_here"`

*Note this command will generate class files under the projects /target folder, but a jar file will not be created and test cases will not be executed.*

**2. Execute with Java**

Run the following commands to clean the project and then package the code into a jar file.  

`mvn clean`

then 

`mvn package`

*Note the package command will run any project test cases and then create a jar file.  Once the jar is created, execute one of the following commands.  As mentioned above you can also run the command with your own directory path.*

Execute the FileScanner with command line arguments. 

`java -cp target/code-sample-1.0.jar code.sample.QuickRun "2" "your_path_here"`

Execute FileScanner using properties

`java -cp target/code-sample-1.0.jar code.sample.QuickRun "2"`

#### Code Sample 3: Multiplier

**1. Compile and Execute with Maven**

The following commands will compile the code and execute the main method in the class code42.sample.QuickRun.

Use the following command to run the Math Multiplier with command line arguments.  The multiplier requires three pieces of data.  The first will indicate the type of sample code to run (3 = Multiplier).  The second and third arguments are the values to be multiplied.  All arguments must be provided.

Sample command

`mvn compile exec:java -Dexec.mainClass=code.sample.QuickRun -Dexec.args="3 100 -4"`

**2. Execute with Java**

Run the following commands to clean the project and then package the code into a jar file.  

`mvn clean`

then 

`mvn package`

*Note the package command will run any project test cases and then create a jar file.  Once the jar is created, execute the following command using your values of choice.

Execute the multiplier with command line arguments. 

`java -cp target/code-sample-1.0.jar code.sample.QuickRun "3" "your_first_val" "your_second_val"`

Example:

`java -cp target/code-sample-1.0.jar code.sample.QuickRun "3" "100" "-4"`


