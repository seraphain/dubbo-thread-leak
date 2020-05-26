package com.github.seraphain.demo.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.Constants;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class Server {

    private static ServiceConfig<TestEndpoint> service = null;

    public static void main(String[] args) {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setThreads(250);
        protocolConfig.setHeartbeat(10000);
        protocolConfig.setPort(10101);
        protocolConfig.setDispatcher("connection");

        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setIothreads(6);
        providerConfig.setThreads(250);
        providerConfig.setDynamic(true);
        providerConfig.setHost("127.0.0.1");
        providerConfig.setPayload(208388608);
        providerConfig.setSerialization("java");
        
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(Constants.ZOOKEEPER_PROTOCOL);
        registry.setAddress("127.0.0.1:2181");
        registry.setTimeout(600000);
        
        service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("test"));
        service.setProtocol(protocolConfig);
        service.setProvider(providerConfig);
        service.setRegistry(registry);
        service.setSerialization("java");
        service.setConnections(500);
        service.setActives(60);
        service.setTimeout(10000);
        service.setDynamic(true);
        service.setInterface(TestEndpoint.class);
        service.setRef(new DubboTestEndpoint());

        service.export();
        
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        service.unexport();
    }

}
