package kanban_board.user;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    // Post
    public User createUser(User user);

    // Post
    public Boolean validateUser(String username, String password);

    // Get One
    public User getUser(String username);

    // Get All
    public List<User> getUsers();

    // Put
    public User updateUser(User user);

    // Delete
    public void deleteUser(String username);

}
