package project.Util.Comparator;

import project.Model.Contact;

import java.util.Comparator;

public class FirstNameZtoAComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact c1, Contact c2){
        return (c2.getFirstName().compareTo(c1.getFirstName()));
    }
}

