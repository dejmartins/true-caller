package africa.semicolon.trueCaller.data.repositories;

import africa.semicolon.trueCaller.data.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryImplTest {
    private ContactRepository contactRepository;

    @BeforeEach
    public void startWithThis(){
        contactRepository = new ContactRepositoryImpl();
    }

    @Test
    public void saveContact_countIsOneTest(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");

        Contact savedContact = contactRepository.save(contact);
        assertEquals(1, savedContact.getId());
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void saveContact_countIsTwoTest(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");

        Contact contact2 = new Contact();
        contact2.setFirstName("Ladi");

        Contact savedContact = contactRepository.save(contact);
        Contact savedContact2 = contactRepository.save(contact2);
        assertEquals(1, savedContact.getId());
        assertEquals(2, savedContact2.getId());
        assertEquals(2, contactRepository.count());
    }

    @Test
    public void savedContact_findByIdReturnsContact(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");
        Contact savedContact = contactRepository.save(contact);

        Contact contact2 = new Contact();
        contact2.setFirstName("Abiola");
        Contact savedContact2 = contactRepository.save(contact2);

        Contact foundContact = contactRepository.findById(1);
        Contact foundContact2 = contactRepository.findById(2);

        assertEquals(1, foundContact.getId());
        assertEquals("Samson", foundContact.getFirstName());
    }

    @Test
    public void savedContact_findByFirstNameReturnsContact(){
        Contact contact = new Contact();
        contact.setFirstName("Kene");

        Contact savedContact = contactRepository.save(contact);
        Contact foundContact = contactRepository.findByFirstName("Kene");

        assertEquals("Kene", foundContact.getFirstName());
    }

    @Test
    public void savedContact_deleteContactAfterGotten(){
        Contact contact = new Contact();
        contact.setFirstName("Kene");
        Contact savedContact = contactRepository.save(contact);

        Contact contact2 = new Contact();
        contact2.setFirstName("Abiola");
        Contact savedContact2 = contactRepository.save(contact2);

        Contact foundContact = contactRepository.findByFirstName("Kene");
        Contact deleted = contactRepository.deleteContact(foundContact);

        assertEquals(1, contactRepository.count());
        assertNull(contactRepository.findByFirstName("Kene"));
    }

    @Test
    public void savedContact_updateNameSavedWithContact(){
        Contact contact = new Contact();
        contact.setFirstName("Kene");
        contact.setLastName("Okonkwo");
        Contact savedContact = contactRepository.save(contact);

        Contact foundContact = contactRepository.findById(1);
        contactRepository.updateContactName(foundContact, "Lucia", null);

        assertEquals("Lucia", foundContact.getFirstName());
        assertEquals("Okonkwo", foundContact.getLastName());
    }

    @Test
    public void savedContact_addAnotherPhoneNumberToContact(){
        Contact contact = new Contact();
        contact.setPhoneNumber("123456");
        Contact savedContact = contactRepository.save(contact);

        Contact foundContact = contactRepository.findById(1);
        contactRepository.addAnotherPhoneNumber(foundContact, "234567");
        contactRepository.addAnotherPhoneNumber(foundContact, "345678");
        String number = contactRepository.findPhoneNumber(foundContact, 2);

        assertEquals("234567", number);
        assertEquals(3, contactRepository.numberOfPhoneNumbers(foundContact));
    }

    @Test
    public void savedContact_addingMoreThanThreeNumbers_ThrowsException(){
        Contact contact = new Contact();
        contact.setPhoneNumber("123456");
        Contact savedContact = contactRepository.save(contact);

        Contact foundContact = contactRepository.findById(1);
        contactRepository.addAnotherPhoneNumber(foundContact, "234567");
        contactRepository.addAnotherPhoneNumber(foundContact, "345678");

        assertThrows(ArrayIndexOutOfBoundsException.class, () ->  contactRepository.addAnotherPhoneNumber(foundContact, "345694"));

        String number = contactRepository.findPhoneNumber(foundContact, 2);
        assertEquals("234567", number);
        assertEquals(3, contactRepository.numberOfPhoneNumbers(foundContact));
    }

    @Test
    public void savedContact_updatePhoneNumberInContact(){
        Contact contact = new Contact();
        contact.setPhoneNumber("123456");
        Contact savedContact = contactRepository.save(contact);

        Contact foundContact = contactRepository.findById(1);
        contactRepository.addAnotherPhoneNumber(foundContact, "234567");
        contactRepository.updatePhoneNumber(foundContact,1, "3334567");

        String number = contactRepository.findPhoneNumber(foundContact, 1);
        assertEquals("3334567", number);
    }
}