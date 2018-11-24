package tec.ac.cr.carpoolingtec.connection;

public class Serializer {
    public void serializer throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("simple_bean.xml"), new SimpleBean());
        File file = new File("simple_bean.xml");
    }
    public void deserializer throws IOException{
        File file = new File("simple_bean.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = initialReader(new FileInputStream(file));
        SimpleBean value = xmlMapper.readValue(xml, SimpleBean.class);
    }
    public static String initialReader throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
