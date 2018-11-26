package tec.ac.cr.carpoolingtec.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.Data.SubRoute;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.io.IOException;

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

    /**
     * Method to serialize a driver
     * @param driver driver that will be serialized
     * @return serialized driver as json
     * @throws JsonProcessingException in case of an error
     */
    public static String serializeDriver(Driver driver) throws JsonProcessingException {
        return objectMapper.writeValueAsString(driver);
    }

    /**
     * Method to deserialize a driver
     * @param json json with the driver data
     * @return instance of the driver
     * @throws IOException in case of an error
     */
    public static Driver deserializeDriver(String json) throws IOException {
        return objectMapper.readValue(json, Driver.class);
    }

    /**
     * Method to serialize a rider
     * @param rider rider that will be serialized
     * @return serialized rider as json
     * @throws JsonProcessingException in case of an error
     */
    public static String serializeRider(Rider rider) throws JsonProcessingException {
        return objectMapper.writeValueAsString(rider);
    }

    /**
     * Method to deserialize a rider
     * @param json json with the rider data
     * @return instance of the rider
     * @throws IOException in case of an error
     */
    public static Rider deserializeRider(String json) throws IOException {
        return objectMapper.readValue(json, Rider.class);
    }

    /**
     * Method to serialize a route
     * @param subRoute route that will be serialized
     * @return serialized route as json
     * @throws JsonProcessingException in case of an error
     */
    public static String serializeRoute(SubRoute subRoute) throws JsonProcessingException {
        return objectMapper.writeValueAsString(subRoute);
    }

    /**
     * Method to deserialize a route
     * @param json json with the route data
     * @return instance of the route
     * @throws IOException in case of an error
     */
    public static SubRoute deserializeRoute(String json) throws IOException {
        return objectMapper.readValue(json, SubRoute.class);
    }
}
