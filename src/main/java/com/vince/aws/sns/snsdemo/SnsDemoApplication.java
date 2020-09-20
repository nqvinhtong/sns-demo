package com.vince.aws.sns.snsdemo;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication (exclude = {ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class})
@RestController
public class SnsDemoApplication {

    @Autowired
    private AmazonSNSClient amazonSNSClient;
    private String TOPIC_ARN = "arn:aws:sns:ap-southeast-1:872767853650:";
    private String TOPIC_NAME = "poc-sns-test";

    @GetMapping("/addSubcription/{email}")
    public String addSubcription(@PathVariable String email) {
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN + TOPIC_NAME, "email", email);
        amazonSNSClient.subscribe(request);
        return "Subscription request is pending. To confirm the subscription, check your email: " +email;
    }

    @GetMapping("/sendNotification")
    public String publishMessageToTopic() {
        amazonSNSClient.createTopic(TOPIC_NAME);
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN + TOPIC_NAME, buildEmailBody(), "Notification: Salary anouncement");
        amazonSNSClient.publish(publishRequest);
        return "Notification success !!!";
    }

    private String buildEmailBody() {
        return "Dear Vince, \n" +
                "\n" +
                "\n" +
                "You got the salary this month. Please spend your time to buy a new macbook pro 2022";
    }

    public static void main(String[] args) {
        SpringApplication.run(SnsDemoApplication.class, args);
    }

}
