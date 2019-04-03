package cn.wyb.personal.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils : 字符串处理工具类.
 *
 * @author : wangyibin
 * @date : 2019/4/2 10:37
 */
public class StringUtils {

  /**
   * 利用正则判断字符串中是否含有表情.
   *
   * @param content :
   * @return boolean
   * @create by : wangyibin
   * @createTime : 2019/4/2 11:46
   */
  public static boolean hasEmoji(String content) {

    Pattern pattern = Pattern
        .compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
    Matcher matcher = pattern.matcher(content);
    if (matcher.find()) {
      String group = matcher.group();
      System.out.println(group);
      return true;
    }
    return false;
  }


  /**
   * 判断字符串中是否含有表情.
   *
   * @param source : 字符串
   * @return boolean
   * @create by : wangyibin
   * @createTime : 2019/4/2 11:37
   */
  public static boolean containsEmoji(String source) {
    int len = source.length();
    for (int i = 0; i < len; i++) {
      char hs = source.charAt(i);
      System.out.println();
      if (0xd800 <= hs && hs <= 0xdbff) {
        if (source.length() > 1) {
          char ls = source.charAt(i + 1);
          int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
          if (0x1d000 <= uc && uc <= 0x1f77f) {
            return true;
          }
        }
      } else {
        if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
          return true;
        } else if (0x2B05 <= hs && hs <= 0x2b07) {
          return true;
        } else if (0x2934 <= hs && hs <= 0x2935) {
          return true;
        } else if (0x3297 <= hs && hs <= 0x3299) {
          return true;
        } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
            || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
            || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
          return true;
        }
        if (source.length() > 1 && i < source.length() - 1) {
          char ls = source.charAt(i + 1);
          if (ls == 0x20e3) {
            return true;
          }
        }
      }
    }
    return false;
  }


  public static void main(String[] args) {
    String str = "你是一个🐋吗？";
    boolean hasEmoji = hasEmoji(str);
    System.out.println(hasEmoji);
    boolean containsEmoji = containsEmoji(str);
    System.out.println(containsEmoji);
  }

}
