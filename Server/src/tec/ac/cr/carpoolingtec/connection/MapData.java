package tec.ac.cr.carpoolingtec.connection;

import tec.ac.cr.carpoolingtec.logic.Holder;
import tec.ac.cr.carpoolingtec.logic.MainBrain;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("map_data")
public class MapData {

    public static Holder holder;

    /**
     * Method that sends the client the current graph, that has the map data
     * @return instance of the holder class with all the data
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Holder getMapData() {
        if (!MainBrain.mapCreated){
            holder = MainBrain.preparation();
            return holder;
        }
        else{
            return holder;
        }
    }

}