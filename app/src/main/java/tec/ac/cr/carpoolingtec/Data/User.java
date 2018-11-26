package tec.ac.cr.carpoolingtec.Data;

public class User {

    private int location;
    private int destination;
    private int id;
    private boolean arrived;

    /**
     * Constructor of the user class
     * @param location current location of the user
     * @param destination place where the user wants to go
     * @param id identification number of the user
     */
    public User(int location, int destination, int id) {
        this.location = location;
        this.destination = destination;
        this.id = id;
        this.arrived = false;
    }

    public User(){}

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }
}
