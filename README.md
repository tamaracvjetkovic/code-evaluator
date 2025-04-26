# CodeEvaluator 📊

A console application that analyzes methods for complexity.

---

The ``goal`` of the project was to:
- implement an efficient way to scan a large number of .java files,
- evaluate and analyze the complexity of the methods/functions,
- keep the structure and code well-organized and clean
#
Technologies used: 
- ``Java``
- ``JavaParser (library)``
#
Date: May, 2024.

---

# How does it work? ❓
- reads Java (.java) files and evaluates basic aspects of code quality,
- analyzes the methods and their complexity,
  - outputs top three methods with the highest count of conditional statements,
- checks and counts the number of method names that do not follow the camelCase naming convention,
  - outputs the percentage of methods that do not adhere to the naming convention

---

# Using the Application ⚙️

To use this application, follow the next steps:
1) clone this repo,
2) run the program in the IntelliJ IDE 

---

# Understanding the Approach 🎯

The main part of the application is the class Application, which starts and runs the CodeEvaluator and CodeValidator, the two most important classes in this project.
#
``CodeEvaluator`` is a class made for the first part of the task - "Code Complexity Evaluator":
- analyzes the passed directory and every file in it,
- after the analysis, it stores the results in a variable and prints them on the console screen.

The analysis was done with the help of JavaParser library. The JavaParser provides an Abstract Syntax Tree of the Java code. It was a very handy tool in this situation, because manually searching for every method name would probably take forever (to implement, but also to run!). The analysis itself is not difficult, as it only counts the number of conditional statements in all methods. 

Visitor Adapters are classes intended for searching the method names and every conditional statements, and they are used together with the JavaParser (also very practical, but the documentation for it was almost nonexistent).
> OUTPUT: top three methods with the highest count of conditional statements.
#
``CodeValidator`` is a class made for the second part of the task - 'Basic Code Style Check':
- analyzes all the method names in the directory and checks if their naming convention is satisfied (camelCase).

It was pretty easy task, compared to the first task. There are two functions "hasFirstLetterUpperCase" and "hasSpecialCharacter", which are the main part of this class. After the analysis, the results is stored in a variable and the percentage is printed on the console screen.
> OUTPUT: the percentage of methods that do not adhere to the specified naming convention. 
#
FileManager and DirectoryParser are additional classes that handle the file management. 

EvaluationResult is an small class that presents the result of the first task.

---

# Conclusion 📝

Even though the task description is somewhat short and looks easy at first glance, I can’t say that there wasn’t a lot to do.

Adequate number of “hidden” classes that can be implemented in order to make the code as clean as possible. At one point, I hesitated because some of my ideas for certain classes looked and felt pointless. There was a time when one class completely derailed my progress. It didn’t belong there, and unfortunately, I realized that after spending several hours pondering it.

At the end, I am quite satisfied with the overall modularity.
This project was not just a test of my overall knowledge, but also an opportunity to identify my most common mistakes and correct them.

Altough it was a short journey, it was very interesting to learn something new! This is the first time I have used the JavaParses, and before this, I didn't even know such tool even existed. Many tools are effective if you know how to use them, and I think that this project is an example of it. 
