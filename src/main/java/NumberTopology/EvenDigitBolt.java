package NumberTopology;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by farooq khan on 1/4/2019.
 */
public class EvenDigitBolt extends BaseRichBolt {
    // To output tuples from this bolt to the next bolt.
    OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
    this.collector = outputCollector;
    }

    public void execute(Tuple tuple) {
        // Get the 1st column 'random-digit' from the tuple
        int randomDigit = tuple.getInteger(0);
        if (randomDigit % 2 == 0) {
            collector.emit(new Values(randomDigit));
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // Tell Storm the schema of the output tuple for this bolt.
        // It consists of a single column called 'even-digit'
        declarer.declare(new Fields("even-digit"));
    }

}
