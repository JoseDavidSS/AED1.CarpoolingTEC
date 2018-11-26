package tec.ac.cr.carpoolingtec.connection;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class Serializer {

    /**
     * Method to serialize to xml
     */
    public static void serializeXML(){
        XmlMapper xmlMapper = new XmlMapper();
       // xmlMapper.writeValue(new File("simple_bean.xml"), new SimpleBean());
       // File file = new File("simple_bean.xml");
    }

    /**
     * Method to deserialize from xml
     * @throws IOException in case of an error
     */
    public static void deserializeXML() throws IOException {
        File file = new File("simple_bean.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = initialReader(new FileInputStream(file));
       // SimpleBean value = xmlMapper.readValue(xml, SimpleBean.class);
    }

    /**
     * Helper method to deserialize
     * @param fileInputStream file to be deserialized
     * @return string with the data to be deserialized
     * @throws IOException in case of an error
     */
    public static String initialReader(FileInputStream fileInputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
