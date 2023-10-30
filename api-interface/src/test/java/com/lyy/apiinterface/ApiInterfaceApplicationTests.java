package com.lyy.apiinterface;

import com.lyy.apiclientsdk.client.NameApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Resource
    private NameApiClient heartApiClient;

    @Test
    void contextLoads() {
//        Map<String,Object> map1 = new HashMap<>();
//        map1.put("name","lyy");
//        System.out.println(heartApiClient.getRequest("http://localhost:8090/api/name/get",map1));
//
//        map1.put("name","xiaoLian");
//        System.out.println(heartApiClient.postRequest("http://localhost:8090/api/name/post",map1));

//        User user = new User();
//        user.setName("sdk-starter");
//        System.out.println(heartApiClient.restfulRequest("http://localhost:8090/api/name/user",user));


//        map1.put("schoolName","大仲恺");
//        System.out.println(heartApiClient.postRequest("http://localhost:8090/api/school/", map1));
    }

}
