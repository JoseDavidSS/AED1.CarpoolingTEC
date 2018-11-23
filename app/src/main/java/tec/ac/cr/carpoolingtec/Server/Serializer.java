package tec.ac.cr.carpoolingtec.Server;

import com.fasterxml.jackson.databind.ObjectMapper;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.io.IOException;

public class Serializer {

    public static TemporalHolder deserializeHolder(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, TemporalHolder.class);
    }

}
