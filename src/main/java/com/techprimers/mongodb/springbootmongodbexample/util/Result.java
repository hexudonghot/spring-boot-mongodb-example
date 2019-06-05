package com.techprimers.mongodb.springbootmongodbexample.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author wangjunfeng
 * @date 2016年10月27日
 */
public class Result<T> extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 0);
        put("msg", "success");
    }

    public static Result error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result ok() {
        return new Result();
    }
    
    public static <T>Result ok(T t){
    	Result<T> result = new Result<T>();
    	result.put("data", t);
		return result;
	}

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
