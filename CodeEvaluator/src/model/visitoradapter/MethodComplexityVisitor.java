package model.visitoradapter;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class MethodComplexityVisitor extends VoidVisitorAdapter<HashMap<String, Integer>> {

    private final String methodName;

    public MethodComplexityVisitor(String methodName) {
        this.methodName = methodName;
    }

    private boolean isIfStatement(Node node) {
        return node.getClass() == IfStmt.class;
    }

    private boolean isSwitchStatement(Node node) {
        return node.getClass() == SwitchStmt.class;
    }

    private boolean isForStatement(Node node) {
        return node.getClass() == ForStmt.class;
    }

    private boolean isForeachStatement(Node node) {
        return node.getClass() == ForeachStmt.class;
    }

    private boolean isWhileStatement(Node node) {
        return node.getClass() == WhileStmt.class;
    }

    private boolean isDoStatement(Node node) {
        return node.getClass() == DoStmt.class;
    }

    public void addOneScore(HashMap<String, Integer> methodsComplexity) {
        int currentComplexity = methodsComplexity.get(methodName);
        methodsComplexity.put(methodName, currentComplexity + 1);
    }

    @Override
    public void visit(ForStmt node, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
        visitAllStatements(node, methodsComplexity);
    }

    @Override
    public void visit(ForeachStmt node, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
        visitAllStatements(node, methodsComplexity);
    }

    @Override
    public void visit(IfStmt node, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);

        Optional<Statement> elseStmt = Optional.ofNullable(node.getElseStmt());
        boolean hasElseStatementAfterIf = elseStmt.isPresent();

        if (hasElseStatementAfterIf) {
            Statement elseStatement = elseStmt.get();
            boolean containsIfStatement = elseStatement instanceof IfStmt;

            if (!containsIfStatement) {
                addOneScore(methodsComplexity);
            }
        }

        visitAllStatements(node, methodsComplexity);
    }

    @Override
    public void visit(SwitchStmt node, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
        visitAllStatements(node, methodsComplexity);
    }

    @Override
    public void visit(WhileStmt node, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
        visitAllStatements(node, methodsComplexity);
    }

    @Override
    public void visit(DoStmt node, HashMap<String, Integer> methodsComplexity) {
        addOneScore(methodsComplexity);
        visitAllStatements(node, methodsComplexity);
    }

    public void visitAllStatements(Node node, HashMap<String, Integer> methodsComplexity) {
        List<Node> childrenNodes = node.getChildrenNodes();

        for (Node childNode : childrenNodes) {
            if (isIfStatement(childNode)) visit((IfStmt) childNode, methodsComplexity);
            else if (isSwitchStatement(childNode)) visit((SwitchStmt) childNode, methodsComplexity);
            else if (isForStatement(childNode)) visit((ForStmt) childNode, methodsComplexity);
            else if (isForeachStatement(childNode)) visit((ForeachStmt) childNode, methodsComplexity);
            else if (isWhileStatement(childNode)) visit((WhileStmt) childNode, methodsComplexity);
            else if (isDoStatement(childNode)) visit((DoStmt) childNode, methodsComplexity);
            else {
                visitAllStatements(childNode, methodsComplexity);
            }
        }
    }
}