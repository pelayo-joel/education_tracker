package dbconnection;

import java.sql.*;



public class CRUD {

    private Connection connection = null;
    private PreparedStatement sqlStatement = null;
    private ResultSet statementResult = null;



    public CRUD() {
        String url = "jdbc:mysql://localhost:3306/education_tracker";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, "root", "sqlroot");
            System.out.println("Connected: " + this.connection);
        } catch (Exception e) {
            System.out.println(e);
        }
    }





    //Create Operations

    public void NewStudent(String firstName, String name, int age) throws SQLException {
        String createQuery = "INSERT INTO students (nom, prenom, age) VALUES (?, ?, ?);";
        this.sqlStatement = this.connection.prepareStatement(createQuery);

        this.sqlStatement.setString(1, firstName);
        this.sqlStatement.setString(2, name);
        this.sqlStatement.setInt(3, age);

        this.statementResult = this.sqlStatement.executeQuery();
    }

    public void NewGrade(int grade, int id) throws SQLException {
        String createQuery = "INSERT INTO notes (notes, student_id) VALUES (?, ?);";
        this.sqlStatement = this.connection.prepareStatement(createQuery);

        this.sqlStatement.setInt(1, grade);
        this.sqlStatement.setInt(2, id);

        this.statementResult = this.sqlStatement.executeQuery();
    }



    //Read Operations

    public ResultSet ReadAll() throws SQLException {
        String readQuery = "SELECT * FROM students;";
        this.sqlStatement = this.connection.prepareStatement(readQuery);

        this.statementResult = this.sqlStatement.executeQuery();
        return this.statementResult;
    }

    public ResultSet SortedRead(String sortingType) throws SQLException {
        String readQuery = "SELECT * FROM students ORDER BY ? ASC;";
        this.sqlStatement = this.connection.prepareStatement(readQuery);

        this.sqlStatement.setString(1, sortingType);

        this.statementResult = this.sqlStatement.executeQuery();
        return this.statementResult;
    }

    public ResultSet AdvancedRead(String compType, String value) throws SQLException {
        String readQuery;

        if (compType.equals("age") || compType.equals("nom") || compType.equals("prenom") || compType.equals("prenom")) {
            readQuery = "SELECT * FROM students WHERE ?=?;";
        }
        else {
            readQuery = "SELECT * FROM students INNER JOIN notes ON notes.student_id = students.id WHERE AVG(?) > ?;";
        }
        this.sqlStatement = this.connection.prepareStatement(readQuery);

        this.sqlStatement.setString(1, compType);
        this.sqlStatement.setString(2, value);

        this.statementResult = this.sqlStatement.executeQuery();
        return this.statementResult;
    }



    //Update Operations

    public void UpdateStudent(String column, String newValue) throws SQLException {
        String updateQuery = "UPDATE students SET ?=?;";
        this.sqlStatement = this.connection.prepareStatement(updateQuery);

        this.sqlStatement.setString(1, column);
        this.sqlStatement.setString(2, newValue);

        this.statementResult = this.sqlStatement.executeQuery();
    }

    public void UpdateGrade(int newGrade, int studentId) throws SQLException {
        String updateQuery = "UPDATE notes INNER JOIN students ON notes.student_id = students.id SET note=? WHERE id=?;";
        this.sqlStatement = this.connection.prepareStatement(updateQuery);

        this.sqlStatement.setInt(1, newGrade);
        this.sqlStatement.setInt(2, studentId);

        this.statementResult = this.sqlStatement.executeQuery();
    }



    //Delete Operations

    public void DeleteStudent(int studentId) throws SQLException {
        String deleteQuery = "DELETE notes FROM notes INNER JOIN students ON notes.student_id = students.id WHERE student_id=?;";
        this.sqlStatement = this.connection.prepareStatement(deleteQuery);
        this.sqlStatement.setInt(1, studentId);
        this.statementResult = this.sqlStatement.executeQuery();
        
        deleteQuery = "DELETE students FROM students WHERE id=?;";
        this.sqlStatement = this.connection.prepareStatement(deleteQuery);
        this.sqlStatement.setInt(1, studentId);
        this.statementResult = this.sqlStatement.executeQuery();
    }

    public void DeleteGrade(int studentId, String course) throws SQLException {
        String deleteQuery = "DELETE notes FROM notes INNER JOIN students ON notes.student_id = students.id WHERE student_id=? AND course=?;";
        this.sqlStatement = this.connection.prepareStatement(deleteQuery);

        this.sqlStatement.setInt(1, studentId);
        this.sqlStatement.setString(2, course);

        this.statementResult = this.sqlStatement.executeQuery();
    }
}