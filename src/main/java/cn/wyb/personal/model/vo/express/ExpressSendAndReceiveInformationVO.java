package cn.wyb.personal.model.vo.express;

/**
 * ExpressSendAndReceiveInformationVO: 快递收发件信息
 *
 * @author wangyibin
 * @date 2018/10/11 14:16
 * @see
 */
public class ExpressSendAndReceiveInformationVO {

    /**
     * 是 张三 收件人姓名
     */
    private String name;

    /**
     * 否 13898896666 收件人的手机号，手机号和电话号二者其一必填
     */
    private String mobile;

    /**
     * 否 0755-86689999 收件人的电话号，手机号和电话号二者其一必填
     */
    private String tel;

    /**
     * 否 518000 收件人所在地邮编
     */
    private String zipCode;

    /**
     * 否 广东省 收件人所在省份
     */
    private String province;

    /**
     * 否 深圳市 收件人所在市
     */
    private String city;

    /**
     * 否 南山区 收件人所在区
     */
    private String district;

    /**
     * 否 科技南十二路2号金蝶软件园 收件人所在地址
     */
    private String addr;

    /**
     * 否 广东深圳市深圳市南山区科技南十二路2号金蝶软件园 收件人所在完整地址；province、city、distinct、addr 和 printAddr
     * 任选一个必填。如果有填写province，city，distinct，addr
     * 则系统优先读取这些信息；如果只填写printAddr，系统将自动识别对应的省、市与区
     */
    private String printAddr;

    /**
     * 否 快递100 收件人所在公司名称
     */
    private String company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPrintAddr() {
        return printAddr;
    }

    public void setPrintAddr(String printAddr) {
        this.printAddr = printAddr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
