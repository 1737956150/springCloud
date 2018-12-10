package com.zzg.controller;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @classname testController
 * @desc TODO
 * @author: zzg
 * @date: 2018/12/7 16:29
 */
@RestController
@Slf4j
public class testController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test(){
        //第一种方式 直接用 RestTemplate 写死要调用服务的url
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://192.168.50.25:8001/hello/word", String.class);
        //第二种方式 利用LoadBalancerClient通过应用名称获取url 然后在使用 RestTemplate 调用
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance choose = loadBalancerClient.choose("eureka-client-1");
        String url = String.format("http://%s:%s", choose.getHost(), choose.getPort())+"/hello/word";
        String response = restTemplate.getForObject(url, String.class);

        // 第三种 创建一个config注入进来
//        String response = restTemplate.getForObject("http://eureka-client-1/hello/word", String.class);
//        log.info("response={}",response);
        return response;
    }
}
