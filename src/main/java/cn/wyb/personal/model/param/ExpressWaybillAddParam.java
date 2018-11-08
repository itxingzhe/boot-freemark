package cn.wyb.personal.model.param;

import cn.wyb.personal.model.vo.express.ExpressSendAndReceiveInformationVO;
import io.swagger.annotations.ApiModel;

/**
 * ExpressWaybillAddParam: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/10/11 13:59
 * @see
 */
@ApiModel(value = "eee", description = "")
public class ExpressWaybillAddParam {

    /**
     * 否 DB83CDE6E35CEB298B47716DF3048991
     * 电子面单客户账户或月结账号，需向快递公司在贵司当地的网点申请；若已和快递100超市业务合作，则可不填。
     * 顺丰、EMS的可输入月结账号；若所选快递公司为宅急送（即kuaidicom字段为zhaijisong），则此项可不填。
     */
    private String                             partnerId;

    /**
     * 否 EpLzwptJ8922 电子面单密码，需向快递公司在贵司当地的网点申请；若已和快递100超市业务合作，则可不填。
     * 顺丰、EMS的如果partnerId填月结账号，则此字段不填；若所选快递公司为宅急送（即kuaidicom字段为zhaijisong），则此项可不填。
     */
    private String                             partnerKey;

    /**
     * 否 EpLzwptJ8922
     * 收件网点名称,由快递公司当地网点分配，若已和快递100超市业务合作，则可不填。顺丰、EMS的如果partnerId填月结账号，则此字段不填；若所选快递公司为宅急送（即kuaidicom字段为zhaijisong），则此项可不填。
     */
    private String                             net;

    /**
     * 是 ems 快递公司的编码，一律用小写字母，见《快递公司编码》
     */
    private String                             kuaidicom;

    /**
     * 否 881443775034378914 快递单号，单号的最大长度是32个字符
     */
    private String                             kuaidinum;

    /**
     * 否 881443775034378914 贵司内部自定义的订单编号,需要保证唯一性
     */
    private String                             orderId;

    /**
     * 是 收件人地址信息
     */
    private ExpressSendAndReceiveInformationVO recMan;

    /**
     * 是 寄件人地址信息
     */
    private ExpressSendAndReceiveInformationVO sendMan;

    /**
     * 否 发票 物品名称
     */
    private String                             cargo;

    /**
     * 是 1 物品总数量；如果需要子单（指同一个订单打印出多张电子面单，即同一个订单返回多个面单号），needChild = 1、count
     * 需要大于1，如count = 2 则一个主单 一个子单，count = 3则一个主单 二个子单；返回的子单号码见返回结果的childNum字段
     */
    private Integer                            count;

    /**
     * 是 0.5 物品总重量KG
     */
    private double                             weight;

    /**
     * 否 0.1 物品总体积：CM*CM*CM
     */
    private double                             volumn;

    /**
     * 否 SHIPPER 支付方式：SHIPPER:寄方付（默认）、CONSIGNEE:到付、MONTHLY:月结、THIRDPARTY:第三方支付
     */
    private String                             payType;

    /**
     * 否 标准快递 快递类型:标准快递（默认）、顺丰特惠、EMS经济
     */
    private String                             expType;

    /**
     * 否 发票 备注
     */
    private String                             remark;

    /**
     * 否 0 保价额度
     */
    private double                             valinsPay;

    /**
     * 否 0 代收货款额度
     */
    private double                             collection;

    /**
     * 否 0 是否需要子单：1:需要、0:不需要(默认)。如果需要子单（指同一个订单打印出多张电子面单，即同一个订单返回多个面单号），needChild =
     * 1、count 需要大于1，如count = 2 一个主单 一个子单，count = 3 一个主单
     * 二个子单，返回的子单号码见返回结果的childNum字段
     */
    private String                             needChild;

    /**
     * 否 0 是否需要回单：1:需要、 0:不需要(默认)。返回的回单号见返回结果的returnNum字段
     */
    private String                             needBack;

    /**
     * 否 0 是否需要打印模板：1:需要、 0不需要(默认)。如果需要，则返回要打印的模版的HTML代码，
     * 贵司可以直接将之显示到IE等浏览器，然后通过浏览器进行打印
     */
    private String                             needTemplate;

    /**
     * key String 是 kytRsteof 我方分配给贵司的的授权key，见授权key邮件说明 t Long 是 1470304729724 请求时间戳
     */

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getKuaidicom() {
        return kuaidicom;
    }

    public void setKuaidicom(String kuaidicom) {
        this.kuaidicom = kuaidicom;
    }

    public String getKuaidinum() {
        return kuaidinum;
    }

    public void setKuaidinum(String kuaidinum) {
        this.kuaidinum = kuaidinum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ExpressSendAndReceiveInformationVO getRecMan() {
        return recMan;
    }

    public void setRecMan(ExpressSendAndReceiveInformationVO recMan) {
        this.recMan = recMan;
    }

    public ExpressSendAndReceiveInformationVO getSendMan() {
        return sendMan;
    }

    public void setSendMan(ExpressSendAndReceiveInformationVO sendMan) {
        this.sendMan = sendMan;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolumn() {
        return volumn;
    }

    public void setVolumn(double volumn) {
        this.volumn = volumn;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getValinsPay() {
        return valinsPay;
    }

    public void setValinsPay(double valinsPay) {
        this.valinsPay = valinsPay;
    }

    public double getCollection() {
        return collection;
    }

    public void setCollection(double collection) {
        this.collection = collection;
    }

    public String getNeedChild() {
        return needChild;
    }

    public void setNeedChild(String needChild) {
        this.needChild = needChild;
    }

    public String getNeedBack() {
        return needBack;
    }

    public void setNeedBack(String needBack) {
        this.needBack = needBack;
    }

    public String getNeedTemplate() {
        return needTemplate;
    }

    public void setNeedTemplate(String needTemplate) {
        this.needTemplate = needTemplate;
    }
}
