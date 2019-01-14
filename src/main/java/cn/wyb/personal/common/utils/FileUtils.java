package cn.wyb.personal.common.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import cn.wyb.personal.model.vo.LgFaqVO;
import cn.wyb.personal.pdf.MyFontsProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * FileUtils: 文件操作工具类
 *
 * @author wangyibin
 * @date 2018/10/15 16:17
 * @see
 */
public class FileUtils {

    public static final String SUFFIX_XLS                   = ".xls";
    public static final String SUFFIX_XLSX                  = ".xlsx";
    public static final String SUFFIX_JPG                   = ".jpg";
    public static final String SUFFIX_PNG                   = ".png";
    public static final String SUFFIX_TXT                   = ".txt";
    public static final String SUFFIX_JAVA                  = ".java";
    public static final String SUFFIX_PDF                   = ".pdf";
    public static final String SHEET_DEFAULT_NAME           = "Sheet1";
    public static final String PUNCTUATION_SLASH            = "/";
    public static final String PUNCTUATION_SLASH_DOUBLE     = "//";
    public static final String PUNCTUATION_BACKSLASH        = "\\";
    public static final String PUNCTUATION_BACKSLASH_DOUBLE = "\\\\";
    public static final String LINE_BREAK                   = "\r\n";
    public static final String ENCODING_UTF8                = "UTF-8";
    private static int         interval                     = -5;

    public static void main(String[] args) throws FileNotFoundException {
        LgFaqVO faq = new LgFaqVO();
        faq.setTitle("聚运通支付");
        faq.setText("<p>\n"
                + "\t<a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a>\n"
                + "</p>\n" + "<p>\n"
                + "\t<a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线</a>\n"
                + "</p>\n" + "<p>\n"
                + "\t<img src=\"http://image.jumore.test/jfs1/000/18/E1/rBIBPVw2zESAZb-WAAHeuH5Yg5Y126.jpg\" title=\"1.jpg\" alt=\"1.jpg\" />\n"
                + "</p>\n" + "<p>\n"
                + "\t<a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协</a>\n"
                + "</p>\n" + "<p>\n"
                + "\t<a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a><a href=\"https://manage.jumoreyun.test/index?page=/faq/list#\">在线支付服务协议</a>\n"
                + "</p>\n" + "<p>\n"
                + "\t<span style=\"color:#4F4F4F;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过模版导出PDF的方法有很多，但是思路都大致相同，总结起来就是&nbsp;</span><span style=\"font-weight:700;color:#4F4F4F;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">1&nbsp;</span><span style=\"color:#4F4F4F;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">渲染模版&nbsp;</span><span style=\"font-weight:700;color:#4F4F4F;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">2</span><span style=\"color:#4F4F4F;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">&nbsp;导出模版。以为之前使用过freemarker，所以我这里<span style=\"background-color:#E53333;\">渲染模版的技术用的是freemarker，然后在使用itextpdf将渲染好的模版导出</span>。</span>\n"
                + "</p>\n" + "<p>\n" + "\t<br />\n</p>");
        String s = PdfUtils.convertToPdf("/static/ftl", "hello.html", faq.getTitle() + SUFFIX_PDF, faq);
        /*
         * PDFKit pdfKit = new PDFKit(); String s = pdfKit.exportToFile("hello.pdf",
         * faq);
         */
        System.out.println(s);
    }

    public static ElementList parseHtml(final String html, final String imgPath) {
        CSSResolver cssResolver = new StyleAttrCSSResolver();

        MyFontsProvider fontProvider = new MyFontsProvider();// 中文字体设置
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        // 设置html中图片的路径
        htmlContext.setImageProvider(new AbstractImageProvider() {
            public String getImageRootPath() {
                return imgPath;
            }
        });
        htmlContext.autoBookmark(false);

        ElementList elements = new ElementList();
        ElementHandlerPipeline end = new ElementHandlerPipeline(elements, null);
        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, end);
        CssResolverPipeline cssPipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
        XMLWorker worker = new XMLWorker(cssPipeline, true);
        XMLParser p = new XMLParser(worker);
        try {
            p.parse(new ByteArrayInputStream(html.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elements;
    }

    private static void convertToPDF(PdfWriter writer, Document document, String htmlString) throws Exception {
        document.open();
        MyFontsProvider fontProvider = new MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
        // writer.setPageEvent(new BackGroundImage());
        try {
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlString.getBytes("UTF-8")),
                    XMLWorkerHelper.class.getResourceAsStream("/default.css"), Charset.forName("UTF-8"), fontProvider);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

    }

    public static void createFileByFreemarker() {
        try {
            Configuration config = new Configuration();
            HashMap<Object, Object> map = Maps.newHashMap();
            config.setDefaultEncoding("UTF-8");
            config.setDirectoryForTemplateLoading(new File("/static/ftl"));
            Template template = config.getTemplate("hello.html");
            FileWriter fileWriter = new FileWriter(new File(""));
            template.process(map, new FileWriter("F:\\bb.html"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取class类所在的项目路径
     *
     * @author wangyibin
     * @date 2018/11/7 13:55
     * @param clazz
     * @return4
     */
    public static String getPathByClazz(Class clazz) {
        if (clazz == null) {
            return null;
        }
        String path = clazz.getResource("/").getPath();
        path = path.substring(1, path.indexOf("/target"));
        path += "/src/main/java/";
        String aPackage = clazz.getPackage().getName().replace(".", "/");
        path += aPackage;
        return path;
    }

    /**
     * formatModuleAnnotation : 格式化注解为标准样式
     *
     * @author wangyibin
     * @date 2018/11/6 11:11
     * @param
     * @return void
     *
     */
    public static void formatModuleAnnotation(String rootPath) {

        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            return;
        }
        ArrayList<File> files = Lists.newArrayList();
        getFiles(files, rootFile);
        for (File file : files) {
            if (file != null && file.getName().endsWith(SUFFIX_JAVA)) {
                FileInputStream fis = null;
                InputStreamReader isr = null;
                // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
                BufferedReader br = null;
                FileOutputStream fos = null;
                OutputStreamWriter osw = null;
                // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
                BufferedWriter bw = null;
                try {
                    String line = "";
                    StringBuffer annotation = new StringBuffer();
                    StringBuffer content = new StringBuffer();
                    fis = new FileInputStream(file);
                    // 从文件系统中的某个文件中获取字节
                    // InputStreamReader 是字节流通向字符流的桥梁,
                    isr = new InputStreamReader(fis);
                    // 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
                    br = new BufferedReader(isr);
                    boolean flag = Boolean.FALSE;
                    String tab = "";
                    while ((line = br.readLine()) != null) {
                        if (line.trim().startsWith(PUNCTUATION_SLASH_DOUBLE)) {
                            tab = line.substring(0, line.indexOf(PUNCTUATION_SLASH_DOUBLE));
                            if (!flag) {
                                line = line.replace(PUNCTUATION_SLASH_DOUBLE, "/**\r\n" + tab + " *");
                                annotation.append(line);
                                flag = Boolean.TRUE;
                            } else {
                                line = line.replace(PUNCTUATION_SLASH_DOUBLE, " * ");
                                annotation.append(line);
                                annotation.append(LINE_BREAK);
                            }
                        } else {
                            if (annotation.length() > 0) {
                                content.append(annotation);
                                annotation.delete(0, annotation.length());
                                content.append(LINE_BREAK);
                                content.append(tab);
                                content.append(" */");
                                content.append(LINE_BREAK);
                                flag = Boolean.FALSE;
                            }
                            if (line.indexOf(PUNCTUATION_SLASH_DOUBLE) > 0 && line.indexOf("\"//") < 0 && line.indexOf("\'//") < 0) {
                                String code = line.substring(0, line.indexOf(PUNCTUATION_SLASH_DOUBLE));
                                String anno = line.substring(line.indexOf(PUNCTUATION_SLASH_DOUBLE));
                                if (anno.trim().length() > 2) {
                                    tab = code.substring(0, code.length() - code.trim().length());
                                    content.append(tab);
                                    content.append("/**");
                                    content.append(LINE_BREAK);
                                    content.append(tab);
                                    content.append(anno.replace(PUNCTUATION_SLASH_DOUBLE, " * "));
                                    content.append(LINE_BREAK);
                                    content.append(tab);
                                    content.append(" */");
                                    content.append(LINE_BREAK);
                                }
                                line = code;
                            }
                            content.append(line);
                            content.append(LINE_BREAK);
                        }
                    }
                    fos = new FileOutputStream(file);
                    osw = new OutputStreamWriter(fos);
                    bw = new BufferedWriter(osw);
                    bw.write(content.toString());
                } catch (FileNotFoundException e) {
                    System.out.println("找不到指定文件");
                } catch (IOException e) {
                    System.out.println("读取文件失败");
                } finally {
                    closeAllIo(fis, isr, br, fos, osw, bw);

                }
            }
        }
    }

    /**
     * 为module中的类添加swagger 注解
     *
     * @author wangyibin
     * @date 2018/11/7 14:19
     * @param clazz 需要添加注解的类
     * @param isPO 是否是po类
     * @return
     */
    public static void addSwaggerAnnotationForModule(Class clazz, boolean isPO) {
        if (clazz == null) {
            return;
        }
        String path = getPathByClazz(clazz);
        String fileName = getFileNameOfClass(clazz);
        addSwaggerAnnotationForModule(path, fileName, isPO);
    }

    public static String getFileNameOfClass(Class clazz) {
        if (clazz == null) {
            return null;
        }
        String name = clazz.getName();
        return name.substring(name.lastIndexOf(".") + 1) + ".java";
    }

    /**
     * 为module中的类添加swagger 注解
     *
     * @author wangyibin
     * @date 2018/11/7 14:19
     * @param rootPath 文件所在根路径
     * @param fileName 文件名
     * @param isPO 是否是po类
     * @return
     */
    public static void addSwaggerAnnotationForModule(String rootPath, String fileName, boolean isPO) {
        if (rootPath == null && fileName == null) {
            return;
        }
        ArrayList<File> files = Lists.newArrayList();
        if (rootPath != null) {
            File rootFile = new File(rootPath);
            if (rootFile.exists()) {
                getFiles(files, rootFile);
            }
        }
        for (File file : files) {
            if (file == null) {
                continue;
            }
            if (fileName != null && !file.getName().equals(fileName)) {
                continue;
            }
            FileInputStream fis = null;
            InputStreamReader isr = null;
            // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
            BufferedReader br = null;
            FileOutputStream fos = null;
            OutputStreamWriter osw = null;
            BufferedWriter bw = null;
            try {
                String line = null;
                StringBuffer annotation = new StringBuffer();
                StringBuffer content = new StringBuffer();
                fis = new FileInputStream(file);
                // 从文件系统中的某个文件中获取字节
                // InputStreamReader 是字节流通向字符流的桥梁,
                isr = new InputStreamReader(fis);
                // 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
                br = new BufferedReader(isr);
                while ((line = br.readLine()) != null) {
                    if (line.trim().startsWith("/**")) {
                        StringBuffer amp = new StringBuffer("@ApiModelProperty(value = \"");
                        if (line.trim().indexOf("*/") > 5) {
                            String val = line.substring(line.indexOf("/**") + 3, line.indexOf("*/"));
                            amp.append(val.trim());
                            amp.append("\",dataType = \"");
                            String nextLine = getNextLine(br, content, isPO);
                            boolean flag = Boolean.TRUE;
                            flag = beforeIsAnno(content, amp, nextLine, flag);
                            if (flag) {
                                content.append(line);
                                content.append(LINE_BREAK);
                            }
                            content.append(nextLine);
                            content.append(LINE_BREAK);
                        } else if (line.trim().equals("/**")) {
                            String annoc = getNextLine(br, content, isPO);
                            String annoe = getNextLine(br, content, isPO);
                            String nextLine = getNextLine(br, content, isPO);
                            amp.append(annoc.trim().substring(1).trim());
                            amp.append("\",dataType = \"");
                            boolean flag = Boolean.TRUE;
                            if (annoe.trim().equals("*/")) {
                                flag = beforeIsAnno(content, amp, nextLine, flag);
                            }
                            if (flag) {
                                content.append(line);
                                content.append(LINE_BREAK);
                                content.append(annoc);
                                content.append(LINE_BREAK);
                                content.append(annoe);
                                content.append(LINE_BREAK);
                            }
                            content.append(nextLine);
                            content.append(LINE_BREAK);
                        }
                    } else if (line.trim().startsWith(PUNCTUATION_SLASH_DOUBLE)) {
                        StringBuffer amp = new StringBuffer("@ApiModelProperty(value = \"");
                        amp.append(line.substring(3).trim());
                        amp.append("\",dataType = \"");
                        String nextLine = getNextLine(br, content, isPO);
                        boolean flag = Boolean.TRUE;
                        flag = beforeIsAnno(content, amp, nextLine, flag);
                        if (flag) {
                            content.append(line);
                            content.append(LINE_BREAK);
                        }
                        content.append(nextLine);
                        content.append(LINE_BREAK);

                    } else {
                        if (!isPO) {
                            if (line.trim().startsWith("@Column") || line.trim().startsWith("@Sequence") || line.trim().startsWith("@Id")) {
                                continue;
                            }
                        }
                        if (line.trim().startsWith("public class")) {
                            String trim = line.trim();
                            String aClass = trim.substring(trim.indexOf("class") + 5).trim();
                            String className = aClass.substring(0, aClass.indexOf(" "));
                            className = className.substring(0, 1).toUpperCase() + className.substring(1);
                            if (className.length() > 2) {
                                content.append("@ApiModel(value = \"");
                                content.append(className);
                                content.append("\",description = \"\")");
                                content.append(LINE_BREAK);
                            }
                        }
                        content.append(line);
                        content.append(LINE_BREAK);
                    }
                }
                fos = new FileOutputStream(file);
                osw = new OutputStreamWriter(fos);
                bw = new BufferedWriter(osw);
                bw.write(content.toString());
            } catch (FileNotFoundException e) {
                System.out.println("找不到指定文件");
            } catch (IOException e) {
                System.out.println("读取文件失败");
            } finally {
                closeAllIo(fis, isr, br, fos, osw, bw);
            }

        }
    }

    /**
     * beforeIsAnno : 判断前面是否是注解
     *
     * @author wangyibin
     * @date 2019/1/7 15:02
     * @param content
     * @param amp
     * @param nextLine
     * @param flag
     * @return boolean
     *
     */
    public static boolean beforeIsAnno(StringBuffer content, StringBuffer amp, String nextLine, boolean flag) {
        String tab;
        if (nextLine.trim().startsWith("private ")) {
            String[] split = nextLine.trim().split(" ");
            if (split.length > 2) {
                tab = nextLine.substring(0, nextLine.indexOf("private"));
                amp.append(split[1]);
                amp.append("\")");
                content.append(tab);
                content.append(amp);
                content.append(LINE_BREAK);
                flag = Boolean.FALSE;
            }
        }
        return flag;
    }

    /**
     * closeAllIo : 关闭IO流
     *
     * @author wangyibin
     * @date 2019/1/7 15:18
     * @param fis
     * @param isr
     * @param br
     * @param fos
     * @param osw
     * @param bw
     * @return void
     *
     */
    public static void closeAllIo(FileInputStream fis, InputStreamReader isr, BufferedReader br, FileOutputStream fos,
            OutputStreamWriter osw, BufferedWriter bw) {
        try {
            // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (bw != null) {
                bw.close();
            }
            if (osw != null) {
                osw.close();
            }
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNextLine(BufferedReader br, StringBuffer content, boolean isPO) throws IOException {
        String line = null;
        while ((line = br.readLine()) != null) {
            if (!isPO) {
                if (line.trim().startsWith("@Column") || line.trim().startsWith("@Sequence") || line.trim().startsWith("@Id")) {
                    continue;
                }
            }
            if (line.trim().startsWith("@")) {
                content.append(line);
                content.append(LINE_BREAK);
                continue;
            }
            if (line.trim().length() > 0) {
                return line;
            }
        }
        return line;
    }

    public static void getFiles(ArrayList<File> files, File path) {
        if (path == null || files == null) {
            return;
        }
        if (path.isFile()) {
            files.add(path);
        } else {
            File[] tempList = path.listFiles();
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    files.add(tempList[i]);
                }
                if (tempList[i].isDirectory()) {
                    getFiles(files, tempList[i]);
                }
            }
        }
    }

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
