package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 19/05/2017.
 */

public class doctor {
    private info.androidhive.saluDate.classes.specialty specialty;
    private String doctorName;

    public doctor(info.androidhive.saluDate.classes.specialty specialty, String doctorName){
        this.setSpecialty(specialty);
        this.doctorName=doctorName;
    }
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public info.androidhive.saluDate.classes.specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(info.androidhive.saluDate.classes.specialty specialty) {
        this.specialty = specialty;
    }
}
