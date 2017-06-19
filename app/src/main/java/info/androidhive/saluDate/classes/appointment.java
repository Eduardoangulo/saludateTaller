package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 05/05/2017.
 */

public class appointment {
<<<<<<< HEAD
    private Integer id;
    private Integer schedule_doctor;
    private Integer speciality_doctor;
    private Integer patient;
    private String description;
    private String annotations;

    public appointment(Integer schedule_doctor, Integer speciality_doctor, Integer patient, String description, String annotations){
        this.schedule_doctor=schedule_doctor;
        this.speciality_doctor=speciality_doctor;
        this.patient=patient;
        this.description = description;
        this.annotations=annotations;
    }
=======
    /*private Integer id;
    private schedule_doctor schedule_doctor;
    private speciality_doctor speciality_doctor;
    private patient patient;
    private String descripcion;
    private String annotations;

>>>>>>> master

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

<<<<<<< HEAD
    public Integer getSchedule_doctor() {
        return schedule_doctor;
    }

    public void setSchedule_doctor(Integer schedule_doctor) {
        this.schedule_doctor = schedule_doctor;
    }

    public Integer getSpeciality_doctor() {
        return speciality_doctor;
    }

    public void setSpeciality_doctor(Integer speciality_doctor) {
        this.speciality_doctor = speciality_doctor;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
=======
    public schedule_doctor getSchedule_doctor() {
        return schedule_doctor;
    }

    public void setSchedule_doctor(schedule_doctor schedule_doctor) {
        this.schedule_doctor = schedule_doctor;
    }

    public speciality_doctor getSpeciality_doctor() {
        return speciality_doctor;
    }

    public void setSpeciality_doctor(speciality_doctor speciality_doctor) {
        this.speciality_doctor = speciality_doctor;
    }

    public patient getPatient() {
        return patient;
    }

    public void setPatient(patient patient) {
>>>>>>> master
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }*/
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
