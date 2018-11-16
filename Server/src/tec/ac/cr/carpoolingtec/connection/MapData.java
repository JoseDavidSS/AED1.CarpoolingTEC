package tec.ac.cr.carpoolingtec.connection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("map_data")
public class MapData {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(){
        return "ESTA";
    }


}
