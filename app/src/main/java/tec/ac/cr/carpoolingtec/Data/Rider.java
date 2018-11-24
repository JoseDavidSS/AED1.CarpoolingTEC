package tec.ac.cr.carpoolingtec.Data;

public class Rider extends User{

    private boolean inCar;

    /**
     * Constructor of the rider class
     * @param id identification number of the rider
     * @param location current location of the rider
     * @param inCar boolean to check if the rider is already inside the car
     * @param destination place where the rider wants to go
     */
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
