package NumberTopology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Created by farooq khan on 1/4/2019.
 */
public class OurSimpleTopology {
    public static void main(String[] args) throws Exception {
        // Create the topology
        TopologyBuilder builder = new TopologyBuilder();

        // Attach the random digit spout to the topology.
        // Use just 1 thread for the spout.
        builder.setSpout("random-digit-spout", new RandomDigitSpout());

        // Connect the even digit bolt to our spout.
        // The bolt will use 2 threads and the digits will be randomly
        // shuffled/distributed among the 2 threads.
        // The third parameter is formally called the parallelism hint.
        builder.setBolt("even-digit-bolt", new EvenDigitBolt(), 2)
                .shuffleGrouping("random-digit-spout");

        // Connect the multiply-by-10 bolt to our even digit bolt.
        // This bolt will use 4 threads, among which data from the
        // even digit bolt will be shuffled/distributed randomly.
        builder.setBolt("multiplied-by-ten-bolt", new MultiplyByTenBolt(), 4)
                .shuffleGrouping("even-digit-bolt");

        // Create a configuration object.
        Config conf = new Config();
        conf.setDebug(true);

        // The number of independent JVM processes this topology will use.
        conf.setNumWorkers(2);

        // Submit our topology with the configuration.
        LocalCluster localCluster = new LocalCluster();
        try {
            localCluster.submitTopology("our-simple-topology", conf, builder.createTopology());
            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            localCluster.shutdown();
        }
    }
}
