package model.evaluation;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class CodeStyleValidator {

    public ArrayList<String> methodNames;
    private int validationResult;

    public CodeStyleValidator() {
        methodNames = new ArrayList<>();
    }

    public void checkNamingConvention() {
        for (String methodName : methodNames) {
            checkMethodName(methodName);
        }
    }

    private void checkMethodName(String methodName) {
        if (hasFirstLetterUpperCase(methodName) || hasSpecialCharacter(methodName)) {
            validationResult += 1;
        }
    }

    private boolean hasFirstLetterUpperCase(String methodName) {
        char firstChar = methodName.charAt(0);
        return Character.isUpperCase(firstChar);
    }

    private boolean hasSpecialCharacter(String methodName) {
        String[] upperCaseWords = methodName.split("(?=[A-Z])");

        for (String upperCaseWord : upperCaseWords) {
            String substringWord = upperCaseWord.substring(1);

            boolean isSmallLettersOnly = substringWord.matches("[a-z]+");
            if (!isSmallLettersOnly) {
                return true;
            }
        }
        return false;
    }

    public void printStyleCheckResults() {
        double result = (double) (validationResult * 100) / methodNames.size();

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(result);
        double roundedResult = Double.parseDouble(formattedNumber);

        System.out.println(roundedResult + "%");
    }
}