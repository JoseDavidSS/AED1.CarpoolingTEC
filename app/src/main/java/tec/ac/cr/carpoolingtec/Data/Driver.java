package tec.ac.cr.carpoolingtec.Data;

public class Driver extends User{

    private int peopleInside;
    private boolean arrived;

    public Driver(int location, int peopleInside) {
        super(location);
        this.peopleInside = peopleInside;
        this.arrived = false;
    }

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
}
