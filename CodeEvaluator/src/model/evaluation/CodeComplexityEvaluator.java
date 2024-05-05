package model.evaluation;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import filemanagement.FilesManager;
import model.visitoradapter.MethodVisitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CodeComplexityEvaluator  {

    private final FilesManager fileManager;
    public HashMap<String, Integer> methodsComplexity;
    public ArrayList<EvaluationResult> results;

    public CodeComplexityEvaluator(String directoryName) {
        this.fileManager = new FilesManager(directoryName);
        this.methodsComplexity = new HashMap<>();
        this.results = new ArrayList<>();
    }

    public void analyzeDirectory() {
        for (File file : fileManager.getLoadedFiles()) {
            analyzeFile(file);
        }
        loadEvaluationResults();
    }

    private void analyzeFile(File file) {
        CompilationUnit cu;
        try {
            cu = JavaParser.parse(file);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }

        new MethodVisitor().visit(cu, methodsComplexity);
    }

    private void loadEvaluationResults() {
        for (String methodName : methodsComplexity.keySet()) {
            int complexityScore = methodsComplexity.get(methodName);
            EvaluationResult result = new EvaluationResult(methodName, complexityScore);

            results.add(result);
            results.sort((result1, result2) -> Integer.compare(result2.complexityScore, result1.complexityScore));
        }
    }

    public void printEvaluationResults() {
        for (int i = 0; i < 3; i++) {
            System.out.println(results.get(i).toString());
        }
    }

    public ArrayList<String> getMethodNames() {
        return new ArrayList<>(methodsComplexity.keySet());
    }
}