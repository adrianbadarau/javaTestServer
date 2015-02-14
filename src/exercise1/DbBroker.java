package exercise1;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by adrian on 13.02.2015.
 */
public class DbBroker {
    static Connection connection =null;
    static void connectDB() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/JAVA-TEST-DB","root","");
        
    }
    static String getString(String userQuery) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        connectDB();
        Statement query = connection.createStatement();
        ResultSet qRezult = query.executeQuery(userQuery);
        qRezult.next();
        String result = qRezult.getString("name");
        closeDB();
        return result;
    }
    static int getInt(String UserQuery) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connectDB();
        Statement query = connection.createStatement();
        ResultSet qRez = query.executeQuery(UserQuery);
        qRez.next();
        int result = qRez.getInt(1);
        closeDB();
        return result;
    }
    static void getQuerry(String userQuery) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connectDB();
        Statement query =connection.createStatement();
        System.out.println(query.execute(userQuery));
        closeDB();
    }
    static ArrayList<String[]> getArray(String userQuery) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connectDB();
        Statement query = connection.createStatement();
        ResultSet qRez = query.executeQuery(userQuery);
        int colCount = qRez.getMetaData().getColumnCount();
        ArrayList<String[]> rezult = new ArrayList<String[]>();
        while(qRez.next()){
            String[] row = new String[colCount];
            for(int i=0;i<colCount;i++){
                row[i] = qRez.getString(i+1);
            }
            rezult.add(row);
        }
        closeDB();
        return rezult;
    }

    private static void closeDB() throws SQLException {
        connection.close();
    }
}
