package tec.ac.cr.carpoolingtec.connection;

import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.logic.MainBrain;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("update")
public class Updater {

    /**
     * Method that checks if the driver arrived, if he picked up a rider or if is necessary to change the route
     * @param driver driver that will be analyzed
     * @return instance of the same driver with up to date data
     */
    @POST
    @Path("driver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Driver updateDriver(Driver driver){
        if (driver.getLocation() == driver.getDestination()){
            driver.setArrived(true);
        }
        if (driver.getPeopleInside() != 4){
            for(int i = 0; i < Add.ridersArrayList.size(); i++){
                if (driver.getDestination() == Add.ridersArrayList.get(i).getDestination()){
                    Rider rider = Add.ridersArrayList.get(i);
                    if (rider.getLocation() == driver.getLocation()){
                        rider.setInCar(true);
                        driver.addPeople();
                        driver.setOnWay(false);
                        rider.setDriverID(driver.getId());
                        Add.ridersArrayList.remove(rider);
                    }else if (!driver.isOnWay()){
                        ArrayList<Integer> personRoute = MainBrain.createRoute(rider.getLocation(), rider.getDestination(), MapData.holder.getRoadMatrix());
                        ArrayList<Integer> pickupRoute = MainBrain.createRoute(driver.getLocation(), rider.getLocation(), MapData.holder.getRoadMatrix());
                        if (pickupRoute.size() <= 3){
                            if (pickupRoute.size() == 2){
                                int a = pickupRoute.get(0);
                                int b = pickupRoute.get(1);
                                if (MainBrain.checkRoad(a, b, MapData.holder.getMatrixEnableRoads())) {
                                    pickupRoute.addAll(personRoute);
                                    driver.setCurrentRoute(pickupRoute);
                                    driver.setOnWay(true);
                                    break;
                                }
                            }else{
                                pickupRoute.addAll(personRoute);
                                driver.setCurrentRoute(pickupRoute);
                                driver.setOnWay(true);
                                break;
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i < Add.driverArrayList.size(); i++){
            if (driver.getId() == Add.driverArrayList.get(i).getId()){
                Add.driverArrayList.set(i, driver);
                break;
            }
        }
        return driver;
    }

    /**
     * Method that checks if the rider is already inside a car
     * @param rider instance of the rider to analyze
     * @return the same instance with up to date data
     */
    @POST
    @Path("rider")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rider updateRider(Rider rider){
        for(int i = 0; i < Add.driverArrayList.size(); i++){
            Driver driver = Add.driverArrayList.get(i);
            if (driver.getId() == rider.getDriverID()){
                rider.setLocation(driver.getLocation());
                rider.setArrived(driver.isArrived());
                break;
            }
        }
        return rider;
    }

}
