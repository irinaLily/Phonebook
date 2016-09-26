package searchcontacts;

import contact.Person;

import java.util.Comparator;

/**
 * Created by Ирина on 07.02.2016.
 */
public class ComporatorName implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        int returnComporator=o1.getFirstName().compareTo(o2.getFirstName());
        if((returnComporator==0)){
            return o1.getLastName().compareTo(o2.getLastName());
        }

        return returnComporator;
    }
}
