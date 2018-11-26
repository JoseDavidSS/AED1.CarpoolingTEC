package tec.ac.cr.carpoolingtec.Data;

import java.util.ArrayList;

public class SubRoute {

    private ArrayList<Integer> arrayList;

    /**
     * Constructor of the SubRoute class
     * @param arrayList current route to follow
     */
    public SubRoute(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public SubRoute(){}

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }
}
