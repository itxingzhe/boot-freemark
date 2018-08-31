package cn.wyb.personal;

import cn.wyb.personal.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunMain {

	public static void main(String[] args) {
		/*DecimalFormat df1 = new DecimalFormat("#.00");
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
		//d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//System.out.println(d);
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		String s = numberFormat.format(d);
		String j = null;
		System.out.println(s);
		System.out.println(s.equals(j));*/

		/*String ss = "重货:3000元/吨，轻货:3000元/立方，最低一票:33333元。阶梯价：60吨以上，重货3000元/吨，轻货3000元/立方；60吨以上，重货3000元/吨，轻货3000元/立方；";
		ss = ss.split("。")[1];
		String t = ss.contains(",") ? "," : "，";
		System.out.println(ss.replace(t, " ").substring(4));
		int a = 39;
		int b = 10;
		int i = Math.floorDiv(a, b);
		double ceil = Math.ceil(a / b);
		System.out.println(i);
		System.out.println(ceil);

		AmapCoordinateParam param = new AmapCoordinateParam();
		param.setLocations("120.13755,30.271353");
		List<PointStrVO> result = AmapUtil.getCoordinateResponse(param);
		System.out.println(result.toString());

		double lon = 71094169d / 600000d;
		double lat = 18857989d / 600000d;
		System.out.println(lon + "," + lat);


		ByteSource source = ByteSource.Util.bytes("hlhdidi89898989890OPI");
		System.out.println(new Md5Hash("123YUI	ftgh1sdgegserg23YUIftgh", source).toString());

		String s = new String("zhonghuatengfei");
		System.out.println("加密前：" + s);
		//生成MD5值
		String encryptResult = encryptMD5(s);
		System.out.println("MD5后：" + encryptResult);
		//加密
		String decryptResult = decryptMD5(encryptResult);
		System.out.println("MD5后加密：" + decryptResult);
		//解密
		String decryptResult2 = decryptMD5(decryptResult);
		System.out.println("解密为MD5后的：" + decryptResult2);*/

		/*UserPO user = new UserPO();
		user.setUname("张三");
		user.setAddress("78956@qq.com'\"<img src=x onerror=alert(1)>;//");
		String s = JSON.toJSONString(user);
		System.out.println(s);
		String s1 = StringEscapeUtils.escapeJson(s);
		StringEscapeUtils.unescapeJson(s1);
		System.out.println(s1);*/

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
		System.out.println(DateUtils.formatDateToString(instance.getTime(), DateUtils.DATE_FORMAT_FULL));

		Instant now = Instant.now();

		LocalDate now1 = LocalDate.now();
		System.out.println("今天的日期是：" + now1.toString());
		LocalDate d = LocalDate.of(2016, 2, 29);
		LocalDate d1 = d.plusYears(3);
		LocalDate d2 = d.minusYears(3);
		System.out.println(d.toString());
		System.out.println(d1.toString());
		System.out.println(d2.toString());

		//捕获日期正则
		String regDate = "^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";
		//非捕获日期正则
		String regDates = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
		//Instant now3 = Instant.now();
		long l = System.currentTimeMillis();
		boolean matches = Pattern.compile(regDate).matcher("2016-02-29").matches();
		System.out.println(matches);
		long l1 = System.currentTimeMillis();
		System.out.println(l1 - l);
		//Instant now4 = Instant.now();
		//System.out.println(Duration.between(now3,now4).toMillis());
		matches = Pattern.compile(regDates).matcher("2016-02-29").matches();
		//Instant now5 = Instant.now();
		System.out.println(matches);
		long l2 = System.currentTimeMillis();
		System.out.println(l2 - l1);
		//System.out.println(Duration.between(now4,now5).toMillis());

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


	}

	/**
	 * 获取时间字符对应的格式化类型.
	 *
	 * @param date
	 * @return java.lang.String
	 * @author wangyibin
	 * @date 2018/8/31 15:19
	 */
	public static String getDatetimeFormat(String date) {
		if (date != null) {
			date = date.trim();
			//yyyyMMddHHmmss
			String a1 = "[0-9]{4}((0?[0-9])|(1[0-2]))((0?[1-9])|([12][0-9])|(3[01]))((([01][0-9]|2[0-3])[0-5]?[0-9][0-5]?[0-9])|240?00?0)";
			//yyyyMMdd
			String a2 = "[0-9]{4}((0?[0-9])|(1[0-2]))((0?[1-9])|([12][0-9])|(3[01]))";
			//yyyy-MM-dd HH:mm:ss
			String a3 = "[0-9]{4}-((0?[0-9])|(1[0-2]))-((0?[1-9])|([12][0-9])|(3[01])) (((([01][0-9])|(2[0-3])):[0-5]?[0-9]:[0-5]?[0-9])|24:0?0:0?0)";
			//yyyy-MM-dd
			String a4 = "[0-9]{4}-((0?[0-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))";
			//yyyy-MM-dd  HH:mm
			String a5 = "[0-9]{4}-((0?[0-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1])) (((([0,1][0-9])|(2[0-3])):[0-5]?[0-9])|24:0?0)";
			boolean datea1 = Pattern.compile(a1).matcher(date).matches();
			if (datea1) {
				return "yyyyMMddHHmmss";
			}
			boolean datea2 = Pattern.compile(a2).matcher(date).matches();
			if (datea2) {
				return "yyyyMMdd";
			}
			boolean datea3 = Pattern.compile(a3).matcher(date).matches();
			if (datea3) {
				return "yyyy-MM-dd HH:mm:ss";
			}
			boolean datea4 = Pattern.compile(a4).matcher(date).matches();
			if (datea4) {
				return "yyyy-MM-dd";
			}
			boolean datea5 = Pattern.compile(a5).matcher(date).matches();
			if (datea5) {
				return "yyyy-MM-dd HH:mm";
			}
		}
		return "";
	}

	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
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
			//按位异或
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	//获取单元格各类型值，返回字符串类型
	private static String getCellValueByCell(HSSFWorkbook wb, HSSFCell cell) {
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		//判断是否为null或空串
		if (cell == null || cell.toString().trim().equals("")) {
			return "";
		}
		String cellValue = "";
		int cellType = cell.getCellType();
		if (cellType == Cell.CELL_TYPE_FORMULA) { //表达式类型
			cellType = evaluator.evaluate(cell).getCellType();
		}

		switch (cellType) {
			case Cell.CELL_TYPE_STRING: //字符串类型
				cellValue = cell.getStringCellValue().trim();
				cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
				break;
			case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC: //数值类型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					cellValue = dateFormat.format(cell.getDateCellValue());
				} else {  //否
					cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
				}
				break;
			default: //其它类型，取空串吧
				cellValue = "";
				break;
		}
		return cellValue;
	}
}
