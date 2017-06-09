package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 05/05/2017.
 */

public class appointment {
    private int image;
    private String specialty;
    private String doctorName;

    public appointment(int img, String doctor, String specialty){
        this.setDoctorName(doctor);
        this.setImage(img);
        this.setSpecialty(specialty);
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
