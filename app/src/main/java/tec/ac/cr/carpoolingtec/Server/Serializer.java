package tec.ac.cr.carpoolingtec.Server;

import com.fasterxml.jackson.databind.ObjectMapper;
import tec.ac.cr.carpoolingtec.Logic.Holder;

import java.io.IOException;

public class Serializer {

    public static Holder deserializeHolder(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Holder holder = objectMapper.readValue(json, Holder.class);
        return holder;
    }

}
