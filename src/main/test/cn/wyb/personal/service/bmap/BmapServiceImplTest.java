package cn.wyb.personal.service.bmap;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wyb.personal.common.BaseTest;
import cn.wyb.personal.model.vo.bmap.Point;

/**
 * BmapServiceImplTest: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/12/29 13:41
 * @see
 */
public class BmapServiceImplTest extends BaseTest {

    @Autowired
    private BmapService bmapService;

    @Test
    public void analyticGeographicCoordinates() {

        Point point = new Point(null, Float.valueOf("1.22365"));
        String s = bmapService.analyticGeographicCoordinates(point);
        System.out.println(s);
    }
}