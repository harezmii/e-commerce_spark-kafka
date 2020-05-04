package temp;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class elasticMain {
    public static void main(String[] args) throws UnknownHostException {

        Settings settings = Settings.builder().put(
                "cluster.name", "elasticsearch"
        ).build();


        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9200));

        List<DiscoveryNode> discoveryNodes = client.listedNodes();
        for (DiscoveryNode node:discoveryNodes
             ) {
            System.out.println(node.toString());
        }

        Map<String,Object> json = new HashMap<String, Object>();
        json.put("name","Suat Canbay");
        json.put("age",25);
        json.put("sinif","6a");

        IndexRequestBuilder response = client.prepareIndex("ogrenci", "_doc", "1")
                .setSource(json,XContentType.JSON);

        GetResponse get = client.prepareGet("ogrenci", "_doc", "1").get();
        System.out.println(get.getIndex());
        System.out.println(get.getFields());

    }
}
