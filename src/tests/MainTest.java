import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by halleyfroeb on 9/29/16.
 */
public class MainTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test") ;
        Main.createTables(conn);
        return conn;
    }

    @Test // insertUser and selectUsers
    public void testUser() throws SQLException{
        Connection conn = startConnection();
        Main.insertUser(conn, "Charlie", "23 cherry st", "dude@dude.com");//test data put into database
        Main.insertUser(conn, "halley", "22 skyline dr", "hot@hotmail.com");
        ArrayList<User> users = Main.selectUsers(conn); // pull out of database
        conn.close(); // only do in tests
        assertTrue(users.size()== 2);
    }

    @Test // updateUser
    public void testUpdateUser() throws SQLException{
        Connection conn = startConnection();
        Main.insertUser(conn, "Charlie", "23 cherry st", "dude@dude.com");
        Main.insertUser(conn, "halley", "22 skyline dr", "hot@hotmail.com");
        ArrayList<User> users = Main.selectUsers(conn);
        User user = users.get(1);
        assertTrue(user.userName.equals("halley"));
        Main.updateUser(conn, 2, "halleynew", "25 skyline dr", "hotmail");
        ArrayList<User> users1 = Main.selectUsers(conn);
        User user1 = users1.get(1);
        conn.close();
        assertTrue(user1.userName.equals("halleynew"));
    }
    @Test // deleteUser
    public void testDeleteUser() throws SQLException{
        Connection conn = startConnection();
        Main.insertUser(conn, "Charlie", "23 cherry st", "dude@dude.com");
        Main.insertUser(conn, "halley", "22 skyline dr", "hot@hotmail.com");
        Main.deleteUser(conn, 1);
        ArrayList<User> users = Main.selectUsers(conn);
        conn.close();
        assertTrue(users.size() == 1);
    }


}
