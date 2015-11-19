package org.openbank;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    private static final String DOCUMENT_ID = "testId";
    private static final String DOCUMENT_ENDPOINT_URI = "http://localhost:8080/document?id=" + DOCUMENT_ID;
    private static final String CONFIRM_ENDPOINT_URI = "http://localhost:8080/confirm";

    public static void main(String[] args) throws Exception {
        // Create connection objects
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI getDocumentEndpointUri = new URI(DOCUMENT_ENDPOINT_URI);
        URI confirmEndpointUri = new URI(CONFIRM_ENDPOINT_URI);

        // Make the connection for get document endpoint
        HttpResponse getDocumentResponse = httpClient.execute(new HttpGet(getDocumentEndpointUri));

        // Print the results of the document endpoint
        System.out.println(IOUtils.toString(getDocumentResponse.getEntity().getContent()));

        // Make the connection for confirm endpoint
        List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
        parameterList.add(new BasicNameValuePair("confirmation_text", "caca"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameterList, Consts.UTF_8);
        HttpPost httpPost = new HttpPost(confirmEndpointUri);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setEntity(urlEncodedFormEntity);
        HttpResponse confirmResponse = httpClient.execute(httpPost);

        // Print the results of the confirm endpoint
        System.out.println(IOUtils.toString(confirmResponse.getEntity().getContent()));
    }
}
