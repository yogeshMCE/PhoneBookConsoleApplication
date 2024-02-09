package project.Util.Comparator;

import project.Model.Contact;

import java.util.Comparator;

public class LastNameAtoZCompator implements Comparator<Contact> {
    @Override
    public int compare(Contact c1, Contact c2){
        return (c1.getLastName().compareTo(c2.getLastName()));
    }
}
