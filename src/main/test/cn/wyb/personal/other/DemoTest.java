package cn.wyb.personal.other;

import static cn.wyb.personal.common.utils.DateUtils.getDatetimeFormat;

import cn.wyb.personal.common.result.PageResult;
import cn.wyb.personal.common.utils.AmapUtil;
import cn.wyb.personal.common.utils.CommonUtils;
import cn.wyb.personal.common.utils.DateUtils;
import cn.wyb.personal.common.utils.FileUtils;
import cn.wyb.personal.demo.LimitConcurrencyTest;
import cn.wyb.personal.model.param.AmapCoordinateParam;
import cn.wyb.personal.model.po.UserPO;
import cn.wyb.personal.model.vo.bmap.PointStrVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class DemoTest {

  @Test
  public void test() {

    LinkedList<String> linkedList = Lists.newLinkedList();
    linkedList.add("dddd");
    linkedList.add(2, "ccc");
    System.out.println(linkedList);
    System.out.println(linkedList.get(1));

    Calendar calendar = Calendar.getInstance();
    int over = Integer.MAX_VALUE - 2107054510;
    System.out.println(over / 60 / 60 / 21);
    ArrayList<Object> list = Lists.newArrayList();
    list.add(1);
    list.add(2);
    // list.addAll(null);

  }

  @Test
  public void testSpecification() {
    List<List> speList = Lists.newArrayList();
    ArrayList<String> s1 = Lists.newArrayList(new String[]{"橙", "蓝", "黄"});
    ArrayList<String> s2 = Lists.newArrayList(new String[]{"20寸", "26寸", "29寸"});
    ArrayList<Integer> s3 = Lists.newArrayList(new Integer[]{3, 4, 5});
    ArrayList<String> s4 = Lists.newArrayList(new String[]{"20G", "32G"});
    ArrayList<String> s5 = Lists.newArrayList(new String[]{"港行", "国行"});
    speList.add(s1);
    speList.add(s2);
    speList.add(s3);
    speList.add(s4);
    speList.add(s5);

    List<Object> aNew = CommonUtils.multigroupStringCombination(speList);
    System.out.println(JSON.toJSONString(aNew));
  }


  @Test
  public void limitConcurrencyTest() throws InterruptedException {
    LimitConcurrencyTest limitConcurrencyTest = new LimitConcurrencyTest();

    for (int i = 0; i < 3; i++) {
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 100; i++) {
            limitConcurrencyTest.semaphoreTest();
          }
        }
      });
      thread.start();
    }
    Thread.sleep(6000L);
  }

  @Test
  public void analysisJsonTest() {
    File file = new File(
        "C:/Users/jm/Documents/Tencent Files/1107085043/FileRecv/CTM/ctmdata.json");
    if (file.exists()) {
      String fileName = file.getName();
      System.out.println(fileName);
      FileInputStream fis = null;
      InputStreamReader isr = null;
      // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
      BufferedReader br = null;
      try {
        String line = null;
        StringBuffer content = new StringBuffer();
        fis = new FileInputStream(file);
        isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);
        while ((line = br.readLine()) != null) {
          content.append(line.trim());
        }
        String s = content.toString();
        System.out.println(s);
        JSONArray jsonObject = JSON.parseArray(s);
        System.out.println(jsonObject);

      } catch (FileNotFoundException e) {
        System.out.println("找不到指定文件");
      } catch (IOException e) {
        System.out.println("读取文件失败");
      } finally {
        FileUtils.closeAllIo(fis, isr, br, null, null, null);
      }
    }
  }

  @Test
  public void jsonTest() {
    UserPO userPO = new UserPO();
    userPO.setUname("dddd");
    userPO.setAge(33);
    UserPO user = new UserPO();
    user.setUname("dddd");
    user.setAge(33);
    ArrayList<Object> list = Lists.newArrayList();
    list.add(userPO);
    list.add(user);
    System.out.println(JSON.toJSONString(list));

    String json = "{\"total\":15,\"pageNum\":0,\"rows\":[{\"uid\":1,\"username\":\"zhangsan\",\"password\":\"28dc4103b273f13f1629d0473e44ce38\",\"uname\":\"张伞伞\",\"age\":23,\"sex\":\"男\",\"address\":\"求是路3号\",\"version\":\"0\",\"roles\":[]},{\"uid\":2,\"username\":\"zhaoliu\",\"password\":\"4c24ba976846dc67e58c3a26dc9bffd9\",\"uname\":\"张三\",\"age\":23,\"sex\":\"男\",\"address\":\"求是路3号\",\"version\":\"1\",\"roles\":[]},{\"uid\":10,\"username\":\"lisi\",\"password\":\"39d75529bc43830d18800118cfd68756\",\"uname\":\"lisi\",\"age\":18,\"sex\":\"未知\",\"address\":\"保密\",\"version\":\"0\",\"roles\":[]},{\"uid\":11,\"username\":\"lalala\",\"password\":\"df3ea1160e9427e0a50b0c11bfc00b0e\",\"uname\":\"啦啦啦\",\"age\":23,\"sex\":\"保密\",\"address\":\"\",\"version\":\"0\",\"roles\":[]},{\"uid\":12,\"username\":\"wangwu\",\"password\":\"a3b7f19b44fa558422a1146ce8076a72\",\"uname\":\"王五\",\"age\":33,\"sex\":\"男\",\"address\":\"舟山渔山岛\",\"version\":\"0\",\"roles\":[]},{\"uid\":13,\"username\":\"lilei\",\"password\":\"4594fb06ee7fdfcb756fda4647e6d583\",\"uname\":\"李雷\",\"age\":23,\"sex\":\"男\",\"address\":\"杭州市余杭区\",\"version\":\"0\",\"roles\":[]},{\"uid\":14,\"username\":\"lilei\",\"password\":\"c05ce384dc74f04ec65b2d8546fe1034\",\"uname\":\"李雷\",\"age\":23,\"sex\":\"男\",\"address\":\"杭州市余杭区\",\"version\":\"0\",\"roles\":[]},{\"uid\":15,\"username\":\"hanmeimei\",\"password\":\"0e3118eacd4e3487a3998baac2cf505a\",\"uname\":\"韩梅梅\",\"age\":23,\"sex\":\"女\",\"address\":\"杭州西湖区\",\"version\":\"0\",\"roles\":[]},{\"uid\":16,\"username\":\"hanmeimei\",\"password\":\"5c93879ef45e8a941ec018ab8f0d21ef\",\"uname\":\"韩梅梅\",\"age\":23,\"sex\":\"女\",\"address\":\"杭州西湖区\",\"version\":\"0\",\"roles\":[]},{\"uid\":17,\"username\":\"hhhha\",\"password\":\"c2895d73aea7c9ac6e8ad7b52c204b8a\",\"uname\":\"哈哈哈哈\",\"age\":34,\"sex\":\"未知\",\"address\":\"未知\",\"version\":\"0\",\"roles\":[]},{\"uid\":18,\"username\":\"hhhha\",\"password\":\"a5637f81e96b69fa02b8f8622d9ba01f\",\"uname\":\"哈哈哈哈\",\"age\":34,\"sex\":\"未知\",\"address\":\"未知\",\"version\":\"0\",\"roles\":[]},{\"uid\":19,\"username\":\"heiehi\",\"password\":\"7b7ee25000dbe6fb0c0afb0c64d8d711\",\"uname\":\"嘿嘿\",\"age\":43,\"sex\":\"未知\",\"address\":\"未知\",\"version\":\"0\",\"roles\":[]},{\"uid\":20,\"username\":\"heiehi\",\"password\":\"9e707bed32ce0abb2dcb64e7fb4cb085\",\"uname\":\"嘿嘿\",\"age\":43,\"sex\":\"未知\",\"address\":\"未知\",\"version\":\"0\",\"roles\":[]},{\"uid\":21,\"username\":\"enene\",\"password\":\"fcd7909a295b233aaea6064d0879569e\",\"uname\":\"恩恩额\",\"age\":23,\"sex\":\"未知\",\"address\":\"未知\",\"version\":\"0\",\"roles\":[]},{\"uid\":22,\"username\":\"enene\",\"password\":\"2a17ef1f92e88d69fc7e3edfa5edf721\",\"uname\":\"恩恩额\",\"age\":23,\"sex\":\"未知\",\"address\":\"未知\",\"version\":\"0\",\"roles\":[]}]}";

    JSONObject jsonObject = JSON.parseObject(json);
    PageResult result = JSON.parseObject(json, PageResult.class);
    System.out.println(jsonObject);

  }

  @Test
  public void forTest() {

    for (int i = 0; i < 1000; i++) {
      if (i == 1000 || (i != 0 && i % 90 == 0)) {
        // i = i - 90;
        System.out.println("每90次返回一次");
      }
      if (i == 998) {
        System.out.println("第999次");
      }
      System.out.println("index :" + i);
    }

  }

  @Test
  public void fileUtilsTest() {
    FileUtils.addSwaggerAnnotationForModule("F:/test", "LgAmountSystemParam.java", false);
    String path = FileUtils.getPathByClazz(null);
    System.out.println(path);
    File file = new File("E:");
    boolean exists = file.exists();
    System.out.println(exists);
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
  }

  @Test
  public void workbookTest() {
    File file = new File("C:/Users/jm/Desktop/My Files/others/puser1.xls");
    try {
      FileInputStream inputStream = new FileInputStream(file);
      Workbook workbook = new HSSFWorkbook(inputStream);
      List<HashMap> resultList = FileUtils.workbook2ObjectList(workbook, HashMap.class, null);
      System.out.println(resultList);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void numberTest() {
    DecimalFormat df1 = new DecimalFormat("#.00");
    DecimalFormat df2 = new DecimalFormat("#.#");
    DecimalFormat df4 = new DecimalFormat("#.###");
    System.out.println(df1.format(12.3000000));
    System.out.println(df2.format(12.35));
    System.out.println(df4.format(1265411.377888554414));
    long maxValue = Long.MAX_VALUE;
    System.out.println(maxValue);
    System.out.println(df4.format(maxValue));
    double d = 114.458236;
    BigDecimal b = new BigDecimal(d);
    // d = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    // System.out.println(d);
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    numberFormat.setMaximumFractionDigits(2);
    numberFormat.setRoundingMode(RoundingMode.HALF_UP);
    String s = numberFormat.format(d);
    String j = null;
    System.out.println(s);
    System.out.println(s.equals(j));

    System.out.println("integer_max:" + Integer.MAX_VALUE);
    System.out.println("fload_max:" + Float.MAX_VALUE);
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    String format = decimalFormat.format(Float.valueOf(Float.MAX_VALUE).doubleValue());
    System.out.println(format);

    BigDecimal bd1 = BigDecimal.valueOf(3);
    BigDecimal bd2 = BigDecimal.valueOf(-6);
    System.out.println("正负相加：》》》" + bd1.add(bd2));
    System.out.println("相反数：》》》" + bd1.negate());

    Long ll1 = new Long(3);
    Long ll2 = -ll1;
    System.out.println(ll1 + ".....");
    System.out.println(ll2 + ">>>>" + ll2.toString());

    double a = 333333333333333.33;
    int b1 = 333333333;
    Object c = b1;
    int d1 = (int) c;
    try {
      Field age = UserPO.class.getDeclaredField("age");
      System.out.println(age.getType());
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }

    System.out.println(Double.valueOf(a).intValue());
    System.out.println(c.getClass());
    System.out.println(d1);

    System.out.println(Double.valueOf(a).intValue());

    Short sho = null;
    sho.shortValue();
    short s8 = 1;
    System.out.println("Short equal ==  >>>>>>" + (1 == sho));

  }

  @Test
  public void AmapApiTest() {
    AmapCoordinateParam param = new AmapCoordinateParam();
    param.setLocations("120.13755,30.271353");
    List<PointStrVO> result = AmapUtil.getCoordinateResponse(param);
    System.out.println(result.toString());

    double lon = 71094169d / 600000d;
    double lat = 18857989d / 600000d;
    System.out.println(lon + "," + lat);
  }

  @Test
  public void stringSplitTest() {
    String ss = "重货:3000元/吨，轻货:3000元/立方，最低一票:33333元。阶梯价：60吨以上，重货3000元/吨，轻货3000元/立方；60吨以上，重货3000元/吨，轻货3000元/立方；";
    ss = ss.split("。")[1];
    String t = ss.contains(",") ? "," : "，";
    System.out.println(ss.replace(t, " ").substring(4));
    ss = "aaadd24455|";
    // String[] split = ss.split("\\|");
    String[] split = ss.split(",");
    System.out.println(split.length);
    int a = 39;
    int b = 10;
    int i = Math.floorDiv(a, b);
    double ceil = Math.ceil(a / b);
    System.out.println(i);
    System.out.println(ceil);
  }

  @Test
  public void escapeTest() {
    UserPO user = new UserPO();
    user.setUname("张三");
    user.setAddress("78956@qq.com'\"<img src=x onerror=alert(1)>;//");
    String s = JSON.toJSONString(user);
    System.out.println(s);
    String s1 = StringEscapeUtils.escapeJson(s);
    StringEscapeUtils.unescapeJson(s1);
    System.out.println(s1);
  }

  @Test
  public void dateTest() {
    Date date = new Date();
    Date newDate = DateUtils.addDays(date, 39);
    System.out.println(DateUtils.formatDateToString(newDate, DateUtils.DATE_FORMAT_FULL));
    System.out.println(DateUtils.formatStringToDate("2017-5-50 3:4:5", DateUtils.DATE_FORMAT_FULL));
    newDate = DateUtils.add(newDate, Calendar.MILLISECOND, 777);
    System.out.println(DateUtils.formatDateToString(newDate, DateUtils.DATE_FORMAT_FULL));
    String s = DateUtils.formatDateToString(newDate, DateUtils.DATE_FORMAT_ALL);
    System.out.println(s);
    Calendar instance = Calendar.getInstance();
    instance.setTime(newDate);
    instance.set(Calendar.MONTH, 1);
    instance.set(Calendar.DAY_OF_MONTH, 29);
    System.out
        .println(DateUtils.formatDateToString(instance.getTime(), DateUtils.DATE_FORMAT_FULL));

    Instant now = Instant.now();

    LocalDate now1 = LocalDate.now();
    System.out.println("今天的日期是：" + now1.toString());
    LocalDate d = LocalDate.of(2016, 2, 29);
    LocalDate d1 = d.plusYears(3);
    LocalDate d2 = d.minusYears(3);
    System.out.println(d.toString());
    System.out.println(d1.toString());
    System.out.println(d2.toString());

    // 捕获日期正则
    String regDate = "^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";
    // 非捕获日期正则
    String regDates = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    // Instant now3 = Instant.now();
    long l = System.currentTimeMillis();
    boolean matches = Pattern.compile(regDate).matcher("2016-02-29").matches();
    System.out.println(matches);
    long l1 = System.currentTimeMillis();
    System.out.println(l1 - l); // Instant now4 = Instant.now();
    // System.out.println(Duration.between(now3,now4).toMillis());
    matches = Pattern.compile(regDates).matcher("2016-02-29").matches();
    // Instant now5 =Instant.now();
    System.out.println(matches);
    long l2 = System.currentTimeMillis();
    System.out.println(l2 - l1);
    // System.out.println(Duration.between(now4,now5).toMillis());

    String strDate = "";
    String isDate = getDatetimeFormat(strDate);
    System.out.println("1>" + isDate);
    strDate = "14-9-30";
    isDate = getDatetimeFormat(strDate);
    System.out.println("2>" + isDate);
    strDate = "2014-9-30 15:85:45";
    isDate = getDatetimeFormat(strDate);
    System.out.println("3>" + isDate);
    strDate = "2014-9-30 15:35:75";
    isDate = getDatetimeFormat(strDate);
    System.out.println("4>" + isDate);
    strDate = "2014-13-30 25:35:45";
    isDate = getDatetimeFormat(strDate);
    System.out.println("5>" + isDate);
    strDate = "2014-9-40 15:35:45";
    isDate = getDatetimeFormat(strDate);
    System.out.println("6>" + isDate);
    strDate = "2014930153545";
    isDate = getDatetimeFormat(strDate);
    System.out.println("7>" + isDate);
    strDate = "20141231";
    isDate = getDatetimeFormat(strDate);
    System.out.println("8>" + isDate);
    strDate = "2014-12-31 23:59:59";
    isDate = getDatetimeFormat(strDate);
    System.out.println("9>" + isDate);
    strDate = "2014-12-31";
    isDate = getDatetimeFormat(strDate);
    System.out.println("10>" + isDate);
    strDate = "2014-12-31 23:59";
    isDate = getDatetimeFormat(strDate);
    System.out.println("11>" + isDate);
    strDate = "2014-12-31 24:00:00";
    isDate = getDatetimeFormat(strDate);
    System.out.println("12>" + isDate);
    System.out.println(DateUtils.formatStringToDate(strDate, DateUtils.DATE_FORMAT_FULL));
    strDate = "2014-12-31 00:00:00";
    isDate = getDatetimeFormat(strDate);
    System.out.println("13>" + isDate);

    System.out.println(DateUtils.addDays(new Date(), 1).toString());
    System.out.println(new Date().before(DateUtils.addDays(new Date(), 33333)));

    LocalDateTime localDateTimeNow = LocalDateTime.now();
    LocalDateTime localDateTime2 = LocalDateTime.of(2015, 3, 31, 13, 55);
    LocalDateTime localDateTime = localDateTime2.plusMonths(1);
    Long bb = 3L;
    int cc = 3;

    localDateTime = localDateTime.plusMonths(bb * cc);
    System.out.println(localDateTime);

    bb = null;
    System.out.println(BigDecimal.valueOf(bb));
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
}
