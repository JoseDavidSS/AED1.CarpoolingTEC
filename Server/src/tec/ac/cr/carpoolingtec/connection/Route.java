package tec.ac.cr.carpoolingtec.connection;

import tec.ac.cr.carpoolingtec.logic.MainBrain;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("route")
public class Route {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList createRoute(ArrayList<Integer> arrayList){
        return MainBrain.createRoute(arrayList.get(0), arrayList.get(1), MapData.holder.getRoadMatrix());
    }

}
