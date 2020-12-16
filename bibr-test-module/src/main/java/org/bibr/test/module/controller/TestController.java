package org.bibr.test.module.controller;

import java.util.Map;

import org.bibr.test.module.bean.po.Users;
import org.bibr.test.module.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test/")
public class TestController {
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "vl/selectUser", method = RequestMethod.POST)
	@ResponseBody
	public Users selectUser(@RequestBody Map<String, Object> map) {
		logger.info("comming selectUser ......");
		Users users = this.testService.selectUser(map);
		return users;
	}
}
