package tec.ac.cr.carpoolingtec.Server;

import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class ConnectionManagerMapData implements Callable<TemporalHolder> {

    @Override
    public TemporalHolder call(){
        try{
            URL url = new URL("http://192.168.1.16:9080/Server_war_exploded/carpoolingtec/map_data");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            InputStream inputStream = con.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] buffer = new char[1024];
            int leidos = 0;
            StringBuilder builder = new StringBuilder();
            while ((leidos = reader.read(buffer))>0){
                builder.append(new String(buffer,0,leidos));
            }
            reader.close();

            con.disconnect();
            return Serializer.deserializeHolder(builder.toString());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
