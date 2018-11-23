package tec.ac.cr.carpoolingtec.Data;

public class Rider extends User{

    private int id;
    private boolean inCar;

    public Rider(int id, int location, boolean inCar) {
        super(location);
        this.id = id;
        this.inCar = inCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInCar() {
        return inCar;
    }

    public void setInCar(boolean inCar) {
        this.inCar = inCar;
    }
}
