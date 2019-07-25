package com.fanhehe.util.http;

import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import javax.annotation.Resource;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import com.fanhehe.util.result.IResult;
import java.lang.reflect.ParameterizedType;
import com.fanhehe.util.result.InvokeResult;
import org.apache.http.client.fluent.Request;
import com.fanhehe.util.result.ParameterizedTypeImpl;

@Resource(name = "com.fanhehe.util.http.HttpUtil")
public abstract class HttpUtil<T> implements Endpoint, IHttpUtil<T> {

    private int socketTimeout = 60000;
    private int connectTimeout = 60000;
    private String scheme = "http://";
    private HttpVersion httpVersion = HttpVersion.HTTP_1_1;

    private static final Gson gson = new Gson();

    /**
     * 获取访问地址的endpoint(ip + port)
     * @return 返回值形如: 127.0.0.1:80
     */
    public abstract String getEndpoint();

    /**
     * 发出参数为HashMap请求的调用方法
     * @param path 请求路径 /api/...
     * @param params 请求参数 HashMap
     * @param headers 请求首部 HashMap
     * @param method 调用方式，仅支持 GET / POST
     * @return 返回调用的结果，形如IResult
     */
    @Override
    public IResult<T> call(String path, Map<String, String> params, Map<String, String> headers, String method) {

        Request request;
        String uri = String.join("", scheme, getEndpoint(), path);

        // 封装不同请求方法的差异性
        switch (method) {
            case GET:
                // 添加GET参数
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> item: params.entrySet()) {
                    sb = sb
                            .append(item.getKey())
                            .append('=')
                            .append(item.getValue())
                            .append('&');
                }
                sb.deleteCharAt(sb.length() - 1);
                uri = String.join("?", uri, sb.toString());
                request = Request.Get(uri);
                break;
            case POST:

                // 添加POST参数
                ArrayList<NameValuePair> entity = new ArrayList<>(params.size());

                for (Map.Entry<String, String> item: params.entrySet()) {
                    entity.add(new NameValuePair() {
                        @Override
                        public String getName() {
                            return item.getKey();
                        }

                        @Override
                        public String getValue() {
                            return item.getValue();
                        }
                    });
                }

                request = Request.Post(uri).bodyForm(entity);
                break;
            default:
                return InvokeResult.failure("不支持当前的HTTP请求方式", -1);
        }

        // 添加请求首部
        for (Map.Entry<String, String> item: headers.entrySet()) {
            request.addHeader(item.getKey(), item.getValue());
        }

        request
                .version(httpVersion)
                .socketTimeout(socketTimeout)
                .connectTimeout(connectTimeout);

        String content;

        try {
            content = request
                    .execute()
                    .returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
            return InvokeResult.failure(e.getMessage(), -2);
        }

        Type type = InvokeResult.class;
        Type tp = getClass().getGenericSuperclass();
        // reference https://juejin.im/entry/5b5e6bb7e51d45195312803a

        if (tp instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType)tp).getActualTypeArguments();
            type = new ParameterizedTypeImpl(InvokeResult.class, new Type[]{ types[0] });
        }

        return gson.fromJson(content, type);
    }
}
