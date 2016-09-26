package searchcontacts;

import contact.Person;

import java.util.Comparator;

/**
 * Created by Ирина on 28.01.2016.
 */
public class CompLocalDate implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
      return o1.getBirthday().compareTo(o2.getBirthday());
    }
}
