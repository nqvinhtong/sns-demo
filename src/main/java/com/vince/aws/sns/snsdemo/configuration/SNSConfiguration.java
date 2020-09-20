package com.vince.aws.sns.snsdemo.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SNSConfiguration {

    private static final String aws_access_key_id = "ABC_IS_XYZ_ID";
    private static final String aws_secret_access_key = "ABC_IS_XYZ_KEY";

    @Primary
    @Bean
    public AmazonSNSClient getSnsClient() {
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard()
                .withRegion(Regions.AP_SOUTHEAST_1)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(aws_access_key_id, aws_secret_access_key)))
                .build();
    }
}
