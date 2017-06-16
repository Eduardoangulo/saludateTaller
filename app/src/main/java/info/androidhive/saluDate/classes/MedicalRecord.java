package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 16/06/2017.
 */

public class MedicalRecord {
    private Integer id;
    private patient patient;
    private String blood_type;
    private double weight;
    private double height;
    private String harmful_habits;
    private String background;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHarmful_habits() {
        return harmful_habits;
    }

    public void setHarmful_habits(String harmful_habits) {
        this.harmful_habits = harmful_habits;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public patient getPatient() {
        return patient;
    }

    public void setPatient(patient patient) {
        this.patient = patient;
    }
}
