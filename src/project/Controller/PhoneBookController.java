package project.Controller;

import project.Service.PhoneBookService;
import project.View.PhoneBookView;
import project.View.Response;

public class PhoneBookController {
    private final PhoneBookView phoneBookView;
   private final PhoneBookService phoneBookService;
    public PhoneBookController() {
        phoneBookView= new PhoneBookView(this);
        phoneBookService= new PhoneBookService();
    }

    public void start(){
        phoneBookView.DisplayMenu();
    }
    public Response AddContact(String AddCommand){
        return phoneBookService.AddContact(AddCommand);

    }
    public Response GetAllContact(){
      return phoneBookService.GetAllContact();
    }
    public Response GetContactByName(String command){
        return phoneBookService.GetContactByName(command);
    }
    public Response UpdateContact(String Command){
        return phoneBookService.UpdateContact(Command);
    }
    public Response DeleteContact(String Command){
        return phoneBookService.DeleteContact(Command);
    }
    public Response DeleteAllContact(){
        return phoneBookService.DeleteAllContact();
    }
    public Response SortContactList(String Command){
        return phoneBookService.SortContactList(Command);
    }

}
