package tec.ac.cr.carpoolingtec.Server;

import tec.ac.cr.carpoolingtec.Data.Driver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class ConnectionManagerUpdateDriver implements Callable<Driver> {

    private Driver driver;
    private String url;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Method that connects to the server to update the driver data
     * @return updated driver
     */
    @Override
    public Driver call(){
        try{
            URL url = new URL("http://" + this.url + ":9080/Server_war_exploded/carpoolingtec/update/driver");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            String json = Serializer.serializeDriver(this.driver);
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
            return Serializer.deserializeDriver(response.toString());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
