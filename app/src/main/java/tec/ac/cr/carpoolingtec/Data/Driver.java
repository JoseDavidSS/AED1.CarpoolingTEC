package tec.ac.cr.carpoolingtec.Data;

import java.util.ArrayList;

public class Driver extends User{

    private int peopleInside;
    private boolean arrived;
    private boolean onWay;
    private ArrayList<Integer> currentRoute;

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
        this.onWay = false;
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

    public boolean isOnWay() {
        return onWay;
    }

    public void setOnWay(boolean onWay) {
        this.onWay = onWay;
    }

    public ArrayList<Integer> getCurrentRoute() {
        return currentRoute;
    }

    public void setCurrentRoute(ArrayList<Integer> currentRoute) {
        this.currentRoute = currentRoute;
    }

    /**
     * Method to add people to the car
     */
    public void addPeople(){
        this.peopleInside++;
    }
}
