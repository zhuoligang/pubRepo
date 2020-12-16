package org.lg.pay.module.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.lg.pay.module.bean.po.Users;
import org.lg.pay.module.dao.UsersMapper;
import org.lg.pay.module.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
@ResponseBody
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private TestService testService;

    @ApiOperation(value = "test1————zlg", notes = "无")
    @PostMapping("/test1")
    public void test(
            @RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, Object> map) {
        Users user = this.usersMapper.selectByPrimaryKey(Integer.parseInt(map.get("id").toString()));
        System.out.println(JSONObject.toJSONString(user));
    }

    @PostMapping("/test2")
    public void test2(@RequestBody Map<String, String> map) {
        this.testService.tt(map);
    }

}
