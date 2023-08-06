package com.kuumca.view;

import com.kuumca.dao.UserDAO;
import com.kuumca.model.User;
import com.kuumca.service.GenerateOTP;
import com.kuumca.service.SendOTPService;
import com.kuumca.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Welcome {
    public  void welcomeScreen(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("***************Welcome to File Protection Using OTP***************");
        System.out.println("=============================");
        System.out.println("press 1 to LOGIN");
        System.out.println("press 2 to SIGNUP");
        System.out.println("press 0 to EXIT");
        System.out.println("=============================");
        int choice=0;
        try {
             choice =Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (choice){
            case 1 -> login();
            case 2 -> signup();
            case 0 -> System.exit(0);
        }
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Email Id :");
        String email=scanner.nextLine();
        try {
            if (UserDAO.isExist(email)) {
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter The OTP :");
                String otp = scanner.nextLine();

                if (otp.equals(genOTP)) {
                    new UserView(email).home();
                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User Not Found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void signup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Name :");
        String name=scanner.nextLine();
        System.out.println("Enter Your Email");
        String email=scanner.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter The OTP :");
        String otp = scanner.nextLine();
         if(genOTP.equals(otp)){
             User user = new User(name,email);
             int response= UserService.saveUser(user);
             switch (response){
                 case 0 -> System.out.println("User Already Existed");
                 case 1 -> System.out.println("User Registered");

             }
         }else {
             System.out.println("Wrong OTP");
         }


    }
}
