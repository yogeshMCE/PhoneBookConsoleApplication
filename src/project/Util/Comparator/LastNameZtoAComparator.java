package project.Util.Comparator;

import project.Model.Contact;

import java.util.Comparator;

public class LastNameZtoAComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact c1, Contact c2){
        return (c2.getLastName().compareTo(c1.getLastName()));
    }
}
