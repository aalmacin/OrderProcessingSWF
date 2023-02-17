package com.raidrin.orderprocessing;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    @Value("${aws.region}")
    private String regionName;

    public String domain = "OrderProcessingDomain";

    public String taskList = "OrderProcessingTaskList";

    @Bean
    public AmazonSimpleWorkflow swfClient() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonSimpleWorkflowClient swf = new AmazonSimpleWorkflowClient(awsCreds);
        Region region = Region.getRegion(Regions.fromName(regionName));
        swf.setRegion(region);
        return swf;
    }

}
