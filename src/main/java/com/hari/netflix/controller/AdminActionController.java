package com.hari.netflix.controller;

import com.hari.netflix.dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminActionController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/v1/admin/get_all_users")
    public ResponseEntity getAllUsers(HttpServletRequest request) throws Exception {
        String pageParam = request.getParameter("page");
        int page = 0;
        if(pageParam != null && !pageParam.isEmpty()) page = Integer.valueOf(pageParam);

        return new ResponseEntity(userDAO.getAllUsersPaginated(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/admin/delete_user")
    public ResponseEntity deleteUser(HttpServletRequest request) throws Exception {
        String email = request.getParameter("email");
        if(email == null || email.isEmpty()) return new ResponseEntity("Email parameter cannot be empty", HttpStatus.BAD_REQUEST);
        userDAO.deleteUser(email);
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }

}
