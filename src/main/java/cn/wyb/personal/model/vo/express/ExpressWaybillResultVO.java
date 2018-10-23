package cn.wyb.personal.model.vo.express;

/**
 * ExpressWaybillResultVO: 电子面单响应数据
 *
 * @author wangyibin
 * @date 2018/10/11 15:54
 */
public class ExpressWaybillResultVO {

    /** 606568155331 快递单号 */
    private String   kuaidinum;

    /** 123456 回单号 */
    private String   returnNum;

    /**
     * 子单号，如果有多个，逗号分隔。 如果需要子单，则需要提交needChild = 1、count 需要大于1， 如count =
     * 2则一个主单、一个子单，count=3则一个主单、二个子单
     */
    private String   childNum;

    /** 大头笔，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   bulkpen;

    /** 始发地区域编码，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   orgCode;

    /** 始发地/始发网点名称，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   orgName;

    /** 目的地区域编码，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   destCode;

    /** 目的地/到达网点，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   destName;

    /** 始发分拣编码，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   orgSortingCode;

    /** 始发分拣名称，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   orgSortingName;

    /** 目的分栋编码，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   destSortingCode;

    /** 目的分栋中心名称，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   destSortingName;

    /** 始发其他信息，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   orgExtra;

    /** 目的其他信息，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   destExtra;

    /** 集包编码，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   pkgCode;

    /** 集包地名称，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   pkgName;

    /** 路区，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   road;

    /** 二维码，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   qrCode;

    /** 快递公司订单号，用于显示于电子面单上规定位置；是否有值取决于快递公司 */
    private String   orderNum;

    /**
     * 快递业务类型编码，用于显示于电子面单上规定位置
     */
    private String   expressCode;

    /** 快递业务类型名称，用于显示于电子面单上规定位置 */
    private String   expressName;

    /**
     * http://www.kuaidi100.com/XXX
     * 在线显示电子面单模版的网址，用浏览器打开该网址，就能看到生成的电子面单，直接通过浏览器的打印功能即可完成打印
     */
    private String   templateurl;

    /**
     * 电子面单显示html代码,只需要将之写着一个web页面并显示出来，即可在网页中显示出电子面单并通过浏览器实现面单打印
     */
    private String[] template;

    public String getKuaidinum() {
        return kuaidinum;
    }

    public void setKuaidinum(String kuaidinum) {
        this.kuaidinum = kuaidinum;
    }

    public String getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(String returnNum) {
        this.returnNum = returnNum;
    }

    public String getChildNum() {
        return childNum;
    }

    public void setChildNum(String childNum) {
        this.childNum = childNum;
    }

    public String getBulkpen() {
        return bulkpen;
    }

    public void setBulkpen(String bulkpen) {
        this.bulkpen = bulkpen;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDestCode() {
        return destCode;
    }

    public void setDestCode(String destCode) {
        this.destCode = destCode;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public String getOrgSortingCode() {
        return orgSortingCode;
    }

    public void setOrgSortingCode(String orgSortingCode) {
        this.orgSortingCode = orgSortingCode;
    }

    public String getOrgSortingName() {
        return orgSortingName;
    }

    public void setOrgSortingName(String orgSortingName) {
        this.orgSortingName = orgSortingName;
    }

    public String getDestSortingCode() {
        return destSortingCode;
    }

    public void setDestSortingCode(String destSortingCode) {
        this.destSortingCode = destSortingCode;
    }

    public String getDestSortingName() {
        return destSortingName;
    }

    public void setDestSortingName(String destSortingName) {
        this.destSortingName = destSortingName;
    }

    public String getOrgExtra() {
        return orgExtra;
    }

    public void setOrgExtra(String orgExtra) {
        this.orgExtra = orgExtra;
    }

    public String getDestExtra() {
        return destExtra;
    }

    public void setDestExtra(String destExtra) {
        this.destExtra = destExtra;
    }

    public String getPkgCode() {
        return pkgCode;
    }

    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getTemplateurl() {
        return templateurl;
    }

    public void setTemplateurl(String templateurl) {
        this.templateurl = templateurl;
    }

    public String[] getTemplate() {
        return template;
    }

    public void setTemplate(String[] template) {
        this.template = template;
    }
}
