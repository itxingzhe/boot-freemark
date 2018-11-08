package cn.wyb.presonal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.junit.Test;

import cn.wyb.personal.common.utils.FileUtils;

public class RunMain {

    @Test
    public void test() throws InterruptedException, IOException {

        String a = " 222 // ccccc // 22222   ";
        String b = " 222 // ccccc  //  22222   ";
        String[] split = a.split("//", 1);
        String substring = a.substring(0, a.indexOf("//"));
        String substring1 = a.substring(a.indexOf("//"));
        String[] split1 = b.split(" ");
        System.out.println(a.trim());
        System.out.println(a);
        String line = "public class RunMain {";
        String trim = line.trim();
        String aClass = trim.substring(trim.indexOf("class") + 5).trim();
        String className = aClass.substring(0, aClass.indexOf(" "));
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        String path = FileUtils.getPathByClazz(null);
        System.out.println(path);
        File file = new File("E:");
        boolean exists = file.exists();
        System.out.println(exists);

        // FileUtils.formatParamJavaCode("F:/test","LgAmountSystemParam.java",false);

        /*
         * double a = 333333333333333.33; int b = 333333333; Object c = b; int d =
         * (int)c; try { Field age = UserPO.class.getDeclaredField("age");
         * System.out.println(age.getType()); } catch (NoSuchFieldException e) {
         * e.printStackTrace(); }
         * 
         * System.out.println(Double.valueOf(a).intValue());
         * System.out.println(c.getClass()); System.out.println(d);
         *
         * System.out.println(Double.valueOf(a).intValue());
         */

        // Map map = (HashMap) Map.class.newInstance();
        /*
         * System.out.println(NewMap.class.getAnnotatedInterfaces());
         * System.out.println(NewMap.class.getAnnotatedSuperclass().getClass());
         * System.out.println(NewMap.class.getGenericInterfaces());
         * System.out.println(Map.class.isInterface());
         * System.out.println("map>>>>isAnonymousClass>>>>>" +
         * Map.class.isAnonymousClass()); System.out.println("map>>>>isLocalClass>>>>>"
         * + Map.class.isLocalClass()); System.out.println("map>>>>isMemberClass>>>>>" +
         * Map.class.isMemberClass());
         * System.out.println("NewMap>>>>isAnonymousClass>>>>>" +
         * NewMap.class.isAnonymousClass());
         * System.out.println("NewMap>>>>isLocalClass>>>>>" +
         * NewMap.class.isLocalClass());
         * System.out.println("NewMap>>>>isMemberClass>>>>>" +
         * NewMap.class.isMemberClass());
         * System.out.println("NewMap>>>>isMemberClass>>>>>" +
         * NewMap.class.isMemberClass());
         * 
         * System.out.println("integer_max:" + Integer.MAX_VALUE);
         * System.out.println("fload_max:" + Float.MAX_VALUE); DecimalFormat
         * decimalFormat = new DecimalFormat("0.00"); String format =
         * decimalFormat.format(Float.valueOf(Float.MAX_VALUE).doubleValue());
         * System.out.println(format);
         * 
         * BigDecimal bd1 = BigDecimal.valueOf(3); BigDecimal bd2 =
         * BigDecimal.valueOf(-6); System.out.println("正负相加：》》》" + bd1.add(bd2));
         * System.out.println("相反数：》》》" + bd1.negate());
         * 
         * Long ll1 = new Long(3); Long ll2 = -ll1; System.out.println(ll1 + ".....");
         * System.out.println(ll2 + ">>>>" + ll2.toString());
         * 
         * System.out.println(DateUtils.addDays(new Date(),1).toString());
         * System.out.println(new Date().before(DateUtils.addDays(new Date(),33333)));
         * 
         * 
         * LocalDateTime now = LocalDateTime.now(); LocalDateTime localDateTime2 =
         * LocalDateTime.of(2015, 3, 31, 13, 55); LocalDateTime localDateTime =
         * localDateTime2.plusMonths(1); Long bb = 3L; int cc = 3;
         * 
         * localDateTime = localDateTime.plusMonths(bb * cc);
         * System.out.println(localDateTime);
         * 
         * bb = null; System.out.println(BigDecimal.valueOf(bb));
         */

        /*
         * File file = new File("C:/Users/jm/Desktop/My Files/others/puser1.xls"); try {
         * FileInputStream inputStream = new FileInputStream(file); Workbook workbook =
         * new HSSFWorkbook(inputStream); List<HashMap> resultList =
         * FileUtils.workbook2ObjectList(workbook, HashMap.class, null);
         * System.out.println(resultList);
         * 
         * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
         * e) { e.printStackTrace(); }
         */

        /*
         * DecimalFormat df1 = new DecimalFormat("#.00"); DecimalFormat df2 = new
         * DecimalFormat("#.#"); DecimalFormat df4 = new DecimalFormat("#.###");
         * System.out.println(df1.format(12.3000000));
         * System.out.println(df2.format(12.35));
         * System.out.println(df4.format(1265411.377888554414)); long maxValue =
         * Long.MAX_VALUE; System.out.println(maxValue);
         * System.out.println(df4.format(maxValue)); double d = 114.458236; BigDecimal b
         * = new BigDecimal(d); //d = b.setScale(2,
         * BigDecimal.ROUND_HALF_UP).doubleValue(); //System.out.println(d);
         * NumberFormat numberFormat = NumberFormat.getNumberInstance();
         * numberFormat.setMaximumFractionDigits(2);
         * numberFormat.setRoundingMode(RoundingMode.HALF_UP); String s =
         * numberFormat.format(d); String j = null; System.out.println(s);
         * System.out.println(s.equals(j));
         */

        /*
         * String ss =
         * "重货:3000元/吨，轻货:3000元/立方，最低一票:33333元。阶梯价：60吨以上，重货3000元/吨，轻货3000元/立方；60吨以上，重货3000元/吨，轻货3000元/立方；";
         * ss = ss.split("。")[1]; String t = ss.contains(",") ? "," : "，";
         * System.out.println(ss.replace(t, " ").substring(4)); int a = 39; int b = 10;
         * int i = Math.floorDiv(a, b); double ceil = Math.ceil(a / b);
         * System.out.println(i); System.out.println(ceil);
         * 
         * AmapCoordinateParam param = new AmapCoordinateParam();
         * param.setLocations("120.13755,30.271353"); List<PointStrVO> result =
         * AmapUtil.getCoordinateResponse(param); System.out.println(result.toString());
         * 
         * double lon = 71094169d / 600000d; double lat = 18857989d / 600000d;
         * System.out.println(lon + "," + lat);
         * 
         * 
         * ByteSource source = ByteSource.Util.bytes("hlhdidi89898989890OPI");
         * System.out.println(new Md5Hash("123YUI	ftgh1sdgegserg23YUIftgh",
         * source).toString());
         * 
         * String s = new String("zhonghuatengfei"); System.out.println("加密前：" + s);
         * //生成MD5值 String encryptResult = encryptMD5(s); System.out.println("MD5后：" +
         * encryptResult); //加密 String decryptResult = decryptMD5(encryptResult);
         * System.out.println("MD5后加密：" + decryptResult); //解密 String decryptResult2 =
         * decryptMD5(decryptResult); System.out.println("解密为MD5后的：" + decryptResult2);
         */

        /*
         * UserPO user = new UserPO(); user.setUname("张三");
         * user.setAddress("78956@qq.com'\"<img src=x onerror=alert(1)>;//"); String s =
         * JSON.toJSONString(user); System.out.println(s); String s1 =
         * StringEscapeUtils.escapeJson(s); StringEscapeUtils.unescapeJson(s1);
         * System.out.println(s1);
         */

        /*
         * Date date = new Date(); Date newDate = DateUtils.addDays(date, 39);
         * System.out.println(DateUtils.formatDateToString(newDate,
         * DateUtils.DATE_FORMAT_FULL));
         * System.out.println(DateUtils.formatStringToDate("2017-5-50 3:4:5",
         * DateUtils.DATE_FORMAT_FULL)); newDate = DateUtils.add(newDate,
         * Calendar.MILLISECOND, 777);
         * System.out.println(DateUtils.formatDateToString(newDate,
         * DateUtils.DATE_FORMAT_FULL)); String s =
         * DateUtils.formatDateToString(newDate, DateUtils.DATE_FORMAT_ALL);
         * System.out.println(s); Calendar instance = Calendar.getInstance();
         * instance.setTime(newDate); instance.set(Calendar.MONTH, 1);
         * instance.set(Calendar.DAY_OF_MONTH, 29);
         * System.out.println(DateUtils.formatDateToString(instance.getTime(),
         * DateUtils.DATE_FORMAT_FULL));
         * 
         * Instant now = Instant.now();
         * 
         * LocalDate now1 = LocalDate.now(); System.out.println("今天的日期是：" +
         * now1.toString()); LocalDate d = LocalDate.of(2016, 2, 29); LocalDate d1 =
         * d.plusYears(3); LocalDate d2 = d.minusYears(3);
         * System.out.println(d.toString()); System.out.println(d1.toString());
         * System.out.println(d2.toString());
         * 
         * //捕获日期正则 String regDate =
         * "^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";
         * //非捕获日期正则 String regDates =
         * "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
         * //Instant now3 = Instant.now(); long l = System.currentTimeMillis(); boolean
         * matches = Pattern.compile(regDate).matcher("2016-02-29").matches();
         * System.out.println(matches); long l1 = System.currentTimeMillis();
         * System.out.println(l1 - l); //Instant now4 = Instant.now();
         * //System.out.println(Duration.between(now3,now4).toMillis()); matches =
         * Pattern.compile(regDates).matcher("2016-02-29").matches(); //Instant now5 =
         * Instant.now(); System.out.println(matches); long l2 =
         * System.currentTimeMillis(); System.out.println(l2 - l1);
         * //System.out.println(Duration.between(now4,now5).toMillis());
         * 
         * String strDate = ""; String isDate = getDatetimeFormat(strDate);
         * System.out.println("1>" + isDate); strDate = "14-9-30"; isDate =
         * getDatetimeFormat(strDate); System.out.println("2>" + isDate); strDate =
         * "2014-9-30 15:85:45"; isDate = getDatetimeFormat(strDate);
         * System.out.println("3>" + isDate); strDate = "2014-9-30 15:35:75"; isDate =
         * getDatetimeFormat(strDate); System.out.println("4>" + isDate); strDate =
         * "2014-13-30 25:35:45"; isDate = getDatetimeFormat(strDate);
         * System.out.println("5>" + isDate); strDate = "2014-9-40 15:35:45"; isDate =
         * getDatetimeFormat(strDate); System.out.println("6>" + isDate); strDate =
         * "2014930153545"; isDate = getDatetimeFormat(strDate); System.out.println("7>"
         * + isDate); strDate = "20141231"; isDate = getDatetimeFormat(strDate);
         * System.out.println("8>" + isDate); strDate = "2014-12-31 23:59:59"; isDate =
         * getDatetimeFormat(strDate); System.out.println("9>" + isDate); strDate =
         * "2014-12-31"; isDate = getDatetimeFormat(strDate); System.out.println("10>" +
         * isDate); strDate = "2014-12-31 23:59"; isDate = getDatetimeFormat(strDate);
         * System.out.println("11>" + isDate); strDate = "2014-12-31 24:00:00"; isDate =
         * getDatetimeFormat(strDate); System.out.println("12>" + isDate);
         * System.out.println(DateUtils.formatStringToDate(strDate,
         * DateUtils.DATE_FORMAT_FULL)); strDate = "2014-12-31 00:00:00"; isDate =
         * getDatetimeFormat(strDate); System.out.println("13>" + isDate);
         */

    }

    @Test
    public void showURL() throws IOException {

        // 第一种：获取类加载的根路径 D:\git\daotie\daotie\target\classes
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);

        // 获取当前类的所在工程路径; 如果不加“/” 获取当前类的加载目录 D:\git\daotie\daotie\target\classes\my
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径 D:\git\daotie\daotie
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);

        // 第三种： file:/D:/git/daotie/daotie/target/classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath);

        // 第四种： D:\git\daotie\daotie
        System.out.println(System.getProperty("user.dir"));
        /*
         * 结果： C:\Documents and Settings\Administrator\workspace\projectName 获取当前工程路径
         */

        // 第五种： 获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));

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
}

class NewMap extends HashMap {
}