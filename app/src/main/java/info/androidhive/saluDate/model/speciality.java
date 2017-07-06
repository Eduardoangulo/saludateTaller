package info.androidhive.saluDate.model;

/**
 * Created by Luis on 19/05/2017.
 */

public class speciality {
    private Integer id;
    private Integer area;
    private String name;
    private String description;

    public speciality(Integer id, Integer area, String name, String description) {
        this.id=id;
        this.area=area;
        this.name=name;
        this.description=description;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
