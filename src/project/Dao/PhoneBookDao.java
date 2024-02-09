package project.Dao;

import project.Model.Contact;
import project.Model.PhoneBook;
import project.Util.Comparator.FirstNameAtoZComparator;
import project.Util.Comparator.FirstNameZtoAComparator;
import project.Util.Comparator.LastNameAtoZCompator;
import project.Util.Comparator.LastNameZtoAComparator;

import java.util.*;

public class PhoneBookDao {

    private final PhoneBook phoneBook;

    public PhoneBookDao() {
        phoneBook = new PhoneBook();
    }

    public boolean AddContact(Contact contact){
        int id=contact.getId();
        Map<Integer,Contact>ContactMap = phoneBook.getContactMap();
        ContactMap.put(id,contact);

        return true;
    }
    public List<Contact> GetContactList(){

        Collection<Contact> ContactCollection= phoneBook.getContactMap().values();
        return new ArrayList<>(ContactCollection);

    }
    public List<Contact> GetContactByName(String Name){
        Name= Name.toLowerCase();
        Collection<Contact> ContactCollection= phoneBook.getContactMap().values();
        List<Contact>ContactList= new ArrayList<>(ContactCollection);
        List<Contact>ContactByName= new ArrayList<>();
        for(Contact contact: ContactList){
            if(contact.getFirstName().toLowerCase().contains(Name)
                    ||contact.getMiddleName().toLowerCase().contains(Name)
                    ||contact.getLastName().toLowerCase().contains(Name)) {
                  ContactByName.add(contact);
            }
        }
        return ContactByName;
    }

    public boolean UpdateContact(int id, String UpdateField, String UpdateValue){
        Contact ExistingContact=null;
        List<Contact>ContactList= GetContactList();
        for(Contact c:ContactList){
            if(id==c.getId()){
                ExistingContact=c;
                break;
            }
        }
        if(ExistingContact!=null){
            UpdateField= UpdateField.toLowerCase();
            switch (UpdateField){
                case ("first-name"):
                    ExistingContact.setFirstName(UpdateValue);
                    break;
                case ("middle-name"):
                    ExistingContact.setMiddleName(UpdateValue);
                    break;
                case ("last-name"):
                    ExistingContact.setLastName(UpdateValue);
                    break;
                case ("contact-number"):
                    ExistingContact.setContactNumber(UpdateField);
                    break;
            }
            return true;
        }
        return false;
    }
    public boolean DeleteContact(int id){
        Contact obj= phoneBook.getContactMap().remove(id);
        return obj != null;
    }
    public boolean DeleteAllContact(){
        Map<Integer,Contact>mp= phoneBook.getContactMap();
        int size = mp.size();
        if(size!=0){
            mp.clear();
            return true;
        }
        return false;
    }
    public List<Contact> SortContactList(String SortField, String Order){
        List<Contact>ContactList= GetContactList();
        if(ContactList.isEmpty())return ContactList;
        if(Objects.equals(SortField, "first-name")){

            if(Objects.equals(Order,"a-z")){
                ContactList.sort(new FirstNameAtoZComparator());
            }
            else{
                ContactList.sort(new FirstNameZtoAComparator());
            }
        }
        else{
            if(Objects.equals(Order,"a-z")){
                ContactList.sort(new LastNameAtoZCompator());
            }
            else{
                ContactList.sort(new LastNameZtoAComparator());
            }
        }
        return ContactList;
    }
}
