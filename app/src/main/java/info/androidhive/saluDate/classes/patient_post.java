package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 15/06/2017.
 */

public class patient_post {
    private String civil_status;
    private person person;

   public patient_post(patient p){
       civil_status=p.getCivil_status();
       person=p.getPerson();
   }

    public person getPerson() {
        return person;
    }

    public void setPerson(person person) {
        this.person = person;
    }

    public String getCivil_status() {
        return civil_status;
    }

    public void setCivil_status(String civil_status) {
        this.civil_status = civil_status;
    }
}
