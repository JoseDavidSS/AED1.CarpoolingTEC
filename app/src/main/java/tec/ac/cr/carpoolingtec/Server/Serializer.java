package tec.ac.cr.carpoolingtec.Server;

import com.fasterxml.jackson.databind.ObjectMapper;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.io.IOException;

public class Serializer {

    /**
     * Method to deserialize a json of the holder class
     * @param json string with the json to deserialize
     * @return the instance of the holder class
     * @throws IOException in case of an error
     */
    public static TemporalHolder deserializeHolder(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, TemporalHolder.class);
    }

}
