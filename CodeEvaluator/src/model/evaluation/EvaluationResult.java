package model.evaluation;


public class EvaluationResult {

    public String functionName;
    public int complexityScore;

    public EvaluationResult(String functionName, int complexityScore) {
        this.functionName = functionName;
        this.complexityScore = complexityScore;
    }

    @Override
    public String toString() {
        return "Function Name: " + functionName + "\nComplexity Score: " + complexityScore + "\n";
    }
}
