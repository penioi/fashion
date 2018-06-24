package com.demo.fashion.test;

import com.demo.fashion.users.login.LoginController;
import com.demo.fashion.users.signup.SignUpRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {


    @Autowired
    private LoginController loginController;

    @Test
    public void  itWorks(){

    }

    @Test
    public void testLoginController() {
        Assert.assertNotNull(loginController);
    }

    @Test
    public void testRegisterUser() {

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("testUser@gmial.com");
        signUpRequest.setName("Perko");
        signUpRequest.setUsername("perko");
        signUpRequest.setPassword("secred123");

       ResponseEntity responseEntity =  loginController.registerUser(signUpRequest);
       Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.CREATED);

    }





}
