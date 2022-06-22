package africa.semicolon.trueCaller.data.repositories;

import africa.semicolon.trueCaller.data.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepository{
    private final List<Contact> contacts = new ArrayList<>();
    @Override
    public Contact save(Contact contact) {
        contact.setId(contacts.size() + 1);
        contacts.add(contact);
        return contact;
    }

    @Override
    public Contact findByFirstName(String firstName) {
        for (Contact contact : contacts){
            if (contact.getFirstName().equals(firstName)) return contact;
        }
        return null;
    }

    @Override
    public Contact findById(int id) {
        return contacts.get(id - 1);
    }

    @Override
    public Contact deleteContact(Contact contact) {
        for (Contact details : contacts){
            if (contact.equals(details)) contacts.remove(details);
            return details;
        }
        return null;
    }

    @Override
    public void updateContactName(Contact foundContact, String firstName, String lastName) {
        for (Contact contact : contacts){
            if (contact == foundContact){
                if (firstName != null){
                    foundContact.setFirstName(firstName);
                }
                if (lastName != null){
                    foundContact.setLastName(lastName);
                }
            }
        }
    }

    @Override
    public int count() {
        return contacts.size();
    }

    @Override
    public void addAnotherPhoneNumber(Contact contact, String phoneNumber) {
        for (Contact c : contacts){
            if (c == contact){
                c.setPhoneNumber(phoneNumber);
            }
        }
    }

    @Override
    public String findPhoneNumber(Contact foundContact, int index) {
        for (Contact c : contacts){
            if (c == foundContact){
                return c.getPhoneNumber(index);
            }
        }
        return null;
    }

    @Override
    public int numberOfPhoneNumbers(Contact foundContact) {
        for (Contact c : contacts){
            if (c == foundContact){
                return c.getNumberOfPhoneNumbers();
            }
        }
        return 0;
    }

    @Override
    public void updatePhoneNumber(Contact foundContact, int phoneNumberIndex, String newPhoneNumber) {
        for (Contact c : contacts){
            if (c == foundContact){
                c.replaceNumber(phoneNumberIndex, newPhoneNumber);
            }
        }
    }
}
