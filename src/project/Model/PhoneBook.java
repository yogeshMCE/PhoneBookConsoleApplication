package project.Model;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    Map<Integer,Contact>ContactMap;

    public PhoneBook() {
        ContactMap = new HashMap<>();
    }

    public Map<Integer, Contact> getContactMap() {
        return ContactMap;
    }

    public void setContactMap(Map<Integer, Contact> contactMap) {
        ContactMap = contactMap;
    }
}
