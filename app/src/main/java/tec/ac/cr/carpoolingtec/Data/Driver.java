package tec.ac.cr.carpoolingtec.Data;

public class Driver extends User{

    private int peopleInside;
    private boolean arrived;

    /**
     * Constructor of the driver class.
     * @param location current driver location in the map
     * @param destination place where the driver wants to go
     * @param id random identification number of the car
     */
    public Driver(int location, int destination, int id) {
        super(location, destination, id);
        this.peopleInside = 0;
        this.arrived = false;
    }

    public Driver(){}

    public int getPeopleInside() {
        return peopleInside;
    }

    public void setPeopleInside(int peopleInside) {
        this.peopleInside = peopleInside;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    /**
     * Method to add people to the car
     */
    public void addPeople(){
        this.peopleInside++;
    }
}
