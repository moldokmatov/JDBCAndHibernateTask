package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
       userService.createUsersTable();
      // userService.saveUser("adidas", "sports", (byte) 5);
//userService.getAllUsers();
       // userService.removeUserById(1);
      //  userService.cleanUsersTable();
      //  userService.dropUsersTable();
    }
}
