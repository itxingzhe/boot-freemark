package cn.wyb.model.vo;

public class CommResponse {

    private String name;
    private String message;
    private Object data;

    public static CommResponse success(Object data){
        CommResponse cr = new CommResponse();
        cr.setMessage("恭喜您，操作成功！");
        return cr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
