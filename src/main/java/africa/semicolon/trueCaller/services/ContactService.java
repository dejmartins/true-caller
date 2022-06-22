package africa.semicolon.trueCaller.services;

import africa.semicolon.trueCaller.data.models.Contact;

public interface ContactService {
    public void addContact(String firstName, String lastName, String[] phoneNumber);

    Contact findById(int id);

    Contact deleteContact(Contact contact);

    Contact findByFirstName(String firstName);
}
