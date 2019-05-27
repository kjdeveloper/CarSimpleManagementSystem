package service;

import exceptions.MyExceptions;

import java.util.Scanner;

public class UserDataService {

    private Scanner sc = new Scanner(System.in);

    public int getInt(String message){
        System.out.println(message);

        String text = sc.nextLine();
        if (!text.matches("\\d+")){
            throw new MyExceptions("Invalid number");
        }
        return Integer.parseInt(text);
    }

    public void close(){
        if (sc != null){
            sc.close();
            sc = null;
        }
    }

}
