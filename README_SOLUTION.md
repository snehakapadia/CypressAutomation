I have created a BDD Cucumber framework for the given tasks.
You can go to config.properties and mention the browser that you want to run tests on, for now I have configured Chrome and Firefox.
Below are paths for important folders:
Feature Files: FeatureFiles
StepDefinations: src/test/java/TestAutomation/TestAutomation
TestRunner: src/test/java/TestAutomation/TestAutomation
I have pom.xml for dependency injection. You can find the pom.xml in the root directory of the project.
Execution Reports will be generated in 'target/cucumber/index.html' (The report is a basic cucumber html report, it can be beautified through plugins).

To Run the tests:
1. Git clone the project on your machine to the desired location.
2. On the Terminal, go to the root directory of the project and execute 'mvn test'. Optionally, we can also execute the TestRunner.java class to execute the tests.

Note: The tests will run in headless mode. If you want to execute the tests in head mode you need to change line number 79/line number 91 (based on the browser selected from config.properties) in Stepdefinations.java to 'options.addArguments("start-maximized"); '
Also, Username and Password as of now I have driven through feature file, however we can take from an external source and also encrypt/decrypt the password as well. I havent done it now due to time constraints.

Description of 2 Testcases:
1st Testcase:

1. Login to Trello
2. Add a card
3. Move the card across all the lists (To Do/Doing/Done) and verify the movement.

2nd Testcase:

1. 1. Login to Trello
2. Add a card
3. Edit the card and add label
4. Verify if the label is getting added correctly.

Reason for choosing the above 2 testcases
I have choosen these 2 testcases as I thought these 2 are most critical and most used functionalities, Card addition and movement across all the list. Also, according to me label/description addition is an important testcase.