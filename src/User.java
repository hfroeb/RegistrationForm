/**
 * Created by halleyfroeb on 9/29/16.
 */
public class User {
    Integer id;
    String userName;
    String address;
    String email;

    public User(Integer id, String userName, String address, String email) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.email = email;
    }

    public User() {
    }
}
