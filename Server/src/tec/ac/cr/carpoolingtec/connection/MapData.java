package tec.ac.cr.carpoolingtec.connection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("map_data")
public class MapData {

    //Temporal: Poner @XmlRootElement o Json en la clase a convertir
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMapData(){
        return "jaja";
    }
}