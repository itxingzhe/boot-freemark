package cn.wyb.personal.model.vo;

import java.util.List;
import lombok.Data;

/**
 * Specification : (请描述该类).
 *
 * @author : wangyibin
 * @date : 2019/4/26 11:09
 */
@Data
public class Specification {
  private String name;
  private List<SpecificationProperty> property;

}
