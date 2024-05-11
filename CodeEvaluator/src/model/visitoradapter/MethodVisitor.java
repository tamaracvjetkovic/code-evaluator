package model.visitoradapter;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.List;


public class MethodVisitor extends VoidVisitorAdapter<Object> {
    private final HashMap<String, Integer> methodsComplexity;
    private MethodDeclaration methodDeclarationNode;
    private String methodName;

    public MethodVisitor(HashMap<String, Integer> methodsComplexity) {
        this.methodsComplexity = methodsComplexity;
    }

    @Override
    public void visit(MethodDeclaration node, Object arg) {
        setClassData(node);

        countMethod();
        visitStatements();
    }

    private void setClassData(MethodDeclaration node) {
        this.methodDeclarationNode = node;
        this.methodName = node.getName();
    }

    private void countMethod() {
        methodsComplexity.put(methodName, 0);
    }

    private void visitStatements() {
        new MethodComplexityVisitor(methodName).visit(methodDeclarationNode, methodsComplexity);
    }
}