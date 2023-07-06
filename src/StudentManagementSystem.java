import java.io.Console;

import dbconnection.Commands;



public class StudentManagementSystem {
    private boolean running = true;
    private Commands database;
    private Console console;

    public StudentManagementSystem() {
        this.database = new Commands();
        this.console = System.console();
    }

    public static void main(String[] args) {
        StudentManagementSystem inputSystem = new StudentManagementSystem();

        while (inputSystem.getAppState()) {
            String input = inputSystem.console.readLine("Enter command: ");
            inputSystem.InputHandler(input);
        }
    }





    private void InputHandler(String inputStr) {
        System.out.println(inputStr);
        switch (inputStr) {
            case "ADD": 
                this.running = false;
            case "UPDATE":
                this.running = false;
            case "DELETE":
                this.running = false;
            case "SEARCH":
                this.running = false;
        }
    }

    private boolean getAppState() {
        return this.running;
    }
}