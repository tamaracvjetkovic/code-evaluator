package model.visitoradapter;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;


public class MethodVisitor extends VoidVisitorAdapter<HashMap<String, Integer>> {

    @Override
    public void visit(MethodDeclaration n, HashMap<String, Integer> methodsComplexity) {
        String methodName = n.getName();
        methodsComplexity.put(methodName, 0);
        
        new MethodComplexityVisitor(methodName).visit(n, methodsComplexity);
    }
}


