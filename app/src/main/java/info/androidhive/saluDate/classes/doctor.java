package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 19/05/2017.
 */

public class doctor {
    private Integer id;
    private String school_medicine;
    private person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool_medicine() {
        return school_medicine;
    }

    public void setSchool_medicine(String school_medicine) {
        this.school_medicine = school_medicine;
    }

    public info.androidhive.saluDate.classes.person getPerson() {
        return person;
    }

    public void setPerson(info.androidhive.saluDate.classes.person person) {
        this.person = person;
    }
}
