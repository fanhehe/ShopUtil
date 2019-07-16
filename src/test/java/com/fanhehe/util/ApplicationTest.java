package com.fanhehe.util;

import org.junit.Test;
import org.junit.Assert;

import java.util.Map;
import java.util.HashMap;
import com.fanhehe.util.http.HttpUtil;
import com.fanhehe.util.result.IResult;


public class ApplicationTest {

    @Test
    public void evaluatesExpression() {
        Assert.assertEquals(6, 2 * 3);
    }

    @Test
    public void evaluatesExpression2() {
    	ApiService service = new ApiService();

        IResult<Code> result = null;

    	try {
             result = service.sendEmailCaptcha("13889441200@163.com", "test", String.valueOf(Math.random()));
        } catch (Exception e) {
    	    e.printStackTrace();
        }

        if (result == null) {
    	    return;
        }

    	Assert.assertEquals(result.isSuccess(), true);

    	if (result.getData() != null) {
    		System.out.println(result.getData().toString());
    	} else {
    		System.out.println("=============================");
    	}
    }

}

class Code {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public int getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    private int id;
    private int uid;
    private String app;
    private String orderId;
    private String target;
    private int overtime;
    private String code;
    private int status;
    private int createdAt;
    private int updatedAt;
}

class ApiService extends HttpUtil<Code> {
	public String getEndpoint() {
		return "127.0.0.1:10015";
	}

	IResult<Code> sendEmailCaptcha(String email, String app, String orderId) {

        Map<String, String> params = new HashMap<>();

        params.put("app", app);
        params.put("email", email);
        params.put("orderId", orderId);

        return this.get("/api/message/captcha/email/send", params);
    }
}