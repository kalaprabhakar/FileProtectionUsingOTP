package com.kuumca.view;

import java.util.Scanner;

public class UserView {
    String email;
    UserView(String email){
        this.email=email;
    }

    public void home(){
        do {
            System.out.println("Welcome : " + this.email);
            System.out.println("press 1 to show hidden files");
            System.out.println("press 2 to hide a new file");
            System.out.println("press 3 to un-hide a file");
            System.out.println("press 0 to exit");
            Scanner scanner= new Scanner(System.in);
            int ch =Integer.parseInt(scanner.nextLine());
            switch (ch){


            }
        }while (true);

    }
}
