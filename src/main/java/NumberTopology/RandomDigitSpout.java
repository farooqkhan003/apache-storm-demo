package NumberTopology;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by farooq khan on 1/4/2019.
 */
public class RandomDigitSpout extends BaseRichSpout {
    // To output tuples from spout to the next stage bolt
    SpoutOutputCollector spoutOutputCollector;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
    }

    public void nextTuple() {
        int randomDigit = ThreadLocalRandom.current().nextInt(0, 10);
        // Emit the digit to the next stage bolt
        this.spoutOutputCollector.emit(new Values(randomDigit));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        // Tell Storm the schema of the output tuple for this spout.
        // It consists of a single column called 'random-digit'.
        outputFieldsDeclarer.declare(new Fields("random-digit"));
    }
}