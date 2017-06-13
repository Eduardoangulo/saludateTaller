package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 19/05/2017.
 */

public class Specialty {
    private int img;
    private String specialtyName;

    public Specialty(int img, String name){
        this.img=img;
        setSpecialtyName(name);
    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
