package sample;

import contact.Person;
import contact.Phone;
import searchcontacts.SearchPersonImpl;
import contact.TypePhone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;

public class Controller  {

    private SearchPersonImpl searchPerson=new SearchPersonImpl();

    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private DatePicker birthday;
    @FXML
    private DatePicker birthday2;
    @FXML
    private TextField email;
    @FXML
    private TextField adress;

    @FXML
    private TextField number;
    @FXML
    private ComboBox<TypePhone> type;
    @FXML
    private ListView<Phone> phoneNumbers;
    @FXML
    private ListView<Person> contacts;
    private ObservableList <Person> list;
    private ObservableList<Phone> list2;

    @FXML
    public void initialize() {
        searchPerson.fillTestData();
        list = FXCollections.observableArrayList(searchPerson.getPersonSet());
        contacts.setItems(list);
        list2 = FXCollections.observableArrayList();
        phoneNumbers.setItems(list2);
        type.setItems(FXCollections.observableArrayList(TypePhone.values()));
    }
    @FXML
    private void selectContact() {
        Person person = contacts.getSelectionModel().getSelectedItem();
        list2.clear();
        list2.addAll(person .getPhones());
        firstName.setText(person .getFirstName());
        lastName.setText(person .getLastName());
        birthday.setValue(person .getBirthday());
        email.setText(person .getEmail());
        adress.setText(person.getAdress());
    }

    @FXML
    private void selectNumber() {
        Phone p = phoneNumbers.getSelectionModel().getSelectedItem();
        number.setText(p.getPhoneNamber());
        type.getSelectionModel().select(p.getTypePhone());
    }

    @FXML
    private void addContact (){
        Phone phone = new Phone(number.getText(), type.getValue());
        if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||number.getText().isEmpty())
        {alertWarning("Contact is Empti");}
        else {
            Person contactTmp = new Person(firstName.getText(), lastName.getText(), new HashSet<>(Arrays.asList(phone)), email.getText(), adress.getText(),
                    birthday.getValue());

        if (searchPerson.add(contactTmp)){
            list.add(contactTmp);
        }else {alertWarning("CONTACT is already exist");

        }}

    }

    @FXML
    private void addNumber (){
        Phone phone = new Phone(number.getText(), type.getValue());
      if(phone.equals(null)||number.getText().isEmpty()) {alertWarning("phone is Empti  null");}else{
        Person person = contacts.getSelectionModel().getSelectedItem();
          System.out.println("person "+person+"phone  "+phone);
        if((!contacts.getSelectionModel().isEmpty())&&(!phone.equals(null))&&(!person.equals(null)))
        { searchPerson.addPhone(person,phone);
            list2.add(phone);
            list.clear();
            list.addAll((searchPerson.getPersonSet()));
        }else {
            alertWarning("not the selected contact");

        }}
    }

    @FXML
    private void editContact (){
        Phone editedNumber = new Phone(number.getText(), type.getValue());
        Person person = contacts.getSelectionModel().getSelectedItem();
        if(contacts.getSelectionModel().isEmpty()|| person.getPhones().equals(null)||firstName.getText().isEmpty()||lastName.getText().isEmpty()||number.getText().isEmpty()){alertWarning("CONTACT is already exist");}
        else {
            Set<Phone> listTmp = new HashSet<>(person.getPhones());
            Phone phone = phoneNumbers.getSelectionModel().getSelectedItem();
            listTmp.remove(phone);
            listTmp.add(editedNumber);
            Person contactTmp = new Person(firstName.getText(), lastName.getText(), listTmp, email.getText(),
                    adress.getText(), birthday.getValue());

            if (searchPerson.remove(person) && searchPerson.add(contactTmp)) {
                int i = list.indexOf(person);
                list.remove(person);
                list.add(i, contactTmp);
                contacts.getSelectionModel().select(i);
                list2.clear();
                list2.addAll(listTmp);

            } else {
                alertWarning("CONTACT is already exist");
            }
        }
    }
    private void alertWarning(String str){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ATTENTION");
        alert.setContentText(str);
        alert.show();
    }

    @FXML
    private void removeContact (){
        Person person = contacts.getSelectionModel().getSelectedItem();
        if(searchPerson.remove(person)){
            list.remove(person);
        }
    }

    @FXML
    private void removeNumber (){
        if((!phoneNumbers.getSelectionModel().isEmpty())&&(!contacts.getSelectionModel().isEmpty())) {
            Person person = contacts.getSelectionModel().getSelectedItem();
            Phone phone = phoneNumbers.getSelectionModel().getSelectedItem();
            int i=0;
            for (Phone p:person.getPhones()){i++;}
            if((i>1)&&(person.removePhone(phone))) {
                list2.remove(phone);
                list.clear();
                list.addAll((searchPerson.getPersonSet()));
            }else {alertWarning("it is impossible to remove the phone");}
        }else {alertWarning("not the selected phone number or not the selected person");}
    }

    @FXML
    private void searchLastName (){
        if(lastName.getText()!=null) {
            Collection<Person> searchContact = searchPerson.searchByLastName(lastName.getText());
            list.clear();
            clean();
            list.addAll(searchContact);

        }
        else{
            alertWarning("Last Name is already exist");
        }
    }

    @FXML
    private void searchFirstName (){
        if(firstName.getText()!=null) {
        Collection<Person> searchContact = searchPerson.searchByFirstName(firstName.getText());
        list.clear();
        clean();
        list.addAll(searchContact);
        }else alertWarning("First Name is already exist");
    }

    @FXML
    private void searchNamber (){
        if(number.getText()!=null) {
            Person contact = searchPerson.searchByNamberPhone(number.getText());
            list.clear();
            clean();
            list.addAll(Collections.singleton(contact));


        }else alertWarning("First Name is already exist");

    }

    @FXML
    private void searchByAge (){
        if((birthday.getValue()!=null)&&(birthday2.getValue()!=null)) {
            Collection<Person> searchContact = searchPerson.searchByBirthday(birthday.getValue(),birthday2.getValue());
            list.clear();
            clean();
            list.addAll(searchContact);

        }
        else{
            alertWarning("birthday is already exist");
        }

    }

    @FXML
    private void clean (){
        list2.clear();
        firstName.clear();
        lastName.clear();
        birthday.setValue(null);
        birthday2.setValue(null);
        email.clear();
        number.clear();
        adress.clear();
        type.getSelectionModel().clearSelection();
        contacts.getSelectionModel().clearSelection();
    }

    @FXML
    private void cancel (){
        list2.clear();
        list.clear();
        list.addAll((searchPerson.getPersonSet()));
    }
}
