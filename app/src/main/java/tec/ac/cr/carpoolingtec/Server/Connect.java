package tec.ac.cr.carpoolingtec.Server;

import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;
import tec.ac.cr.carpoolingtec.Data.SubRoute;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;

public class Connect {

    public static TemporalHolder holder;
    public static SubRoute subRoute;
    public static Driver driver;
    public static Rider rider;

    /**
     * Method to connect to the server to retrieve the current map data
     * @return All the data of the map, in a graph form
     */
    public static TemporalHolder getMapData() throws ExecutionException, InterruptedException {
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
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
        thread.start();*/
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<TemporalHolder> callable = new ConnectionManager();
        Future<TemporalHolder> future = executor.submit(callable);
        return future.get();
    }

    public static void addDriver(final Driver driver){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    URL url = new URL("http://192.168.1.16:9080/Server_war_exploded/carpoolingtec/add/driver");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    String json = Serializer.serializeDriver(driver);
                    OutputStream os = con.getOutputStream();
                    os.write(json.getBytes("UTF-8"));
                    os.close();
                    con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void addRider(final Rider rider){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    URL url = new URL("http://192.168.1.16:9080/Server_war_exploded/carpoolingtec/add/rider");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    String json = Serializer.serializeRider(rider);
                    OutputStream os = con.getOutputStream();
                    os.write(json.getBytes("UTF-8"));
                    os.close();
                    con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static SubRoute createRoute(final SubRoute subRoute2){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    URL url = new URL("http://192.168.1.16:9080/Server_war_exploded/carpoolingtec/subRoute");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    String json = Serializer.serializeRoute(subRoute2);
                    OutputStream os = con.getOutputStream();
                    os.write(json.getBytes("UTF-8"));
                    os.close();
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
                    subRoute = Serializer.deserializeRoute(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return subRoute;
    }

    public static Driver updateDriver(final Driver driver2){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    URL url = new URL("http://192.168.1.16:9080/Server_war_exploded/carpoolingtec/update/driver");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    String json = Serializer.serializeDriver(driver2);
                    OutputStream os = con.getOutputStream();
                    os.write(json.getBytes("UTF-8"));
                    os.close();
                    InputStream inputStream = con.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    char[] buffer = new char[1024];
                    int leidos = 0;
                    StringBuilder builder = new StringBuilder();
                    while ((leidos = reader.read(buffer)) > 0){
                        builder.append(new String(buffer,0,leidos));
                    }
                    reader.close();
                    con.disconnect();
                    driver = Serializer.deserializeDriver(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return driver;
    }

    public static Rider updateRider(final Rider rider2){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    URL url = new URL("http://192.168.1.16:9080/Server_war_exploded/carpoolingtec/update/rider");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    String json = Serializer.serializeRider(rider2);
                    OutputStream os = con.getOutputStream();
                    os.write(json.getBytes("UTF-8"));
                    os.close();
                    InputStream inputStream = con.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    char[] buffer = new char[1024];
                    int leidos = 0;
                    StringBuilder builder = new StringBuilder();
                    while ((leidos = reader.read(buffer)) > 0){
                        builder.append(new String(buffer,0,leidos));
                    }
                    reader.close();
                    con.disconnect();
                    rider = Serializer.deserializeRider(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return rider;
    }

}
