package searchcontacts;

import contact.Person;
import contact.Phone;
import contact.TypePhone;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Ирина on 25.01.2016.
 */
public class SearchPersonImpl implements SearchPerson {
    public static ComporatorName comporatorName=new ComporatorName();
    public static CompLocalDate compLocalDate=new CompLocalDate();
    private Set<Person> personSet =new TreeSet<Person>(comporatorName);
    @Override
    public boolean add(Person person) {
        return personSet.add(person);
    }

    @Override
    public boolean addPhone(Person person, Phone phone) {
        for (Person persontmp : personSet){
            if(persontmp.equals(person)){
                return persontmp.addPhone(phone);
            }
        }
        return false;
    }

    @Override
    public boolean remove(Person person) {
        return personSet.remove(person);
    }

    @Override
    public Collection<Person> searchByFirstName(String name) {
        Set<Person> persons =new TreeSet<Person>(comporatorName);
             for (Person person : personSet){
           if( person.getFirstName().equals(name)){
               persons.add(person);
           }
        }
        return persons.isEmpty() ? Collections.EMPTY_SET : persons;
    }

    @Override
    public Collection<Person> searchByLastName(String name) {
        Set<Person> persons =new TreeSet<Person>(comporatorName);
         for (Person person : personSet){
            if( person.getLastName().equals(name)){
               persons.add(person);
            }
        }
        return persons.isEmpty() ? Collections.EMPTY_SET : persons;
    }

    @Override
    public Collection<Person> searchByFullName(String name) {
        Set<Person> persons =new TreeSet<Person>(comporatorName);
        String str="";
        for (Person person : personSet){
            str= person.getFirstName()+" "+ person.getLastName();
            if(str.contains(name)){
               persons.add(person);
            }
        }
        return persons.isEmpty() ? Collections.EMPTY_SET : persons;

    }

    @Override
    public Person searchByNamberPhone(String number) {
        for (Person person : personSet) {
            for (Phone phone : person.getPhones()) {
                if(phone.getPhoneNamber().equals(number)){
                    return person;
                }
            }
        }
            return null;

    }

    @Override
    public Collection<Person> searchByBirthday(LocalDate from, LocalDate to) {
        Set<Person> persons =new TreeSet<Person>(compLocalDate);
        for (Person person : personSet){
            if(( person.getBirthday().isAfter(from))&&(person.getBirthday().isBefore(to))){
                persons.add(person);
            }
        }
        return persons.isEmpty() ? Collections.EMPTY_SET : persons;
    }
    public  Collection<Person> getPersonSet(){return personSet;}



    public void fillTestData(){

        Phone phone=new Phone("09525344577", TypePhone.FAX);
        Phone phone1=new Phone("06725344577", TypePhone.HOME);
        Phone phone2=new Phone("09853445775", TypePhone.WORK);
        Phone phone3=new Phone("0658992345", TypePhone.HOME);
        Phone phone4=new Phone("0978845577", TypePhone.WORK);
        Phone phone5=new Phone("0919018822", TypePhone.WORK);
        Phone phone6=new Phone("0675545882", TypePhone.FAX);
        Phone phone7=new Phone("093871234", TypePhone.WORK);
        Person person = new Person("Geims", "Bond",new HashSet<>( Collections.singleton(phone)), "petrovv@jmail.com", "New York", LocalDate.of(1958, 5, 30));
        Person person1 = new Person("Jon", "Vong", new HashSet<>(Collections.singleton(phone1)), "ggggg11@mail.ru", "Kiev", LocalDate.of(1987, 10, 3));
        Person person2 = new Person("Jon", "Galyno",new HashSet<>( Collections.singleton(phone2)), "galyno1@jmail.com", "Paris", LocalDate.of(1960, 7, 10));
        Person person3 = new Person("Van", "Dam",new HashSet<>( Collections.singleton(phone3)), "galyno1@jmail.com", "Paris", LocalDate.of(1955, 7, 10));
        Person person4 = new Person("Shvarce", "Negger",new HashSet<>( Arrays.asList(phone5,phone4)), "galyno1@jmail.com", "Los Andgeles", LocalDate.of(1954, 8, 10));
        Person person5 = new Person("Nikol"," Kidmn",new HashSet<>( Arrays.asList(phone6,phone7)), "nikolkidman@jmail.com", "Maimy", LocalDate.of(1971, 8, 10));
        personSet.add(person);
        personSet.add(person1);
        personSet.add(person2);
        personSet.add(person3);
        personSet.add(person4);
        personSet.add(person5);

        System.out.println(personSet);
        addPhone(person4,phone3);
        System.out.println(searchByFirstName("Jon"));
        System.out.println(searchByFullName("Geims Bond"));
        System.out.println(searchByNamberPhone("09853445775"));
        System.out.println(searchByBirthday(LocalDate.of(1953,5,30),LocalDate.of(1988,10,3)));
    }
    @Override
    public String toString() {
        String str="";
        for (Person person : personSet){
            str+= person;
        }
        return str;
    }
}
