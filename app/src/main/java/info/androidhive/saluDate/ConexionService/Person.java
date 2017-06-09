package info.androidhive.saluDate.ConexionService;

/**
 * Created by gustavo on 18/05/17.
 */

    public class Person {
        private Integer user_id;
        private User user;
        private String gender;
        private String dni;
        private String home_location;
        private String born_date;
        private Integer born_place;
        private String phone_number;
        private String job_speciality;
        private String civil_status;
        private Integer status;

    public Person(Integer user_id, User user, String gender, String dni, String home_location, String born_date, Integer born_place, String phone_number, String job_speciality, String civil_status, Integer status) {
        this.user_id = user_id;
        this.user = user;
        this.gender = gender;
        this.dni = dni;
        this.home_location = home_location;
        this.born_date = born_date;
        this.born_place = born_place;
        this.phone_number = phone_number;
        this.job_speciality = job_speciality;
        this.civil_status = civil_status;
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getHome_location() {
        return home_location;
    }

    public void setHome_location(String home_location) {
        this.home_location = home_location;
    }

    public String getBorn_date() {
        return born_date;
    }

    public void setBorn_date(String born_date) {
        this.born_date = born_date;
    }

    public Integer getBorn_place() {
        return born_place;
    }

    public void setBorn_place(Integer born_place) {
        this.born_place = born_place;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getJob_speciality() {
        return job_speciality;
    }

    public void setJob_speciality(String job_speciality) {
        this.job_speciality = job_speciality;
    }

    public String getCivil_status() {
        return civil_status;
    }

    public void setCivil_status(String civil_status) {
        this.civil_status = civil_status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}


