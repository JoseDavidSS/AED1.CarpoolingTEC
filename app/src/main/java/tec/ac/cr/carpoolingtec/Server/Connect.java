package tec.ac.cr.carpoolingtec.Server;

import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.Data.SubRoute;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.util.concurrent.*;

public class Connect {

    public static String url = "192.168.43.175";

    /**
     * Method to connect to the server to retrieve the current map data
     * @return All the data of the map, in a graph form
     */
    public static TemporalHolder getMapData() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<TemporalHolder> callable = new ConnectionManagerMapData();
        ((ConnectionManagerMapData) callable).setUrl(url);
        Future<TemporalHolder> future = executor.submit(callable);
        return future.get();
    }

    /**
     * Method that connects to the server to add the driver
     * @param driver driver to be added
     * @return the same driver, this is for confirmation that the everything was successful
     * @throws ExecutionException in case of an error
     * @throws InterruptedException in case of an error
     */
    public static Driver addDriver(Driver driver) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Driver> callable = new ConnectionManagerAddDriver();
        ((ConnectionManagerAddDriver) callable).setDriver(driver);
        ((ConnectionManagerAddDriver) callable).setUrl(url);
        Future<Driver> future = executor.submit(callable);
        return future.get();
    }

    /**
     * Method that connects to the server to add the rider
     * @param rider rider to be added
     * @return the same rider, this is for confirmation
     * @throws ExecutionException in case of an error
     * @throws InterruptedException in case of an error
     */
    public static Rider addRider(Rider rider) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Rider> callable = new ConnectionManagerAddRider();
        ((ConnectionManagerAddRider) callable).setRider(rider);
        ((ConnectionManagerAddRider) callable).setUrl(url);
        Future<Rider> future = executor.submit(callable);
        return future.get();
    }

    /**
     * Method that connects to the server to create a route between 2 points
     * @param subRoute arraylist with the 2 points that will be connected
     * @return an arraylist with the new route to follow
     * @throws ExecutionException in case of an error
     * @throws InterruptedException in case of an error
     */
    public static SubRoute createRoute(SubRoute subRoute) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<SubRoute> callable = new ConnectionManagerCreateRoute();
        ((ConnectionManagerCreateRoute) callable).setSendRoute(subRoute);
        ((ConnectionManagerCreateRoute) callable).setUrl(url);
        Future<SubRoute> future = executor.submit(callable);
        return future.get();
    }

    /**
     * Method that connects to the server to update the driver data
     * @param driver instance of the driver to be updated
     * @return updated driver
     * @throws ExecutionException in case of an error
     * @throws InterruptedException in case of an error
     */
    public static Driver updateDriver(Driver driver) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Driver> callable = new ConnectionManagerUpdateDriver();
        ((ConnectionManagerUpdateDriver) callable).setDriver(driver);
        ((ConnectionManagerUpdateDriver) callable).setUrl(url);
        Future<Driver> future = executor.submit(callable);
        return future.get();
    }

    /**
     * Method that connects to the server to update the rider
     * @param rider instance of the rider to be updated
     * @return updated rider
     * @throws ExecutionException in case of an error
     * @throws InterruptedException in case of an error
     */
    public static Rider updateRider(Rider rider) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Rider> callable = new ConnectionManagerUpdateRider();
        ((ConnectionManagerUpdateRider) callable).setRider(rider);
        ((ConnectionManagerUpdateRider) callable).setUrl(url);
        Future<Rider> future = executor.submit(callable);
        return future.get();
    }

}
