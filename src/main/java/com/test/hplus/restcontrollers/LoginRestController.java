package com.test.hplus.restcontrollers;

import com.test.hplus.beans.Login;
import com.test.hplus.beans.User;
import com.test.hplus.exceptions.LoginFailiureException;
import com.test.hplus.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/hplus/rest/loginUser")
    public ResponseEntity loginUser(@RequestBody Login login) throws LoginFailiureException {
        System.out.println("In Login Rest Controller");
        User user = userRepository.getUserByName(login.getUsername());
        if (null==user)
        {
//            return ResponseEntity.status(404).build();
            return new ResponseEntity("User Not Found",HttpStatus.NOT_FOUND);
        }

        if (login.getUsername().equals(user.getUsername())&&login.getPassword().equals(user.getPassword()))
        {
            return new ResponseEntity("Welcome,"+user.getUsername(),HttpStatus.OK);
        }
        else
        {
            throw  new LoginFailiureException("Invalid Username or Password");

        }
    }
}
