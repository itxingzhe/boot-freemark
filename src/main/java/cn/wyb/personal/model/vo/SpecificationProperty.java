package cn.wyb.personal.model.vo;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;

/**
 * SpecificationProperty : (请描述该类).
 *
 * @author : wangyibin
 * @date : 2019/4/26 11:01
 */
@Data
public class SpecificationProperty {

  private String name;
  private Boolean canSell;
  private List<SpecificationProperty> superstratum = Lists.newArrayList();
  private List<SpecificationProperty> substratum = Lists.newArrayList();
}
