package com.example.login.data;

import com.example.login.data.model.LoggedInUser;
import com.example.login.data.model.NewUser;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    public Result<LoggedInUser> register(NewUser newUser) {

        try {
            //TODO : Add the user to the database
            if (!validUsers.containsKey(newUser.getUserName())) {
                validUsers.put(
                        newUser.getUserName(),
                        new Data(
                                newUser.getUserName(),
                                newUser.getPassword(),
                                newUser.getFirstLastName(),
                                newUser.getEmail(),
                                newUser.getPhoneNumber()));
                Data user = validUsers.get(newUser.getUserName());
                LoggedInUser loggedInUser = new LoggedInUser(user.UserId, user.UserName);
                return new Result.Success<>(loggedInUser);
            } else {
                return new Result.Error(new Exception());
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public Result<LoggedInUser> login(String username, String password) {

        try {
            if (validUsers.containsKey(username) && validUsers.get(username).Password.equals(password)) {
                Data user = validUsers.get(username);
                LoggedInUser loggedInUser = new LoggedInUser(user.UserId, user.UserName);
                return new Result.Success<>(loggedInUser);
            } else {
                return new Result.Error(new Exception());
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    Map<String, Data> validUsers = new HashMap<String, Data>() {{
        put("rachana", new Data("rachana", "password", "Rachana Mandal", "rachana@gmail.com", "4254254254"));
        put("user1", new Data("user1", "password", "User1 LastName", "user1@gmail.com", "42512212234"));
    }};

    private class Data{
        public String UserId;
        public String UserName;
        public String Password;
        public String FirstLastName;
        public String Email;
        public String PhoneNumber;

        public Data(String userName, String password, String firstLastName, String email, String phoneNumber)
        {
            this.UserId = java.util.UUID.randomUUID().toString();
            this.UserName = userName;
            this.Password = password;
            this.FirstLastName = firstLastName;
            this.Email = email;
            this.PhoneNumber = phoneNumber;
        }
    }
}
