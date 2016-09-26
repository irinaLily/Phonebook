package contact;

/**
 * Created by Ирина on 25.01.2016.
 */
public class Phone {
    private  String phoneNamber;
    private  TypePhone typePhone;

    public Phone(String phoneNamber, TypePhone typePhone) {
        this.phoneNamber = phoneNamber;
        this.typePhone = typePhone;
    }

    public void setPhoneNamber(String phoneNamber) {
        this.phoneNamber = phoneNamber;
    }

    public void setTypePhone(TypePhone typePhone) {
        this.typePhone = typePhone;
    }

    public String getPhoneNamber() {
        return phoneNamber;
    }

    public TypePhone getTypePhone() {
        return typePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        return phoneNamber != null ? phoneNamber.equals(phone.phoneNamber) : phone.phoneNamber == null;

    }

    @Override
    public int hashCode() {
        return phoneNamber != null ? phoneNamber.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "  tel=" + phoneNamber+ "   "+ typePhone;
    }
}
