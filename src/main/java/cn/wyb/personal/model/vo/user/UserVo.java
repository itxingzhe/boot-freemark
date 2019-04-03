package cn.wyb.personal.model.vo.user;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * UserVo : (请描述该类).
 *
 * @author : wangyibin
 * @date : 2019/3/23 10:45
 */
@Data
public class UserVo {

  @NotEmpty(message = "用户名不能为空！")
  private String username;

  @NotEmpty(message = "登陆密码不能为空！")
  private String password;

  private String uname;

  private Integer age;

  private String sex;

  private String address;

  private String version;

}
