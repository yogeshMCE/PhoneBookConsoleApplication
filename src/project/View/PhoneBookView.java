package project.View;

import project.Constsnt.Colour;
import project.Controller.PhoneBookController;
import project.Model.Contact;

import java.util.*;

public class PhoneBookView implements Colour {
   private final Scanner scanner;
    private final PhoneBookController phoneBookController;
    public PhoneBookView(PhoneBookController phoneBookController) {
        scanner= new Scanner(System.in);
        this.phoneBookController= phoneBookController;
    }

    public void DisplayMenu(){

         String command="continue";

        while(!command.equalsIgnoreCase("exit")){
            //display menu
            System.out.println(MENU_TEXT+"Add Contact: "+RESET_TEXT+ UNDERLINE_TEXT + ITALIC_TEXT + "add"+RESET_TEXT+ OPTION_TEXT+" <first-name> <middle-name> <last-name> <contact-number>"+RESET_TEXT);
            System.out.println(INFORMATION_TEXT+"<first-name> and <contact-name> are mandatory"+RESET_TEXT);
            System.out.println(INFORMATION_TEXT+"<middle-name> and <last-name> are optional"+RESET_TEXT);
            System.out.println(INFORMATION_TEXT+"If Contact Number Contains Country-code please Add hyphen example: +91-9898989898 or 91-1111111111"+RESET_TEXT);
            System.out.println(MENU_TEXT+"View All Contacts: "+RESET_TEXT+ UNDERLINE_TEXT + ITALIC_TEXT + "ViewAll"+RESET_TEXT);
            System.out.println(MENU_TEXT+"View Contacts by Name: "+RESET_TEXT+ UNDERLINE_TEXT + ITALIC_TEXT + "View"+RESET_TEXT+OPTION_TEXT+" <Name>");
            System.out.println(MENU_TEXT+"Update Contact: "+RESET_TEXT+ UNDERLINE_TEXT + ITALIC_TEXT + "Update"+RESET_TEXT+ OPTION_TEXT+" <Contact-id> <Update-Field> <Update-Value>"+RESET_TEXT);
            System.out.println(INFORMATION_TEXT+"Value of Update Field can be first-name or middle-name oe last-name or contact-number"+RESET_TEXT);
            System.out.println(MENU_TEXT+"Delete Contact: "+RESET_TEXT+ UNDERLINE_TEXT + ITALIC_TEXT + "delete"+RESET_TEXT+ OPTION_TEXT+" <Contact-id>");
            System.out.println(MENU_TEXT+"Delete All Contacts: "+RESET_TEXT+ UNDERLINE_TEXT + ITALIC_TEXT + "DeleteAll"+RESET_TEXT);
            System.out.println(MENU_TEXT+"Sort Contact List: "+RESET_TEXT+UNDERLINE_TEXT+ITALIC_TEXT+"sort"+RESET_TEXT+OPTION_TEXT+"<Field-Name> <Order>");
            // take user input
             command= scanner.nextLine();
             processUserChoice(command);

        }

    }

    private void processUserChoice(String command){
        String [] commandArr= command.split(" ");
        String choice= commandArr[0];
        boolean UserChoice=true;
        switch(choice){
            case "add":
                AddContact(command);

              break;
            case "viewAll":
               ViewAllContact();
               break;
            case "view":
                ViewContactByName(command);
                break;
            case "update":
                 UserChoice= UserChoice();
                if(UserChoice) {
                    UpdateContact(command);
                    }
                break;
            case "delete":
                 UserChoice= UserChoice();
                if(UserChoice) {
                    DeleteContact(command);
                }
                break;
            case "deleteAll":
                UserChoice= UserChoice();
                if(UserChoice) {
                    DeleteAllContact();
                }
                break;
            case "sort":
                SortContactList(command);
        }
    }


    private void SortContactList(String Command){
        Response response=phoneBookController.SortContactList(Command);
        List<Object>ContactList= Collections.singletonList(response.data);
        for(Object contact:ContactList){
            System.out.println(RESULT_TEXT+contact+RESET_TEXT);
        }
    }
    private void DeleteAllContact(){
        Response response= phoneBookController.DeleteAllContact();
        if(response.status){
            System.out.println(RESULT_TEXT+response.data+RESET_TEXT);
        }
        else{
            System.out.println(ERROR_TEXT+response.data+RESET_TEXT);
        }
    }
    private void DeleteContact(String Command){
        Response response= phoneBookController.DeleteContact(Command);
        if(response.status){
            System.out.println(RESULT_TEXT+response.data+RESET_TEXT);
        }
        else{
            System.out.println(ERROR_TEXT+response.data+RESET_TEXT);
        }
    }
    private void UpdateContact(String Command){
        Response response=phoneBookController.UpdateContact(Command);
        if(response.status){
            System.out.println(RESULT_TEXT+response.data+RESET_TEXT);
        }
        else{
            System.out.println(ERROR_TEXT+response.data+RESET_TEXT);
        }
    }
    private void AddContact(String command){
       Response response= phoneBookController.AddContact(command);
       if(!response.status){
           System.out.println(ERROR_TEXT+response.data+RESET_TEXT);
       }
       else{
           System.out.println(INFORMATION_TEXT+response.data+RESET_TEXT);
       }
    }
    private void ViewAllContact(){
         Response response=phoneBookController.GetAllContact();
        List<Object>ContactList= Collections.singletonList(response.data);
        for(Object contact:ContactList){
            System.out.println(RESULT_TEXT+contact+RESET_TEXT);
        }
    }
    private void ViewContactByName(String command){
        Response response=phoneBookController.GetContactByName(command);
        if(response.status){
            System.out.println(INFORMATION_TEXT+" Following Contacts Found: ");
            List<Object>ContactList= Collections.singletonList(response.data);
            for(Object contact:ContactList){
                System.out.println(RESULT_TEXT+contact+RESET_TEXT);
            }
        }
        else{
            System.out.println(INFORMATION_TEXT+response.data+RESET_TEXT);
        }
    }
    private boolean UserChoice(){
        System.out.println(CONFIRM_TEXT+"Are You Sure (yes/no): "+RESET_TEXT);
        String userchoice= scanner.nextLine();
        userchoice= userchoice.toLowerCase();
        while(true){
            if(Objects.equals(userchoice, "yes") || Objects.equals(userchoice, "no"))break;
            else{
                System.out.println(ERROR_TEXT+"Invalid information please ReEnter Your Choice: "+RESET_TEXT);
                userchoice=scanner.nextLine();
            }
        }
        if(Objects.equals(userchoice, "yes"))return true;
        else return false;
    }
}
