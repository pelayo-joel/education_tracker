package dbconnection;
    
import java.io.Console;



public class Commands {

    private CRUD dbConnection;
    private Console console;



    public Commands() {
        this.dbConnection = new CRUD();
        this.console = System.console();
    }





    public void AddCommand() {
        String table = this.console.readLine("Add on which table: ");

        try {
            if (table.equals("students".toLowerCase())) {
                this.dbConnection.NewStudent(null, null, 0);
            }
            else if (table.equals("notes".toLowerCase())) {
                this.dbConnection.NewGrade(0, 0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public void UpdateCommand() {
        String table = this.console.readLine("Update on which table: ");

        try {
            if (table.equals("students".toLowerCase())) {
                this.dbConnection.UpdateStudent(null, null);
            }
            else if (table.equals("notes".toLowerCase())) {
                this.dbConnection.UpdateGrade(0, 0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public void DeleteCommand() {
      String table = this.console.readLine("Delete on which table: ");

        try {
            if (table.equals("students".toLowerCase())) {
                this.dbConnection.DeleteStudent();
            }
            else if (table.equals("notes".toLowerCase())) {
                this.dbConnection.DeleteGrade(null, null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public void SearchCommand() {
        String selectMode = this.console.readLine("Delete on which table: ");

        try {
            this.dbConnection.SortedRead(null);
            this.dbConnection.ReadAll();
            this.dbConnection.AdvancedRead(null, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}