package searchcontacts;

import contact.Person;
import contact.Phone;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Ирина on 25.01.2016.
 */
public interface SearchPerson {
    boolean add(Person person);
    boolean addPhone(Person person, Phone phone);
    boolean remove(Person person);
    Collection<Person> searchByFirstName(String name);
    Collection<Person> searchByLastName(String name);
    Collection<Person> searchByFullName(String name);
    Person searchByNamberPhone(String name);
    Collection<Person> searchByBirthday(LocalDate from, LocalDate to);


    Collection<Person> getPersonSet();
    void fillTestData();
}
