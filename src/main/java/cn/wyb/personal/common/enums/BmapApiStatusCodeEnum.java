package cn.wyb.personal.common.enums;

public enum BmapApiStatusCodeEnum {

    OK(0, "正常"), // 服务请求正常召回

    PARAMETER_INVALID(2, "请求参数非法"), // 必要参数拼写错误或漏传（如query和tag请求中均未传入）

    VERIFY_FAILURE(3, "权限校验失败"),

    QUOTA_FAILURE(4, "配额校验失败"), // 服务当日调用次数已超限，请前往API控制台提升（请优先进行开发者认证）

    RESULT_NULL(112, "返回数据为空"),

    AK_FAILURE(5, "ak不存在或者非法"); // 未传入ak参数；ak已被删除（可前往回收站恢复）

    Integer code;
    String  message;

    BmapApiStatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BmapApiStatusCodeEnum getEnum(Integer code) {
        BmapApiStatusCodeEnum[] values = BmapApiStatusCodeEnum.values();
        for (BmapApiStatusCodeEnum e : BmapApiStatusCodeEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    public static String getMessage(Integer code) {
        BmapApiStatusCodeEnum e = getEnum(code);
        if (e != null) {
            return e.getMessage();
        }
        return null;
    }
}
