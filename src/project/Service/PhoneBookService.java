package project.Service;

import project.Dao.PhoneBookDao;
import project.Exception.InvalidContactNumException;
import project.Exception.InvalidFormatException;
import project.Model.Contact;
import project.Util.PhoneBookExceptionUtil;
import project.View.Response;

import java.lang.reflect.Member;
import java.util.List;

public class PhoneBookService {

    private final PhoneBookExceptionUtil phoneBookExceptionUtil;
    private final PhoneBookDao phoneBookDao;

    public PhoneBookService() {
        phoneBookExceptionUtil = new PhoneBookExceptionUtil();
        phoneBookDao= new PhoneBookDao();
    }

    public Response AddContact(String AddCommand){
        boolean flag= false;
        String Message="";
        try {

            String[] Command = AddCommand.split(" ");
            int CommandLength = Command.length;
            boolean IsCommandLengthValid = phoneBookExceptionUtil.ValidateAddCommandLength(CommandLength);

            if(!IsCommandLengthValid){
                throw new InvalidFormatException("Invalid Format of provided data for adding contact");
            }

            String ContactNumber= Command[CommandLength-1];
            boolean IsContactNumberValid = phoneBookExceptionUtil.ValidateContactNUmber(ContactNumber);

            if(!IsContactNumberValid){
                throw new InvalidContactNumException("Invalid Contact Number");
            }

             String FirstName= Command[1];
             String MiddleName= "";
             String LastName="";
             if(CommandLength==4){
                 LastName=Command[2];
             }
             if(CommandLength==5){
                 MiddleName= Command[2];
                 LastName=Command[3];
             }
             Contact contact= new Contact(FirstName,MiddleName,LastName,ContactNumber);
             flag=phoneBookDao.AddContact(contact);
             Message="Contact Added Succcesfully...";

        }catch(InvalidFormatException | InvalidContactNumException e){
            Message=e.getMessage();
        }
        return new Response(Message,flag);

    }
    public Response GetAllContact(){
        Object data= phoneBookDao.GetContactList();
        return new Response(data, true);
    }
    public Response GetContactByName(String GetCommand){
        Object data="";
        boolean flag=false;
        try{
            String [] GetCommandArr= GetCommand.split(" ");
            int CommandLength= GetCommandArr.length;
            boolean IsCommandLengthValid = phoneBookExceptionUtil.ValidateGetCommandLength(CommandLength);
            if(!IsCommandLengthValid){
                throw new InvalidFormatException("Invalid Format Specify Name..");
            }
            String name= GetCommandArr[1];
            List<Contact> ContactList=phoneBookDao.GetContactByName(name);
            if(!ContactList.isEmpty()){
                data=ContactList;
                flag=true;
            }
            else{
                data="No Contact With "+name+" Found";
            }

        }catch(InvalidFormatException e){

            data= e.getMessage();
        }
        return new Response(data,flag);
    }
    public Response UpdateContact(String Command){
        Object data="";
        boolean flag= false;
        // update id update-Field Update-value
        try{
            String [] CommandArr = Command.split(" ");
            int CommandLength = CommandArr.length;
            boolean IsCommandLengthValid = phoneBookExceptionUtil.ValidateUpdateCommandLength(CommandLength);
            if(!IsCommandLengthValid){
                throw  new InvalidFormatException("Invalid Update Command Specify id , update field ,Update-Value ");
            }
            String UpdateField= CommandArr[2];
            int id = Integer.parseInt(CommandArr[1]);
            String UpdateValue= CommandArr[3];
            boolean IsUpdateFieldValid = phoneBookExceptionUtil.ValidateUpdateField(UpdateField);
            if(!IsUpdateFieldValid){
                throw new InvalidFormatException("Invalid Update Field");
            }
            if(UpdateField.equalsIgnoreCase("contact-number")){
                boolean IsContactNumberValid = phoneBookExceptionUtil.ValidateContactNUmber(UpdateValue);

                if(!IsContactNumberValid){
                    throw new InvalidContactNumException("Invalid Contact Number");
                }
            }
            flag= phoneBookDao.UpdateContact(id,UpdateField,UpdateValue);
            if(flag){
                data="Contact of id :"+id+" Updated Successfully";

            }
            else{
                data="No Contact Exits With id: "+id;
            }

        }
        catch(InvalidFormatException |InvalidContactNumException e){
            data = e.getMessage();
        }
        return new Response(data,flag);
    }
    public Response DeleteContact(String Command){
        Object data="";
        boolean status= false;
       try {
           String[] CommandArr = Command.split(" ");
           int CommandLength = CommandArr.length;
           boolean IsCommandLengthValid = phoneBookExceptionUtil.DeleteCommandLength(CommandLength);
           if(!IsCommandLengthValid){
               throw new InvalidFormatException("Invalid Delete Command...");
           }
           int id = Integer.parseInt(CommandArr[1]);
           status= phoneBookDao.DeleteContact(id);
           if(status){
               data="Contact Deleted Succsesfully...";
           }
           else{
               data="No Contact Found with id: "+id;
           }

       }
       catch(InvalidFormatException e){
           data= e.getMessage();
       }
      return new Response(data,status);
    }
    public Response DeleteAllContact(){
        boolean status=false;
        Object data="";
        status= phoneBookDao.DeleteAllContact();
        if(status){
            data="All Contacts deleted successfully...";
        }
        else{
            data="No Contacts Are Available..";
        }
        return  new Response(data,status);
    }
    public Response SortContactList(String Command){
        boolean status=false;
        Object data="";
        try{
            String []CommandArr= Command.split(" ");
            int CommandLength= CommandArr.length;
            boolean IsCommandLengthValid = phoneBookExceptionUtil.SortCommandLength(CommandLength);
            if(!IsCommandLengthValid){
                throw new InvalidFormatException("Invalid Sort Command...");
            }
            String SortField= CommandArr[1];
            String Order= CommandArr[2];
            Order= Order.toLowerCase();
            boolean IsSortFieldValid = phoneBookExceptionUtil.ValidateSortField(SortField);
            if(!IsSortFieldValid){
                throw new InvalidFormatException("Invalid Sort Field");
            }
            boolean IsOrderValid= phoneBookExceptionUtil.ValidateOrderForSort(Order);
            if(!IsOrderValid){
                throw  new InvalidFormatException("Invalid Sort Order Is Can be a-z or z-a only");
            }
            SortField=SortField.toLowerCase();
            List<Contact>ContactList=phoneBookDao.SortContactList(SortField,Order);
            if(ContactList!=null){
                data=ContactList;
                status=true;
            }
            else{
                data="There is no contact list Available..";
            }

        }
        catch(InvalidFormatException e){
            data= e.getMessage();
        }
        return new Response(data,status);
    }
}
