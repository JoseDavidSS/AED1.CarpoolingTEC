package tec.ac.cr.carpoolingtec.Data;

public class Rider extends User{

    private boolean inCar;

    public Rider(int id, int location, boolean inCar, int destination) {
        super(location, destination, id);
        this.inCar = inCar;
    }

    public Rider(){}

    public boolean isInCar() {
        return inCar;
    }

    public void setInCar(boolean inCar) {
        this.inCar = inCar;
    }
}
