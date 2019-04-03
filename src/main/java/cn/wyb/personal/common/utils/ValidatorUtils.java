package cn.wyb.personal.common.utils;

import cn.wyb.personal.common.enums.AMapApiStatusCodeEnum;
import cn.wyb.personal.model.vo.user.UserVo;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

/**
 * ValidatorUtils : 参数校验工具类.
 *
 * @author : wangyibin
 * @date : 2019/3/23 10:21
 */
public class ValidatorUtils {

  private static Validator validator;

  static {
    ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
        .configure()
        // 设置validator模式为快速失败返回 fals 为普通模式，校验所有
        .addProperty("hibernate.validator.fail_fast", "true")
        .buildValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  public static String validatorObject(Object obj) {
    Set<ConstraintViolation<Object>> validate = validator.validate(obj);
    for (ConstraintViolation<Object> model : validate) {
      System.out.println(model);
      System.out.println(model.getMessage());
    }
    return null;
  }

  public static void main(String[] args) {
    UserVo userVo = new UserVo();
    userVo.setAge(33);
    userVo.setUsername("张三");
    userVo.setPassword("111111");
    Integer aa = null;
    System.out.println(String.valueOf(aa));
    userVo = null;
    //validatorObject(userVo);
    AMapApiStatusCodeEnum ok = AMapApiStatusCodeEnum.valueOf("OK");
    System.out.println("cc".equals(userVo));
    System.out.println(ok);
  }

}
