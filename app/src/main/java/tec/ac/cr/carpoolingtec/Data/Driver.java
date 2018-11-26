package tec.ac.cr.carpoolingtec.Data;

import java.util.ArrayList;

public class Driver extends User{

    private int peopleInside;
    private boolean onWay;
    private ArrayList<Integer> currentRoute;
    private ArrayList<Rider> passengers;

    /**
     * Constructor of the driver class.
     * @param location current driver location in the map
     * @param destination place where the driver wants to go
     * @param id random identification number of the car
     */
    public Driver(int location, int destination, int id) {
        super(location, destination, id);
        this.peopleInside = 0;
        this.onWay = false;
    }

    public Driver(){}

    public int getPeopleInside() {
        return peopleInside;
    }

    public void setPeopleInside(int peopleInside) {
        this.peopleInside = peopleInside;
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

    public ArrayList<Rider> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Rider> passengers) {
        this.passengers = passengers;
    }

    /**
     * Method to add people to the car
     */
    public void addPeople(){
        this.peopleInside++;
    }

    public void addRider(Rider rider){
        this.passengers.add(rider);
    }
}
