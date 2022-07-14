package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/get-api") // 내부에 선언되는 메소드에서 사용할 공통 URL을 설정
public class GetController {
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    // http://localhost:8080/api/v1/get-api/hello
    // RequestMapping을 별다른 설정없이 선언하면 HTTP 의 모든 요청을 받는 다는 의미 -> Get 요청만 받기 위해서는 method 지정이 필요
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello(){
        LOGGER.info("getHello 메소드가 호출되었습니다.");
        return "Hello World!";
    }

    // http://localhost:8080/api/v1/get-api/name
    @GetMapping(value = "/name")
    public String getName() {
        LOGGER.info("getName 메소드가 호출되었습니다.");
        return "Flature";
    }

    // http://localhost:8080/api/v1/get-api/variable1/{string 값}
    // PathVariable : 매개변수를 받아 URL 자체레 값을 담아 요청하는 것 -> GetMapping 어노테이션과 PathVariable에 지정된 변수의 이름이 동일해야 함
    // {} 중괄호로 표시된 윛의 값을 받아 오는 요청
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable ("variable") String var){
        return var;
    }

    // http://localhost:8080/api/v1/get-api/request1?name=value1&email=value2&organization=value3
    // URL 경로에 값을 담아 요청을 보내는 방법이 아니라 쿼리 형식으로 값을 전달하는 방법 = URI 기준
    /*@GetMapping(value = "/request1")
    public String getRequestParam1 (
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }*/

    // 쿼리 스트링에 어떤 값이 들어오는지 모른다면 Map 객체를 활용
    @GetMapping(value = "/request2")
    public String getRequestParam2 (@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping(value="/request3")
    public String getRequestParam3 (MemberDto memberDto) {
       return memberDto.toString();
    }

    // 예제5.23 기존 코드에 Swagger 명세 추가하기
    @ApiOperation(value = "GET 메소드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getRequestParam1 (
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization)
    {
        return name + " " + email + " " + organization;
    }

}
