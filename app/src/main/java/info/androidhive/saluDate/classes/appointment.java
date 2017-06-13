package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 05/05/2017.
 */

public class appointment {
    private Doctor doc;
    public appointment(Doctor Doc){
        this.setDoc(Doc);
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }
}
