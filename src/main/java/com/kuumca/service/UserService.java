package com.kuumca.service;

import com.kuumca.dao.UserDAO;
import com.kuumca.model.User;

public class UserService {
    public static Integer saveUser(User user){
        if(UserDAO.isExist(user.getEmail())){
            return 0;
        }else {
            return UserDAO.saveUser(user);
        }
        
    }
}
