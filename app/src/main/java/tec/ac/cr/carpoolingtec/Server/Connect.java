package tec.ac.cr.carpoolingtec.Server;

import android.widget.Button;
import tec.ac.cr.carpoolingtec.Logic.Holder;
import tec.ac.cr.carpoolingtec.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {

    private static Holder holder;

    public static Holder getMapData(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    //Reemplazar la url con la ipv4 de la compu
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
                    holder = Serializer.deserializeHolder(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return holder;
    }


}
