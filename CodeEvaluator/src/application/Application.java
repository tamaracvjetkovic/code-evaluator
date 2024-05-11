package application;

import model.evaluation.CodeComplexityEvaluator;
import model.evaluation.CodeStyleValidator;


public class Application {

    private String directoryName;
    private CodeComplexityEvaluator codeEvaluator;
    private CodeStyleValidator codeValidator;

    public Application() { }

    public Application(String directoryName) {
        this.directoryName = directoryName;
        this.codeEvaluator = new CodeComplexityEvaluator(directoryName);
        this.codeValidator = new CodeStyleValidator();
    }

    public void run() {
        runCodeComplexityEvaluator();
        runCodeStyleChecker();
    }

    private void runCodeComplexityEvaluator() {
        System.out.println("\n -- Analyzing directory " + directoryName + "...");
        codeEvaluator.analyzeDirectory();

        System.out.println("\nRESULTS 1");
        System.out.println("Top three methods with the highest complexity score:\n");
        codeEvaluator.printEvaluationResults();
    }

    private void runCodeStyleChecker() {
        codeValidator.methodNames = codeEvaluator.getMethodNames();

        System.out.println("\n -- Running code style check... ");
        codeValidator.checkNamingConvention();

        System.out.println("\nRESULTS 2");
        System.out.println("Percentage of methods that do not adhere to the specified naming convention:\n");
        codeValidator.printStyleCheckResults();
    }
}