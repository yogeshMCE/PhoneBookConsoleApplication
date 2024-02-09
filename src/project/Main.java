package project;

import project.Controller.PhoneBookController;


public class Main {
    public static void main(String[] args) {
        PhoneBookController phoneBookController= new PhoneBookController();
        phoneBookController.start();
        int i=2;
        while(i<=10){
            System.out.println("hello");
            i++;
        }
    }

}
