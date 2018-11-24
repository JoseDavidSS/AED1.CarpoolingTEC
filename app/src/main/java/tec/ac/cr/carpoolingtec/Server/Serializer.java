package tec.ac.cr.carpoolingtec.Server;

import android.drm.DrmRights;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.Data.SubRoute;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Serializer {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Method to deserialize a json of the holder class
     * @param json string with the json to deserialize
     * @return the instance of the holder class
     * @throws IOException in case of an error
     */
    public static TemporalHolder deserializeHolder(String json) throws IOException {
        return objectMapper.readValue(json, TemporalHolder.class);
    }

    public static String serializeDriver(Driver driver) throws JsonProcessingException {
        return objectMapper.writeValueAsString(driver);
    }

    public static Driver deserializeDriver(String json) throws IOException {
        return objectMapper.readValue(json, Driver.class);
    }

    public static String serializeRider(Rider rider) throws JsonProcessingException {
        return objectMapper.writeValueAsString(rider);
    }

    public static Rider deserializeRider(String json) throws IOException {
        return objectMapper.readValue(json, Rider.class);
    }

    public static String serializeRoute(SubRoute subRoute) throws JsonProcessingException {
        return objectMapper.writeValueAsString(subRoute);
    }

    public static SubRoute deserializeRoute(String json) throws IOException {
        return objectMapper.readValue(json, SubRoute.class);
    }

   /* public static void main(String args[]) throws ExecutionException, InterruptedException {
        TemporalHolder holder = Connect.getMapData();
        Driver driver = new Driver(1, 0, 3, 98);
        driver = Connect.addDriver(driver);
        System.out.println(driver.getId());
        Rider rider = new Rider(2018088034, 2, false, 8);
        rider = Connect.addRider(rider);
        System.out.println(rider.getId());
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(25);
        SubRoute subRoute = new SubRoute(arrayList);
        subRoute = Connect.createRoute(subRoute);
        System.out.println(subRoute.getArrayList());
        driver.setLocation(3);
        driver.setDestination(3);
        rider.setLocation(3);
        rider.setDestination(3);
        driver = Connect.updateDriver(driver);
        System.out.println(driver.isArrived());
        rider = Connect.updateRider(rider);
        System.out.println(rider.isInCar());

    }*/
}
