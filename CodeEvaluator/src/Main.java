import application.Application;


public class Main {

    public static void main(String[] args) {

        String directoryName = "../JavaFiles";

        Application application = new Application(directoryName);
        application.run();
    }
}