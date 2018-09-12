package cn.wyb.personal.advice;

import cn.wyb.personal.common.exception.BaseException;
import cn.wyb.personal.common.result.CommResponse;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionController: 公共异常处理.
 *
 * @author wangyibin
 * @date 2018/7/26 10:09
 * @see
 */
@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AuthorizationException.class)
	public CommResponse handleShiroException(Exception e) {
		return CommResponse.failure(e.getMessage());
	}

	@ExceptionHandler(AccountException.class)
	public CommResponse handleNoUserException(Exception e) {
		return CommResponse.failure(e.getMessage());
	}

	@ExceptionHandler(BaseException.class)
	public CommResponse handleBaseException(Exception e) {
		return CommResponse.failure(e);
	}

	@ExceptionHandler(AuthenticationException.class)
	public CommResponse handleAuthenticationException(Exception e) {
		return CommResponse.failure("用户名或密码错误，请重新登陆！");
	}

}
