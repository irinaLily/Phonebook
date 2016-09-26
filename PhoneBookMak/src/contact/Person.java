package contact;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ирина on 25.01.2016.
 */
public class Person implements Serializable{
    private  String firstName;
    private String lastName;
    private  Set<Phone> phones;
    private   String email;
    private  String adress;
    private  LocalDate  birthday;

    public Person(String firstName, String lastName, Set<Phone> phones, String email, String adress, LocalDate birthday) {
        this.phones=new HashSet<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
        this.email = email;
        this.adress = adress;
        searhBirthday(birthday);
    }
    private void searhBirthday( LocalDate birthday){
        LocalDate now=LocalDate.now();
        LocalDate past=now.minusYears(110);
        if(birthday.isAfter(now)||birthday.isBefore(past)){
            throw new IllegalArgumentException("incorrect date");
        }
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
   public boolean addPhone(Phone phone){
      return this.phones.add(phone);
   }
    public boolean removePhone(Phone phone){
        return this.phones.remove(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (adress != null ? !adress.equals(person.adress) : person.adress != null) return false;
        return birthday != null ? birthday.equals(person.birthday) : person.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String str="";
          str=firstName + "  " + lastName+" ";
        for (Phone phone:phones){
            str+=phone;
        }
        str+="   " + email+"  " +"adress=" + adress +" " + birthday+ "\n";
        return str;
    }
}
