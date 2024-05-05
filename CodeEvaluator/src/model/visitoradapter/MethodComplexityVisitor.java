package model.visitoradapter;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
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
        visitForLoopsInNode(n, methodsComplexity);
    }

    public void visitForLoopsInNode(Node node, HashMap<String, Integer> methodsComplexity) {
        List<Node> childrenNodes = node.getChildrenNodes();

        for (Node child : childrenNodes) {
            if (child.getClass() == BlockStmt.class || child.getClass() == ForStmt.class) {
                if (child.getClass() == ForStmt.class) {
                    addOneScore(methodsComplexity);
                }
                visitForLoopsInNode(child, methodsComplexity); // Recursively call the function for nested loops
            }
        }
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