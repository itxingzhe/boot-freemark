package cn.wyb.personal.model.param;

/**
 * BmapAnalyticGeographicCoordinatesParam: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/12/29 13:11
 * @see
 */
public class BmapAnalyticGeographicCoordinatesParam extends BmapApiBaseParam {

    public BmapAnalyticGeographicCoordinatesParam(String location) {
        this.location = location;
    }

    private String  location;

    private String  callback;

    private Integer pois = 0;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Integer getPois() {
        return pois;
    }

    public void setPois(Integer pois) {
        this.pois = pois;
    }
}
