package cn.wyb.personal.demo;

import java.util.Scanner;

/**
 * TestEncryptNote: 面试题字条加密
 *
 * @author wangyibin
 * @date 2019/1/17 18:57
 * @see
 */
public class TestEncryptNote {

    public static void main(String[] args) {

        String[] strs = new String[] { "ABCDEFGHI", "JKLMNOPQR", "STUVWXYZ*" }; // 定义一个字符串数组，存放三组字符串
        String str1 = strs[0]; // 分别放进三个字符串内
        String str2 = strs[1];
        String str3 = strs[2];

        Scanner sc1 = new Scanner(System.in); // 输入月份和日期
        Scanner sc2 = new Scanner(System.in); // 输入 大写字母字符串
        // 接受输入参数
        String m = sc1.nextLine();
        String n = sc2.nextLine();

        String tempn = n.replaceAll("\\s", "*"); // 将输入的字符串中的空格转换成*

        String temp[] = m.split("\\s+"); // 按空格分隔，获取输入的月份 和 日期
        int c = Integer.parseInt(temp[0]);
        int d = Integer.parseInt(temp[1]);

        if (c > 12 || c < 0) {
            System.out.println("输入月份不合法");
            return;
        }
        if (d < 0 || d > 31) {
            System.out.println("输入日期不合法");
            return;
        }

        int x = (c - 1) % 3; // 获取分组左移的位置

        int y = (d - 1) % 9; // 获取组内左移的位置

        if (x == 0) {
            strs[0] = str1;
            strs[1] = str2;
            strs[2] = str3;
        } else if (x == 1) {
            strs[0] = str2;
            strs[1] = str3;
            strs[2] = str1;
        } else if (x == 2) {
            strs[0] = str3;
            strs[1] = str1;
            strs[2] = str2;
        }
        System.out.println(strs[0] + " " + strs[1] + " " + strs[2]);
        char[] ch = tempn.toCharArray();// 将输入的字符串转成字符数组
        for (int i = 0; i < strs.length; i++) {
            strs[i] = leftMoveIndex(strs[i], y); // 组内字符左移
        }
        System.out.println(strs[0] + " " + strs[1] + " " + strs[2]);
        for (int j = 0; j < ch.length; j++) { // 循环输入的字符数组 ，若出现在分组中，则输出 分组的位置 和 字符出现的位置
            if (!"ABCDEFGHIJKLMNOPQRSTUVWXYZ*".contains(ch[j] + "")) {
                System.out.println("输入字符串不合法");
                return;
            } else {
                for (int i = 0; i < strs.length; i++) {
                    if (strs[i].contains(ch[j] + "")) {
                        System.out.print((i + 1) + "" + (strs[i].indexOf(ch[j] + "") + 1) + " ");
                    }
                }
            }
        }
    }

    public static String leftMoveIndex(String from, int index) {
        String first = from.substring(0, index);
        String second = from.substring(index);
        first = reChange(first);
        second = reChange(second);
        from = first + second;
        from = reChange(from);
        return from;
    }

    /**
     * 循环反转字符串
     * 
     * @param from
     * @return
     */
    public static String reChange(String from) {
        char[] froms = from.toCharArray();
        int length = froms.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = froms[i];
            froms[i] = froms[length - 1 - i];
            froms[length - 1 - i] = temp;
        }
        return String.valueOf(froms);

    }
}
