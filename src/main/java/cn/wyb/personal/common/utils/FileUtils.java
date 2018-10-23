package cn.wyb.personal.common.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * FileUtils: 文件操作工具类
 *
 * @author wangyibin
 * @date 2018/10/15 16:17
 * @see
 */
public class FileUtils {

    public static final String SUFFIX_XLS         = ".xls";
    public static final String SUFFIX_XLSX        = ".xlsx";
    public static final String SUFFIX_JPG         = ".jpg";
    public static final String SUFFIX_PNG         = ".png";
    public static final String SUFFIX_TXT         = ".txt";
    public static final String SHEET_DEFAULT_NAME = "Sheet1";

    /**
     * makePath : 创建文件存储路径，为防止一个目录下面出现太多文件，要使用hash算法打散存储
     *
     * @author wangyibin
     * @date 2018/10/15 16:18
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return java.lang.String
     * 
     */
    public static String makePath(String filename, String savePath) {
        // 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf; // 0--15
        int dir2 = (hashcode & 0xf0) >> 4; // 0-15
        // 构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3 upload\3\5
        // File既可以代表文件也可以代表目录
        File file = new File(dir);
        // 如果目录不存在
        if (!file.exists()) {
            // 创建目录
            file.mkdirs();
        }
        return dir;
    }

    /**
     * makeFileName : 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     *
     * @author wangyibin
     * @date 2018/10/15 16:23
     * @param filename 文件的原始名称
     * @return java.lang.String
     * 
     */
    public static String makeFileName(String filename) {
        // 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * getWorkbookByUploadFile : 根据上传文件获取Workbook
     *
     * @author wangyibin
     * @date 2018/10/15 17:10
     * @param file
     * @return org.apache.poi.ss.usermodel.Workbook
     *
     */
    public static Workbook getWorkbookByUploadFile(MultipartFile file) {

        if (file == null) {
            return null;
        }
        String fileName = file.getName();
        Workbook workbook = null;
        try {
            if (fileName.endsWith(SUFFIX_XLS)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (fileName.endsWith(SUFFIX_XLSX)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * getCellValue : 获取每个单元格的值
     *
     * @author wangyibin
     * @date 2018/10/15 17:28
     * @param cell
     * @return java.lang.Object
     * 
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date dateCellValue = cell.getDateCellValue();
                        if (dateCellValue != null) {
                            value = DateUtils.formatDateToString(dateCellValue, DateUtils.DATE_FORMAT_FULL);
                        }
                    } else {
                        value = cell.getNumericCellValue();
                    }
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value;
    }

    public static <T> List<T> workbook2ObjectList(Workbook workbook, Class<T> clazz, String sheetName) {

        ArrayList<T> resultList = Lists.newArrayList();
        if (sheetName == null || sheetName.equals("")) {
            sheetName = SHEET_DEFAULT_NAME;
        }
        if (workbook != null && clazz != null) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return resultList;
            }
            // 获取单元格的行数
            int lastRowNum = sheet.getLastRowNum();
            short lastCellNum = 0;
            if (lastRowNum > 0) {
                // 取表头字段
                // 根据表头信息取实体类的字段存入map.单元格的列数为key
                Row header = sheet.getRow(0);
                ArrayList<String> headerCellValues = Lists.newArrayList();
                HashMap<Integer, Field> map = Maps.newHashMap();
                if (header != null) {
                    lastCellNum = header.getLastCellNum();
                    for (int i = 0; i < lastCellNum; i++) {
                        Cell cell = header.getCell(i);
                        Field field = null;
                        String stringCellValue = null;
                        if (cell != null) {
                            try {
                                stringCellValue = cell.getStringCellValue();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (stringCellValue != null && !stringCellValue.equals("")) {
                                try {
                                    field = clazz.getDeclaredField(stringCellValue);
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        headerCellValues.add(StringUtils.isBlank(stringCellValue) ? i + "" : stringCellValue);
                        map.put(Integer.valueOf(i), field);
                    }
                }
                // 表格数据封装进实体类集合
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        try {
                            T obj = clazz.newInstance();
                            for (int j = 0; j < lastCellNum; j++) {
                                Cell cell = row.getCell(j);
                                if (cell == null) {
                                    continue;
                                }
                                Object cellValue = getCellValue(row.getCell(j));
                                if (obj instanceof Map) {
                                    ((Map) obj).put(headerCellValues.get(j), getCellValue(row.getCell(j)));
                                } else {
                                    Field field = map.get(Integer.valueOf(j));
                                    if (field == null) {
                                        continue;
                                    }
                                    if (cellValue != null) {
                                        if (cellValue.getClass() != field.getType()) {
                                            if (cellValue.getClass() == Double.class) {
                                                Double doubleCellValue = (Double) cellValue;
                                                if (field.getType() == Integer.class) {
                                                    cellValue = Integer.valueOf(doubleCellValue.intValue());
                                                } else if (field.getType() == Short.class) {
                                                    cellValue = Short.valueOf(doubleCellValue.shortValue());
                                                } else if (field.getType() == Long.class) {
                                                    cellValue = Long.valueOf(doubleCellValue.longValue());
                                                } else if (field.getType() == Float.class) {
                                                    cellValue = Float.valueOf(doubleCellValue.floatValue());
                                                }
                                            }
                                        }
                                        if (cellValue.getClass() == field.getType()) {
                                            String methodName = "set" + toUpperFirstChar(field.getName());
                                            Method method = clazz.getMethod(methodName, field.getType());
                                            method.invoke(obj, cellValue);
                                        }
                                    }
                                }
                            }
                            resultList.add(obj);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * toUpperFirstChar : 首字母变为大写
     *
     * @author wangyibin
     * @date 2018/10/18 10:31
     * @param str
     * @return java.lang.String
     *
     */
    public static String toUpperFirstChar(String str) {
        if (str != null && str != "") {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

}
