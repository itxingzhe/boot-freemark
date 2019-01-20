package cn.wyb.personal.demo;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RunMain {

    public static void main(String[] args) {
        buyMore();
    }

    public static void buyMore() {
        System.out.println("请输入参数");
        Scanner sc = new Scanner(System.in);
        String totalStr = sc.nextLine();
        String commoditiesStr = sc.nextLine();
        int total = Integer.valueOf(totalStr);
        String[] split = commoditiesStr.split(" ");
        int[] commodities = new int[split.length];
        for (int x = 0; x < split.length; x++) {
            commodities[x] = Integer.valueOf(split[x]);
        }

        for (int x = 0; x < commodities.length; x++) {
            for (int y = commodities.length - 1; y > 0; y--) {
                if (x > y) {
                    break;
                }
                if (commodities[y] < commodities[x]) {
                    commodities[x] = commodities[x] ^ commodities[y];
                    commodities[y] = commodities[x] ^ commodities[y];
                    commodities[x] = commodities[x] ^ commodities[y];
                }
            }
        }
        int pay = 0;
        for (int x = 0; x < commodities.length; x++) {
            pay += commodities[x];
            if (pay > total) {
                System.out.println(pay + "");
                break;
            } else {
                // System.out.print(commodities[x] + " ");
            }
        }
    }

    /***
     * MD5值
     */
    public static String encryptMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * MD5加密
     */
    public static String decryptMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            // 按位异或
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    // 获取单元格各类型值，返回字符串类型
    private static String getCellValueByCell(HSSFWorkbook wb, HSSFCell cell) {
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        // 判断是否为null或空串
        if (cell == null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_FORMULA) { // 表达式类型
            cellType = evaluator.evaluate(cell).getCellType();
        }

        switch (cellType) {
            case Cell.CELL_TYPE_STRING: // 字符串类型
                cellValue = cell.getStringCellValue().trim();
                cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: // 数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) { // 判断日期类型
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellValue = dateFormat.format(cell.getDateCellValue());
                } else { // 否
                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            default: // 其它类型，取空串吧
                cellValue = "";
                break;
        }
        return cellValue;
    }


    public static void encryptionTest() {
        ByteSource source = ByteSource.Util.bytes("hlhdidi89898989890OPI");
        System.out.println(new Md5Hash("123YUI	ftgh1sdgegserg23YUIftgh", source).toString());

        String s = new String("zhonghuatengfei");
        System.out.println("加密前：" + s);
        // 生成MD5值
        String encryptResult = encryptMD5(s);
        System.out.println("MD5后：" + encryptResult); // 加密
        String decryptResult = decryptMD5(encryptResult);
        System.out.println("MD5后加密：" + decryptResult); // 解密
        String decryptResult2 = decryptMD5(decryptResult);
        System.out.println("解密为MD5后的：" + decryptResult2);
    }

    public void classTest() {
        // Map map = (HashMap) Map.class.newInstance();
        System.out.println(NewMap.class.getAnnotatedInterfaces());
        System.out.println(NewMap.class.getAnnotatedSuperclass().getClass());
        System.out.println(NewMap.class.getGenericInterfaces());
        System.out.println(Map.class.isInterface());
        System.out.println("map>>>>isAnonymousClass>>>>>" + Map.class.isAnonymousClass());
        System.out.println("map>>>>isLocalClass>>>>>" + Map.class.isLocalClass());
        System.out.println("map>>>>isMemberClass>>>>>" + Map.class.isMemberClass());
        System.out.println("NewMap>>>>isAnonymousClass>>>>>" + NewMap.class.isAnonymousClass());
        System.out.println("NewMap>>>>isLocalClass>>>>>" + NewMap.class.isLocalClass());
        System.out.println("NewMap>>>>isMemberClass>>>>>" + NewMap.class.isMemberClass());
        System.out.println("NewMap>>>>isMemberClass>>>>>" + NewMap.class.isMemberClass());
    }
}


class NewMap extends HashMap {
}