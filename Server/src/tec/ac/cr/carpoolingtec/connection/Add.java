package tec.ac.cr.carpoolingtec.connection;

import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("add")
public class Add {

    public static ArrayList<Driver> driverArrayList = new ArrayList<>();
    public static ArrayList<Rider> ridersArrayList = new ArrayList<>();

    /**
     * Method to add a driver to the server
     * @param driver instance of the driver that is received from the client
     */
    @POST
    @Path("driver")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addDriver(Driver driver){
        driverArrayList.add(driver);
    }

    /**
     * Method to add a rider to the server
     * @param rider instance of the rider that is received from the client
     */
    @POST
    @Path("rider")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addRider(Rider rider){
        ridersArrayList.add(rider);
    }

}
