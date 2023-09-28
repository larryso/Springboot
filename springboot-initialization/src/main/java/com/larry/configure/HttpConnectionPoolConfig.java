package com.larry.configure;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConnectionPoolConfig {
    public static final int CONCURRENCY=20;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //set the maximum unmber of total open connections
        poolingHttpClientConnectionManager.setMaxTotal(CONCURRENCY);
        //set the maximum number of concurrent connections per route which is two by default
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(CONCURRENCY);
        HttpHost host = new HttpHost("reqres.in", 8080, "https");
        //set the total number of concurrent connections to a specific route, which is two by default
        poolingHttpClientConnectionManager.setMaxPerRoute(new HttpRoute(host), CONCURRENCY);
        return poolingHttpClientConnectionManager;
    }
    @Bean
    public RequestConfig requestConfig(){
        RequestConfig requestConfig = RequestConfig.
                custom().
                setConnectionRequestTimeout(5000).
                setConnectTimeout(5000).
                setSocketTimeout(5000).
                build();
        return requestConfig;
    }
    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager, RequestConfig requestConfig){
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.
                create().
                setConnectionManager(poolingHttpClientConnectionManager).
                setDefaultRequestConfig(requestConfig).
                build();
        return closeableHttpClient;
    }
    @Bean
    public RestTemplate restTemplate(HttpClient httpClient){
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
