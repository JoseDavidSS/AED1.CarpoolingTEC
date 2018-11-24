package tec.ac.cr.carpoolingtec.Server;

import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.Data.SubRoute;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.util.concurrent.*;

public class Connect {

    /**
     * Method to connect to the server to retrieve the current map data
     * @return All the data of the map, in a graph form
     */
    public static TemporalHolder getMapData() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<TemporalHolder> callable = new ConnectionManagerMapData();
        Future<TemporalHolder> future = executor.submit(callable);
        return future.get();
    }

    public static Driver addDriver(Driver driver) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Driver> callable = new ConnectionManagerAddDriver();
        ((ConnectionManagerAddDriver) callable).setDriver(driver);
        Future<Driver> future = executor.submit(callable);
        return future.get();
    }

    public static Rider addRider(Rider rider) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Rider> callable = new ConnectionManagerAddRider();
        ((ConnectionManagerAddRider) callable).setRider(rider);
        Future<Rider> future = executor.submit(callable);
        return future.get();
    }

    public static SubRoute createRoute(SubRoute subRoute) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<SubRoute> callable = new ConnectionManagerCreateRoute();
        ((ConnectionManagerCreateRoute) callable).setSendRoute(subRoute);
        Future<SubRoute> future = executor.submit(callable);
        return future.get();
    }

    public static Driver updateDriver(Driver driver) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Driver> callable = new ConnectionManagerUpdateDriver();
        ((ConnectionManagerUpdateDriver) callable).setDriver(driver);
        Future<Driver> future = executor.submit(callable);
        return future.get();
    }

    public static Rider updateRider(Rider rider) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Rider> callable = new ConnectionManagerUpdateRider();
        ((ConnectionManagerUpdateRider) callable).setRider(rider);
        Future<Rider> future = executor.submit(callable);
        return future.get();
    }

}
