package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 19/05/2017.
 */

public class Doctor {
    private Specialty specialty;
    private String doctorName;

    public Doctor(Specialty specialty, String doctorName){
        this.setSpecialty(specialty);
        this.doctorName=doctorName;
    }
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
