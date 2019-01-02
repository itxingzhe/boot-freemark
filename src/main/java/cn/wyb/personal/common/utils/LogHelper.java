package cn.wyb.personal.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogHelper: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/12/29 13:54
 * @see
 */
public class LogHelper {

    private Logger log;

    public static LogHelper getLogHelper(Class clazz) {
        LogHelper logHelper = new LogHelper();
        logHelper.log = LoggerFactory.getLogger(clazz);
        return logHelper;
    }

    public Logger getLogger() {
        return log;
    }

}
