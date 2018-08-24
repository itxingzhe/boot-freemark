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
import java.util.Calendar;
import java.util.Date;

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
		newDate = DateUtils.add(newDate, Calendar.MILLISECOND, 777);
		System.out.println(DateUtils.formatDateToString(newDate, DateUtils.DATE_FORMAT_FULL));
		String s = DateUtils.formatDateToString(newDate, DateUtils.DATE_FORMAT_ALL);
		System.out.println(s);
		Calendar instance = Calendar.getInstance();
		instance.setTime(newDate);
		instance.set(Calendar.MONTH, 1);
		instance.set(Calendar.DAY_OF_MONTH, 29);
		System.out.println(DateUtils.formatDateToString(instance.getTime(), DateUtils.DATE_FORMAT_FULL));


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
