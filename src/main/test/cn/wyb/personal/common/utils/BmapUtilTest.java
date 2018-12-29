package cn.wyb.personal.common.utils;

import java.util.Map;

import org.junit.Test;

import cn.wyb.personal.common.BaseTest;
import cn.wyb.personal.model.vo.bmap.PointStrVO;

/**
 * BmapUtilTest: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/12/29 10:48
 * @see
 */
public class BmapUtilTest extends BaseTest {

    @Test
    public void getAround() {
        PointStrVO point = new PointStrVO("116.42581", "38.50964");
        Map around = BmapUtil.getAround(point.getY(), point.getX(), "20");
        System.out.println(around);// {maxLat=116.42603965368922, minLat=116.42558034631078,
                                   // minLng=38.50946029520556, maxLng=38.50981970479444}
    }

    @Test
    public void getDistance() {
        String distance = BmapUtil.getDistance("116.42581", "38.50964", "116.42603965368922", "38.50946029520556");
        System.out.println(distance);// 27.070776468256593
    }
}