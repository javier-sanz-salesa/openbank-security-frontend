package org.openbank;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;

/**
 * Hello world!
 */
public class App {
    private static final String DOCUMENT_ID = "testId";
    private static final String DOCUMENT_ENDPOINT_URI = "http://localhost:8080/document?id=" + DOCUMENT_ID;

    public static void main(String[] args) throws Exception {
        // Create connection objects
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI endpointUri = new URI(DOCUMENT_ENDPOINT_URI);

        // Make the connection
        HttpResponse response = httpClient.execute(new HttpGet(endpointUri));

        // Print the results of the document endpoint
        System.out.println(IOUtils.toString(response.getEntity().getContent()));
    }
}
