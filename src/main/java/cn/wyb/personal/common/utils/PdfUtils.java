package cn.wyb.personal.common.utils;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Base64;

import javax.swing.*;

import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import cn.wyb.personal.pdf.MyFontsProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * PdfUtils: PDF生成工具类
 *
 * @author wangyibin
 * @date 2019/1/10 14:42
 * @see
 */
public class PdfUtils {

    public static void main(String[] args) {
        waterMark("E:/workspace/ideaworkspace/bootfreemark/target/classes/pdf/支付申请.pdf",
                "E:/workspace/ideaworkspace/bootfreemark/target/classes/pdf/支付申请-mark.pdf", "添加的水印", 0.8F, 20, 45, null);
    }

    public static String convertToPdf(String tempaltePath, String templateName, String pdfName, Object data) {
        Document document = null;
        try {
            String content = getContent(tempaltePath, templateName, data);
            String classpath = PdfUtils.class.getClassLoader().getResource("").getPath();
            String pdfPath = classpath.substring(0, classpath.length() - 1) + "/pdf/" + pdfName;
            FileOutputStream outputStream = new FileOutputStream(pdfPath);
            document = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            // 设置用户密码, 所有者密码,用户权限,所有者权限
            writer.setEncryption("userpassword".getBytes(), "ownerPassword".getBytes(), PdfWriter.ALLOW_COPY, PdfWriter.ENCRYPTION_AES_128);
            document.open();
            XMLWorkerFontProvider fontProvider = new MyFontsProvider();
            fontProvider.addFontSubstitute("lowagie", "garamond");
            fontProvider.setUseUnicode(true);
            CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            htmlContext.setImageProvider(new AbstractImageProvider() {
                @Override
                public com.itextpdf.text.Image retrieve(String src) {
                    int pos = src.indexOf("base64,");
                    try {
                        if (src.startsWith("data") && pos > 0) {
                            byte[] img = Base64.getDecoder().decode(src.substring(pos + 7));
                            return com.itextpdf.text.Image.getInstance(img);
                        } else if (src.startsWith("http")) {
                            return com.itextpdf.text.Image.getInstance(src);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return null;
                    }
                    return null;
                }

                @Override
                public String getImageRootPath() {
                    return null;
                }

            });
            CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
            XMLWorker worker = new XMLWorker(css, true);
            XMLParser p = new XMLParser(worker);
            p.parse(new StringReader(content));
            document.close();
            outputStream.flush();
            outputStream.close();
            return pdfPath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return null;
    }

    public static String getContent(String tempaltePath, String templateName, Object data) {
        try {

            Template template = getTemplate(tempaltePath, templateName);
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            return writer.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Template getTemplate(String tempaltePath, String templateName) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
        configuration.setDefaultEncoding(FileUtils.ENCODING_UTF8);
        try {
            configuration.setClassForTemplateLoading(FileUtils.class, tempaltePath);
            Template template = configuration.getTemplate(templateName);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * waterMark : 生成水印
     *
     * @author wangyibin
     * @date 2019/1/16 18:06
     * @param inputFile
     * @param outputFile
     * @param waterMarkName
     * @param opacity
     * @param fontSize
     * @param rotation
     * @param color
     * @return void
     * 
     */
    public static void waterMark(String inputFile, String outputFile, String waterMarkName, float opacity, int fontSize, int rotation,
            BaseColor color) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            // 设置强制读取，防止读取只读文件报错
            PdfReader.unethicalreading = Boolean.TRUE;
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
            stamper.setEncryption("userpassword".getBytes(), "ownerPassword".getBytes(), PdfWriter.ALLOW_PRINTING,
                    PdfWriter.ENCRYPTION_AES_128);
            // 设置字体为中文，防止中文乱码
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            // 设置水印透明度
            gs.setFillOpacity(opacity);
            // 设置水印的不透明度
            // gs.setStrokeOpacity(opacity);
            int total = reader.getNumberOfPages() + 1;
            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH = 0;
            int textW = 0;
            label.setText(waterMarkName);
            metrics = label.getFontMetrics(label.getFont());
            textH = metrics.getHeight();
            textW = metrics.stringWidth(label.getText());
            textH = textH * fontSize / 12;
            textW = textW * fontSize / 12;
            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getOverContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                // 设置水印字体字号
                under.setFontAndSize(base, fontSize);
                // 水印文字成**度角倾斜
                int inteval = 20 + fontSize;
                for (int height = inteval; height < pageRect.getHeight(); height = height + textH * 3) {
                    for (int width = inteval; width < pageRect.getWidth() + textW; width = width + textW * 2) {
                        under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, width, height, rotation);
                    }
                }
                // 设置水印颜色
                under.setColorFill(color == null ? BaseColor.GRAY : color);
                // 添加水印文字
                under.endText();
            }
            // 关闭流
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
