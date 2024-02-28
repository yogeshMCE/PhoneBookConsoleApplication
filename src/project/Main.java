package project;

import project.Controller.PhoneBookController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args)  throws IOException {
        PhoneBookController phoneBookController = new PhoneBookController();
        phoneBookController.start();

    }
}
