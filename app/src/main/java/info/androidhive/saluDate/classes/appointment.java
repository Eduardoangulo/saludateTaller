package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 05/05/2017.
 */

public class appointment {
    private Integer id;
    private schedule_doctor schedule_doctor;
    private speciality_doctor speciality_doctor;
    private patient patient;
    private String descripcion;
    private String annotations;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.patient = patient;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }
}
