package cn.wyb.personal.pdf;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

/**
 * MyFontsProvider: (请描述这个类).
 *
 * @author wangyibin
 * @date 2019/1/10 11:31
 * @see
 */
public class MyFontsProvider extends XMLWorkerFontProvider {

    public MyFontsProvider() {
        super(null, null);
    }

    @Override
    public Font getFont(final String fontname, String encoding, float size, final int style) {
        String fntnames = fontname;
        Font FontChinese = null;
        if (fntnames == null) {
            fntnames = "宋体";
        }
        if (size == 0) {
            size = 4;
        }
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            FontChinese = new Font(bfChinese, 12, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (FontChinese == null) {
            FontChinese = super.getFont(fntnames, encoding, size, style);
        }
        return FontChinese;
    }
}
