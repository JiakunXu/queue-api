package com.example.queue.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author JiakunXu
 */
public class HttpUtil {

    private static final int STATUS_CODE_200 = 200;

    private static final int STATUS_CODE_300 = 300;

    /**
     * @param uri
     * @return
     * @throws Exception
     */
    public static String get(String uri) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpGet httpget = new HttpGet(uri);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException,
                        IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= STATUS_CODE_200 && status < STATUS_CODE_300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            return httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
    }

    /**
     * @param uri
     * @param parameter
     * @return
     * @throws Exception
     */
    public static String post(String uri, Map<String, String> parameter) throws Exception {
        return post(uri, parameter, null);
    }

    /**
     * @param uri
     * @param parameter
     * @param header
     * @return
     * @throws Exception
     */
    public static String post(String uri, Map<String, String> parameter,
                              Map<String, String> header) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            List<NameValuePair> parameters = getParameters(parameter);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, Consts.UTF_8);

            HttpPost httppost = new HttpPost(uri);
            httppost.setEntity(entity);
            httppost.setHeaders(getHeaders(header));

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException,
                        IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= STATUS_CODE_200 && status < STATUS_CODE_300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            return httpclient.execute(httppost, responseHandler);
        } finally {
            httpclient.close();
        }
    }

    /**
     * @param uri
     * @param parameter
     * @return
     * @throws Exception
     */
    public static String post(String uri, String parameter) throws Exception {
        return post(uri, parameter, null);
    }

    /**
     * @param uri
     * @param parameter
     * @return
     * @throws Exception
     */
    public static String post(String uri, String parameter,
                              Map<String, String> header) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            StringEntity entity = new StringEntity(parameter, ContentType.APPLICATION_JSON);

            HttpPost httppost = new HttpPost(uri);
            httppost.setEntity(entity);
            httppost.setHeaders(getHeaders(header));

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException,
                        IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= STATUS_CODE_200 && status < STATUS_CODE_300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            return httpclient.execute(httppost, responseHandler);
        } finally {
            httpclient.close();
        }
    }

    /**
     * @param uri
     * @param parameter0
     * @param parameter1
     * @return
     * @throws Exception
     */
    public static String post(String uri, Map<String, String> parameter0,
                              Map<String, InputStream> parameter1,
                              Map<String, String> header) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            for (Map.Entry<String, String> map : parameter0.entrySet()) {
                multipartEntityBuilder.addTextBody(map.getKey(), map.getValue(),
                        ContentType.APPLICATION_FORM_URLENCODED);
            }

            for (Map.Entry<String, InputStream> map : parameter1.entrySet()) {
                multipartEntityBuilder.addBinaryBody(map.getKey(), map.getValue(),
                        ContentType.MULTIPART_FORM_DATA, "");
            }

            HttpPost httppost = new HttpPost(uri);
            httppost.setEntity(multipartEntityBuilder.build());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException,
                        IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= STATUS_CODE_200 && status < STATUS_CODE_300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            return httpclient.execute(httppost, responseHandler);
        } finally {
            httpclient.close();
        }
    }

    /**
     * @param uri
     * @return
     * @throws Exception
     */
    public static byte[] download(String uri) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpGet httpget = new HttpGet(uri);

            // Create a custom response handler
            ResponseHandler<byte[]> responseHandler = new ResponseHandler<byte[]>() {

                @Override
                public byte[] handleResponse(final HttpResponse response) throws ClientProtocolException,
                        IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= STATUS_CODE_200 && status < STATUS_CODE_300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toByteArray(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            return httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
    }

    public static byte[] download(String uri, String parameter) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            StringEntity entity = new StringEntity(parameter, ContentType.APPLICATION_JSON);

            HttpPost httppost = new HttpPost(uri);
            httppost.setEntity(entity);

            // Create a custom response handler
            ResponseHandler<byte[]> responseHandler = new ResponseHandler<byte[]>() {

                @Override
                public byte[] handleResponse(final HttpResponse response) throws ClientProtocolException,
                        IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= STATUS_CODE_200 && status < STATUS_CODE_300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toByteArray(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            return httpclient.execute(httppost, responseHandler);
        } finally {
            httpclient.close();
        }
    }

    /**
     * 将传入的键/值对参数转换为NameValuePair参数集.
     *
     * @param parameter 参数集, 键/值对
     * @return NameValuePair参数集
     */
    private static List<NameValuePair> getParameters(Map<String, String> parameter) {
        if (parameter == null || parameter.size() == 0) {
            return null;
        }

        List<NameValuePair> parameters = new ArrayList<>();

        for (Map.Entry<String, String> map : parameter.entrySet()) {
            parameters.add(new BasicNameValuePair(map.getKey(), map.getValue()));
        }

        return parameters;
    }

    private static Header[] getHeaders(Map<String, String> header) {
        if (header == null || header.size() == 0) {
            return null;
        }

        Header[] headers = new Header[header.size()];
        int i = 0;

        for (Map.Entry<String, String> map : header.entrySet()) {
            headers[i++] = new BasicHeader(map.getKey(), map.getValue());
        }

        return headers;
    }

}
