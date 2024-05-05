package model.visitoradapter;

import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Optional;


public class MethodComplexityVisitor extends VoidVisitorAdapter<HashMap<String, Integer>> {

    private final String methodName;

    public MethodComplexityVisitor(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void visit(IfStmt n, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);

        Optional<Statement> elseStmt = Optional.ofNullable(n.getElseStmt());
        boolean hasElseStatementAfterIf = elseStmt.isPresent();

        if (hasElseStatementAfterIf) {
            addOneScore(methodsComplexity);
        }
    }

    @Override
    public void visit(ForStmt n, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
    }

    @Override
    public void visit(SwitchStmt n, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
    }

    @Override
    public void visit(WhileStmt n, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
    }

    public void addOneScore(HashMap<String, Integer> methodsComplexity) {
        int currentComplexity = methodsComplexity.get(methodName);
        methodsComplexity.put(methodName, currentComplexity + 1);
    }
}