package com.github.seraphain.demo.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.Constants;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class Client {

    public static void main(String[] args) {

        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(Constants.ZOOKEEPER_PROTOCOL);
        registry.setAddress("127.0.0.1:2181");
        registry.setTimeout(600000);
        registry.setDynamic(true);

        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setRegistry(registry);

        ReferenceConfig<TestEndpoint> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("test"));
        reference.setInterface(TestEndpoint.class);
        reference.setConsumer(consumerConfig);
        reference.setRegistry(registry);
        reference.setScope("remote");
        reference.setConnections(50);

        TestEndpoint endpoint = reference.get();

        System.out.println(endpoint.test("aaa"));

        try {
            Thread.sleep(3600000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
