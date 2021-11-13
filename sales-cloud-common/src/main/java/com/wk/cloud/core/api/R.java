package com.wk.cloud.core.api;

import java.util.HashMap;

public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = -8157613083634272196L;

	private final static int ERROR_CODE = 3;

	public R() {
		put("code", 0);
		put("message", "success");
	}

	public R(APIStatus status) {
		put("code", status.getCode());
		put("message", status.getMessage());
	}

	public static R error() {
		return error(ERROR_CODE, "未知异常，请联系管理员");
	}

	public static R error(String message) {

		return error(ERROR_CODE, message);
	}

	public static R error(int code, String message) {
		R r = new R();
		r.put("code", code);
		r.put("message", message);
		return r;
	}

	public static R ok(Object data) {
		R r = new R();
		r.put("data", data);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
