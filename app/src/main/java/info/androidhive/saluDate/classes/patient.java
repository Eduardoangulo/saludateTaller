package info.androidhive.saluDate.classes;

public class patient {

    private Integer id;
    private String civil_status;
    private person person;

    public patient(Integer id, String civil_status, person person){
        this.setId(id);
        this.setCivil_status(civil_status);
        this.setPerson(person);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCivil_status() {
        return civil_status;
    }

    public void setCivil_status(String civil_status) {
        this.civil_status = civil_status;
    }

    public person getPerson() {
        return person;
    }

    public void setPerson(person person) {
        this.person = person;
    }
}

