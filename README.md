AUTHOR: Tamara Cvjetković
# 


# Name of the project
CodeEvaluator: application that analyzes methods for complexity
# 


# INFORMATIONS ABOUT THE AUTHOR
- NAME: Tamara Cvjetković
- STUDENT INDEX: sv48-2022
- FACULTY: Faculty of Technical Science, Novi Sad
- STUDIES: Software Engineering and Information Technologies 
- ACADEMIC DEGREE: Bachelor with Honours in Software Engineering
  
- CONTACT: c.tamara02@gmail.com || cvjetkovic.sv48.2022@uns.ac.rs
- PHONE: +381 65 4468 114 (Serbia) || +387 66 868 639 (BiH)
# 


# Project description
Project for a qualification for the JetBrains internship 2024.

CodeEvaluator is a console application that can read Java (.java) files and evaluates basic aspects of code quality. 

First part of the task was analysis of the methods and their complexity.
The analysis is not difficult, as it only counts the number of conditional statements in all methods.
The output is top three methods with the highest count of conditional statements.

Second part of the task was basic code style check, where the application performs a simple
code style check and counts the number of method names that do not follow the camelCase naming convention.
The output is the percentage of methods that do not adhere to the specified naming convention.
# 


# For the development of the application, the following tools were utilized:
- Java 
- JavaParser (tool/library)
- IntelliJ IDEA (IDE)
# 


# How to start the application:
After downloading the ZIP and extracting files, from the main folder (which consists two folders and a README.md),
open only the 'CodeEvaluator' folder in a Java IDE. 
The application can be started from the Main.java file, located at 'CodeEvaluator/src/Main.java'.
#


# Brief description of the approach
I focused my mind on the three things already listed in the project description: ‘Functionality’, ‘Code quality’ and ‘Learning and initiative’.

#
The main part of the application (after the Main.java which makes an instance of the Application) is the class Application, which starts and runs the CodeEvaluator and CodeValidator, the two most important classes in this project.

CodeEvaluator is a class made for the first part of the task: 'Code complexity evaluator'. It analyzes the passed directory and every file in it. After the analysis, it stores the results in a variable and prints them on the console screen.

The analysis was done with the help of JavaParser library. The JavaParser provides an Abstract Syntax Tree of the Java code.
It was a very handy tool in this situation. I chose it because brute force and manually searching for every method name would probably take forever (to implement, but also to run!). I had some ideas to implement an efficient parser, but there would be too many edge cases that I would definitely oversee.

Visitor Adapters are classes intended for searching the method names and every conditional statements, and they are used together with the JavaParser (also very practical, but the documentation for it was almost nonexistent).

CodeValidator is a class made for the second part of the task: 'Basic code style check'. It analyzes all the method names in the directory and checks if their naming convention is satisfied (camelCase). It was pretty easy task, compared to the first task. I wrote manually two functions "hasFirstLetterUpperCase" and "hasSpecialCharacter", which are the main part of this class. After the analysis, the results is stored in a variable and the percentage is printed on the console screen.

FileManager and DirectoryParser are additional classes that handle the file management. 
EvaluationResult is an small class that presents the result of the first task.
#


Even though the task description is somewhat short and looks easy at first glance, I can’t say that there wasn’t a lot to do.

Adequate number of “hidden” classes that can be implemented in order to make the code as clean as possible. At one point, I hesitated because some of my ideas for certain classes looked and felt pointless. There was a time when one class completely derailed my progress. It didn’t belong there, and unfortunately, I realized that after spending several hours pondering it.

At the end, I am quite satisfied with the overall modularity.

An interesting thing is that, in this year of college, we have a subject called “Introduction to the Software Engineering”, and it just happened that we are currently learning the principles of the clean code.	
This project was not just a test of my previous knowledge, but also an opportunity to see how much material I had grasped from the lectures, and most importantly, to identify my most common mistakes and correct them.

Altough it was a short journey, it was very interesting to learn something new! This is the first time I have used the JavaParses, and before this, I didn't even know such tool even existed. Many tools are effective if you know how to use them, and I think that this project is an example of it. If I didn't use the library, I would probably end with something not so good, that works only in some specific situations. Even though this also isn't a perfect solution, I am atleast sure this is better than manually searching! 
#
