package com.codebygaurav.texttime.storage;

import org.apache.catalina.User;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {

    private static UserStorage userStorage;
    private HashSet<String> users;
    public UserStorage() {
        users = new HashSet<>();
    }

    public static synchronized UserStorage getInstance(){
        if(userStorage == null){
            userStorage = new UserStorage();
        }
        return userStorage;
    }

    public Set<String> getUsers(){
        return users;
    }

    public void setUsers(String userName) throws Exception {
        if (users.contains(userName)) {
            throw new Exception("User already exists with Username : "+userName);
        }
        users.add(userName);
    }
}
