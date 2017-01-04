package database;

// DB için herkes yaptýðý tüm deðiþiklikleri birbirine iletmeli, herkesin localinde tutuluyor.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	 
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    /* Specify your database url here */
    private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    /* Specify your username here */
    private static final String USERNAME = "burcusayinn@gmail.com";
    /* Specify your password here */
    private static final String PASSWORD = "8560477Oracle-";
    


    /* An example function to modify the database */
    public static void addNewVoter(){
          Connection connection = null;
          Statement statement = null;
          try {
                 Class.forName(JDBC_DRIVER);
                 connection = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
                 statement = connection.createStatement();

                 /* Specify your query here */
                 /* Please note that "statement.executeUpdate()" method can only be used for INSERT, UPDATE AND DELETE operations */
                 /* Please remind that there is no control for entering same info for Voters, only the ID is a primary key. Hence, be careful while adding new rows to tables. */
                 int result = statement.executeUpdate("INSERT INTO Voter (voterID , name, email, birthdate, certificateName) VALUES (1, 'Burcu Sayin', 'burcusayin@iyte.edu.tr', TO_DATE('24-02-1992','DD-MM-YYYY'), 'burcusayin.txt')");
                 if(result > 0)
                 {
                	 /* Specify your feedback here */
                     System.out.println("New row is inserted to Voter table successfully!");
                 }
                 else
                 {
                	 System.out.println("Error occured while adding a new Voter");
                 }
          } catch (SQLException sqlEx) {
                 sqlEx.printStackTrace();
                 System.exit(1);
          } catch (ClassNotFoundException clsNotFoundEx) {
                 clsNotFoundEx.printStackTrace();
                 System.exit(1);
          } finally {
                 try {
                        statement.close();
                        connection.close();
                 } catch (Exception e) {
                        System.exit(1);
                 }
          }
    }
    
    /* An example function to query a known value on database */
    public static void selectAVoter(){
          Connection connection = null;
          Statement statement = null;
          try {
                 Class.forName(JDBC_DRIVER);
                 connection = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
                 statement = connection.createStatement();
                 
                 ResultSet rs;
                 
                 rs = statement.executeQuery("SELECT name FROM VOTER WHERE VOTERID = 1");
                 while ( rs.next() ) {
                     String name = rs.getString("name");
                     System.out.println(name);
                 }

                 
          } catch (SQLException sqlEx) {
                 sqlEx.printStackTrace();
                 System.exit(1);
          } catch (ClassNotFoundException clsNotFoundEx) {
                 clsNotFoundEx.printStackTrace();
                 System.exit(1);
          } finally {
                 try {
                        statement.close();
                        connection.close();
                 } catch (Exception e) {
                        System.exit(1);
                 }
          }
    }
    
    /* An example function to query an unknown value on database */
    /* Example for using PreparedStatement */
    public static void selectRecordFromVoter(){
          Connection dbConnection = null;
          
          PreparedStatement preparedStatement = null;

  		String selectSQL = "SELECT NAME, EMAIL FROM VOTER WHERE VOTERID = ?";

  		try {
  			dbConnection = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
  			preparedStatement = dbConnection.prepareStatement(selectSQL);
  			//Set your unknown value here. First argument is the index of ? in SQL command. Here, we have only one ? and so I want to set the first ?
  			//Second parameter specifies the value for ?
  			preparedStatement.setInt(1, 1);

  			// execute select SQL stetement
  			ResultSet rs = preparedStatement.executeQuery();

  			while (rs.next()) {

  				String name = rs.getString("NAME");
  				String email = rs.getString("EMAIL");

  				System.out.println("userid : " + name);
  				System.out.println("username : " + email);

  			}

  		} catch (SQLException e) {

  			System.out.println(e.getMessage());

  		} finally {

  			if (preparedStatement != null) {
  				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			}

  			if (dbConnection != null) {
  				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			}

  		}

    }
}