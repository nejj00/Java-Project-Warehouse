package bg.tuvarna.sit.example.presentation.models;

public class HelloModel {
    private final String welcomeMessage;

    public HelloModel(){
        this.welcomeMessage="Hello, I am your java application project";
    }

    public String getWelcomeMessage(){
        return this.welcomeMessage;
    }
}
