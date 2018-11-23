package tec.ac.cr.carpoolingtec.connection;

import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("update")
public class Updater {

    @POST
    @Path("driver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Driver updateDriver(Driver driver){
        //Anadir aqui la oomprobacion para cambiar ruta
        if (driver.getLocation() == driver.getDestination()){
            driver.setArrived(true);
        }
        for(int i = 0; i < Add.ridersArrayList.size(); i++){
            if (driver.getLocation() == Add.ridersArrayList.get(i).getLocation() && driver.getDestination() == Add.ridersArrayList.get(i).getDestination()){
                if (driver.getPeopleInside() != 4){
                    driver.addPeople();
                    Add.ridersArrayList.get(i).setInCar(true);
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

    @POST
    @Path("rider")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rider updateRider(Rider rider){
        for(int i = 0; i < Add.driverArrayList.size(); i++){
            if (rider.getId() == Add.ridersArrayList.get(i).getId()){
                if (Add.ridersArrayList.get(i).isInCar()){
                    rider.setInCar(true);
                }
                break;
            }
        }
        return rider;
    }

}
