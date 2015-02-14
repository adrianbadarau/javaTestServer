package exercise1;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by adrian on 13.02.2015.
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(DbBroker.getString("select name, password from users where id=1"));
            System.out.println(DbBroker.getInt("select id from users where name ='root'"));
            DbBroker.getQuerry("select * from users");
            ArrayList<String[]> rez;
            rez = DbBroker.getArray("select * from users");
            for(String[] row : rez){
                for(String elem : row){
                    System.out.print(elem);
                }
                System.out.println();
            }
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        MyServer.start(9090);

    }
}
