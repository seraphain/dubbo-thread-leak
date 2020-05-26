package com.github.seraphain.demo.dubbo;

public class DubboTestEndpoint implements TestEndpoint {

    @Override
    public String test(String test) {
        return "Hello " + test;
    }

}
