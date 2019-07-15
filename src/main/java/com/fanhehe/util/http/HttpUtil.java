package com.fanhehe.util.http;

import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import com.google.gson.Gson;
import javax.annotation.Resource;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import com.fanhehe.util.result.IResult;
import com.google.gson.reflect.TypeToken;
import com.fanhehe.util.result.InvokeResult;
import org.apache.http.client.fluent.Request;


@Resource(name = "com.fanhehe.util.http.HttpUtil")
public abstract class HttpUtil<T> implements Endpoint, IHttpUtil<T> {

    private static final String GET = "GET";
    private static final String POST = "POST";

    private static final Gson gson = new Gson();

    private static final String scheme = "http://";
    private static final int socketTimeout = 2000;
    private static final int connectTimeout = 2000;

    private HttpVersion httpVersion = HttpVersion.HTTP_1_1;

    public abstract String getEndpoint();

    @Override
    public IResult<T> call(String path, Map<String, String> params, Map<String, String> headers, String method) {

        Request request;
        String uri = String.join("", scheme, getEndpoint(), path);

        switch (method) {
            case GET:
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
                return null;
        }

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
            return InvokeResult.failure("");
        }

        return gson.fromJson(content, new TypeToken<IResult<T>>(){}.getType());
    }
}