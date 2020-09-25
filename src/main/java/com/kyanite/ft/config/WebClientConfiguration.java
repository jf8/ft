package com.kyanite.ft.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 2017/4/26.
 */
@Configuration
public class WebClientConfiguration {
    @Bean
    public PoolingHttpClientConnectionManager connectionManager(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =new PoolingHttpClientConnectionManager();
        //设置每个主机最大的并发数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(100);
        return poolingHttpClientConnectionManager;
    }

    @Autowired
    @Bean
    public HttpClientBuilder closeableHttpClient(PoolingHttpClientConnectionManager connectionManager , RequestConfig requestConfig){
        return  HttpClientBuilder.create().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig);
    }

    @Bean
    public CloseableHttpClient httpClient(HttpClientBuilder builder){
        return builder.build();
    }

    @Bean
    public RequestConfig requestConfig(){
        return RequestConfig.custom()
            //设置创建连接的最长时间
            .setConnectTimeout(30000)
            //从连接池中获取到连接的最长时间
//            .setConnectionRequestTimeout(500)
            //数据传输的最长时间
            .setSocketTimeout(120000) //120秒
            .build();
    }

    @Bean
    public IdleConnectionEvictor idleConnectionEvictor(PoolingHttpClientConnectionManager connectionManager){
        return new IdleConnectionEvictor(connectionManager,1, TimeUnit.MINUTES);
    }
}
