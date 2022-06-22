package africa.semicolon.trueCaller.services;

import africa.semicolon.trueCaller.data.models.Contact;
import africa.semicolon.trueCaller.data.repositories.ContactRepository;
import africa.semicolon.trueCaller.data.repositories.ContactRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceImplTest {
    private ContactService contactService;
    private ContactRepository contactRepository;

    @BeforeEach
    public void startsWith(){
        contactRepository = new ContactRepositoryImpl();
        contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    public void saveContact_findContactReturnsContact(){
        String[] phoneNumber = {"7367829374"};
        contactService.addContact("Kene", "Chukwu", phoneNumber);

        Contact contact = contactService.findById(1);
        assertEquals("Kene", contact.getFirstName());
        assertEquals("Chukwu", contact.getLastName());
        assertEquals("7367829374", contact.getPhoneNumber(1));
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void saveContact_deleteContact(){
        String[] phoneNumber = {"7367829374"};
        contactService.addContact("Kene", "Chukwu", phoneNumber);

        Contact contact = contactService.findById(1);
        Contact deletedContact = contactService.deleteContact(contact);
        assertEquals(0, contactRepository.count());
        assertNull(contactService.findByFirstName("Kene"));
    }
}