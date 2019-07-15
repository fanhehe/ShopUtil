package com.fanhehe.util.http;

import java.util.Map;
import java.util.HashMap;
import com.fanhehe.util.result.IResult;

public interface IHttpUtil<T> {

    String GET = "GET";
    String POST = "POST";

    IResult<T> call(String path, Map<String, String> params, Map<String, String> headers, String method);

    default IResult<T> get(String path) {
        return get(path, new HashMap<>(), new HashMap<>());
    }

    default IResult<T> get(String path, Map<String, String> params) {
        return get(path, params, new HashMap<>());
    }

    default IResult<T> post(String path, Map<String, String> params) {
        return post(path, params, new HashMap<>());
    }

    default IResult<T> get(String path, Map<String, String> params, Map<String, String> headers) {
        return call(path, params, headers, GET);
    }

    default IResult<T> post(String path, Map<String, String> params, Map<String, String> headers) {
        return call(path, params, headers, POST);
    }
}

