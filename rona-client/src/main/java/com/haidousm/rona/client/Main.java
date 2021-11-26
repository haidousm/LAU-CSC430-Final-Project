package com.haidousm.rona.client;


import com.google.gson.Gson;
import com.haidousm.rona.client.client.Client;
import com.haidousm.rona.client.controllers.UserController;
import com.haidousm.rona.common.requests.Request;
import com.haidousm.rona.common.responses.LoginResponse;
import com.haidousm.rona.common.responses.RegisterResponse;
import com.haidousm.rona.common.responses.Response;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 1234);

//        Path imagePath = Paths.get("/Users/moussa/Desktop/Fall 2021/CSC430/Project/LAU-CSC430-Final-Project/rona-client/src/main/java/com/haidousm/rona/client/profile-pic.png");
//        Request registerUserRequest = UserController.prepareRegisterUser("Moussa", "Haidous", "moussa.haidous@lau.edu", "haidousm", "123456", false, Path.of(""), imagePath);
//        client.send(registerUserRequest);
//        RegisterResponse registerResponse = new Gson().fromJson(client.receive(), RegisterResponse.class);
//        System.out.println(registerResponse);
        Request loginRequest = UserController.prepareLogin("haidousm", "123456");
        client.send(loginRequest);
        Response response = new Gson().fromJson(client.receive(), Response.class);
        LoginResponse loginResponse = new Gson().fromJson(response.getBody(), LoginResponse.class);
        System.out.println(loginResponse);
    }
}
