package cn.wyb.personal.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * ValidatorConfig : 参数校验配置.
 *
 * @author : wangyibin
 * @date : 2019/3/23 10:25
 */
@Configuration
public class ValidatorConfig {

  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
    postProcessor.setValidator(validator());
    return postProcessor;
  }

  @Bean
  public Validator validator() {
    ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
        .configure()
        // 设置validator模式为快速失败返回 fals 为普通模式，校验所有
        .addProperty("hibernate.validator.fail_fast", "true")
        .buildValidatorFactory();
    Validator validator = validatorFactory.getValidator();
    return validator;
  }

}
