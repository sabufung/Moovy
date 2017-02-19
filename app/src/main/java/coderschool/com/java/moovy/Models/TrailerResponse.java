package coderschool.com.java.moovy.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BuuPV on 2/19/2017.
 */

public class TrailerResponse {

    private Integer id;
    private List<Videos> results = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Videos> getResults() {
        return results;
    }

    public void setResults(List<Videos> results) {
        this.results = results;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
