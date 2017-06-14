package info.androidhive.saluDate.classes;

/**
 * Created by Luis on 05/05/2017.
 */

public class appointment {
    private doctor doc;
    public appointment(doctor Doc){
        this.setDoc(Doc);
    }

    public doctor getDoc() {
        return doc;
    }

    public void setDoc(doctor doc) {
        this.doc = doc;
    }
}
