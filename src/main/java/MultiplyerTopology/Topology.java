package MultiplyerTopology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Created by farooq khan on 1/3/2019.
 */
public class Topology {

    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("spout1", new IntegerSpout());
        topologyBuilder.setBolt("bolt1", new MultiplierBolt()).shuffleGrouping("spout1");

        Config config = new Config();
        config.setDebug(true);


        LocalCluster localCluster = new LocalCluster();
        try {
            localCluster.submitTopology("TableTopology", config, topologyBuilder.createTopology());
            Thread.sleep(100000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            localCluster.shutdown();
        }
    }
}
