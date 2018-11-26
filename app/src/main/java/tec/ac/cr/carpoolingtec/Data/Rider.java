package tec.ac.cr.carpoolingtec.Data;

public class Rider extends User{

    private boolean inCar;
    private int driverID;

    /**
     * Constructor of the rider class
     * @param id identification number of the rider
     * @param location current location of the rider
     * @param destination place where the rider wants to go
     */
    public Rider(int id, int location, int destination) {
        super(location, destination, id);
        this.inCar = false;
        this.driverID = -1;
    }

    public Rider(){}

    public boolean isInCar() {
        return inCar;
    }

    public void setInCar(boolean inCar) {
        this.inCar = inCar;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }
}
