package coderschool.com.java.moovy.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BuuPV on 2/16/2017.
 */

public class Dates {

    private String maximum;
    private String minimum;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
