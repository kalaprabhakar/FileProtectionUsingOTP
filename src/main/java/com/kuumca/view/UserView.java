package com.kuumca.view;

import com.kuumca.dao.DataDAO;
import com.kuumca.model.Data;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UserView {
    String email;
    UserView(String email){
        this.email=email;
    }

    public void home(){
        do {
            System.out.println();
            System.out.println();
            System.out.println("============================================================");
            System.out.println("***************Welcome : " + this.email+"***************");
            System.out.println("               press 1 to show hidden files              ");
            System.out.println("               press 2 to hide a new file                ");
            System.out.println("               press 3 to un-hide a file                 ");
            System.out.println("               press 4 to get back Welcome page           ");
            System.out.println("               press 0 to exit                          ");
            System.out.println("============================================================");
            System.out.println();
            System.out.println();
            Scanner scanner= new Scanner(System.in);
            int ch =Integer.parseInt(scanner.nextLine());
            switch (ch){
                case  1 ->{
                    List<Data> files = DataDAO.getAllFiles(this.email);
                    System.out.println("               ID  -  File Name               ");
                    for (Data file : files){
                        System.out.println("               "+file.getId()+  "-"  +file.getFileName()+"               ");
                    }

                }
                case 2 -> {
                    System.out.println("               Enter the file path :               ");
                    String path = scanner.nextLine();

                    File f = new File(path);
                    Data file = new Data(0, f.getName(), path,this.email);

                    DataDAO.hideFile(file);
                    System.out.println("############## The File Hided Successfully ###############");
                }
                case 3 -> {
                    List<Data> files = DataDAO.getAllFiles(this.email);
                    System.out.println("ID  -  File Name");
                    for (Data file : files){
                        System.out.println(file.getId()+  "-"  +file.getFileName());
                    }
                    System.out.println("               Enter the Id of file to un-hide :               ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean isValidId = false;
                    for(Data file : files){
                        if(file.getId()==id){
                            isValidId=true;
                            break;
                        }
                    }

                    if(isValidId){
                        DataDAO.unhide(id);
                        System.out.println("############## The File Un-Hided Successfully ###############");

                    }else {
                        System.out.println("Entered Wrong Id");
                    }

                }
                case 4 ->{
                    Welcome welcome= new Welcome();
                    welcome.welcomeScreen();
                }
                case 0 ->{
                    System.out.println("*************** Secure Your Data & Secure Your Life ***************");
                    System.exit(0);
                }


            }
        }while (true);

    }
}
