# Command Console
This project is written in Java and uses gradle for build.

####Build Steps:
chmod +x gradlew

./gradlew clean build  
It will run all the test cases and build jar file under build/libs

####Execution Steps:
java -jar build/libs/console-0.0.1-SNAPSHOT.jar

It will ask for user name to start a new terminal like below.
Please enter your username to open terminal:<UserName>

You can run any command on terminal now, Please run help command to get list of commands available and run help [command] name for further help. 

####Highlights
Used Command and Singleton design pattern

Followed Single responsibility, Open Close and Interface segregation principle 
