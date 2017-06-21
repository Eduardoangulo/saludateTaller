package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 18/06/2017.
 */

public class appointment_processed {
    private String doctorName;
    private String specialityName;
    private String date;
    private String hour;
    private String status;
    private int id;

    public appointment_processed(String doctorName, String specialityName, String date, String hour, String status, int id) {
        this.doctorName = doctorName;
        this.specialityName = specialityName;
        this.date = date;
        this.hour = hour;
        this.status=status;
        this.id=id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
