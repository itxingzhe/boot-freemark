package cn.wyb.personal.common.utils;

import org.apache.shiro.SecurityUtils;

import cn.wyb.personal.model.po.UserPO;

/**
 * UserUtils: 用户信息工具类
 *
 * @author wangyibin
 * @date 2018/11/9 10:39
 * @see
 */
public class UserUtils {

    /**
     * getLoginUser : 获取当前登录的用户
     *
     * @author wangyibin
     * @date 2018/11/9 10:45
     * @param
     * @return cn.wyb.personal.model.po.UserPO
     * 
     */
    public static UserPO getLoginUser() {
        Object user = SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            return (UserPO) user;
        }
        return null;
    }

    /**
     * getLoginUserId : 获取当前登录用户的Id
     *
     * @author wangyibin
     * @date 2018/11/9 10:45
     * @param
     * @return java.lang.Long
     * 
     */
    public static Long getLoginUserId() {
        UserPO user = getLoginUser();
        if (user != null) {
            return user.getUid().longValue();
        }
        return null;
    }

}
