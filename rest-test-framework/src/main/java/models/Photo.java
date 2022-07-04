package models;

public class Photo {

    private String id;
    private String sol;
    private Camera camera;
    private String img_src;
    private String earth_date;
    private Rover rover;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSol() {
        return sol;
    }
    public void setSol(String sol) {
        this.sol = sol;
    }
    public Camera getCamera() {
        return camera;
    }
    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    public String getImg_src() {
        return img_src;
    }
    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }
    public String getEarth_date() {
        return earth_date;
    }
    public void setEarth_date(String earth_date) {
        this.earth_date = earth_date;
    }
    public Rover getRover() {
        return rover;
    }
    public void setRover(Rover rover) {
        this.rover = rover;
    }

}