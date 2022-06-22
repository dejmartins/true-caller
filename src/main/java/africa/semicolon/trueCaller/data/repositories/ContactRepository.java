package africa.semicolon.trueCaller.data.repositories;

import africa.semicolon.trueCaller.data.models.Contact;

public interface ContactRepository {
    Contact save(Contact contact);
    Contact findByFirstName(String firstName);
    Contact findById(int id);
    Contact deleteContact(Contact contact);
    void updateContactName(Contact foundContact, String firstName, String lastName);
    int count();
    void addAnotherPhoneNumber(Contact contact, String phoneNumber);
    String findPhoneNumber(Contact contact, int index);
    int numberOfPhoneNumbers(Contact foundContact);
    void updatePhoneNumber(Contact foundContact, int phoneNumberIndex, String newPhoneNumber);
}
