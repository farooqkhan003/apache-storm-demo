package MultiplyerTopology;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by farooq khan on 1/3/2019.
 */
public class IntegerSpout extends BaseRichSpout {
    SpoutOutputCollector spoutOutputCollector;
    int index = 0;

    public void open(Map config, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
    }

    public void nextTuple() {
        if (index < 100) {
            this.spoutOutputCollector.emit(new Values(index));
            index++;
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("integers"));
    }
}
