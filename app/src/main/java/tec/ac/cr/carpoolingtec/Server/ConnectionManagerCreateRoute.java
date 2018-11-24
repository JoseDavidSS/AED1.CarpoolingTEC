package tec.ac.cr.carpoolingtec.Server;

import tec.ac.cr.carpoolingtec.Data.SubRoute;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class ConnectionManagerCreateRoute implements Callable<SubRoute> {

    private SubRoute sendRoute;

    public SubRoute getSendRoute() {
        return sendRoute;
    }

    public void setSendRoute(SubRoute sendRoute) {
        this.sendRoute = sendRoute;
    }

    @Override
    public SubRoute call(){
        try{
            URL url = new URL("http://192.168.1.16:9080/Server_war_exploded/carpoolingtec/route");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            String json = Serializer.serializeRoute(this.sendRoute);
            DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
            dataOutputStream.writeBytes(json);
            dataOutputStream.flush();
            dataOutputStream.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            con.disconnect();

            return Serializer.deserializeRoute(response.toString());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
