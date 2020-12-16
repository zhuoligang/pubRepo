package com.lg.web.module.controller.front.verify;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lg.web.module.annotation.OperateNote;
import com.lg.web.module.annotation.VisitLimit;
import com.lg.web.module.bean.po.Users;
import com.lg.web.module.bean.vo.ResultVO;
import com.lg.web.module.constant.NoteTypeEnum;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.service.SecretService;
import com.lg.web.module.service.UsersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/verify/front/")
@Api(value = "云算力用户系列控制台")
public class FrontVerifyController {

	private final static Logger logger = LoggerFactory.getLogger(FrontVerifyController.class);

	@Autowired
	private UsersService usersService;

	Map<String, String> testMap = new HashMap<String, String>();

	@ApiOperation(value = "根据id查询用户————zlg", notes = "用户ID		id（必填）")
	@RequestMapping(value = "vl/selectUser", method = RequestMethod.POST)
	@OperateNote(noteType = NoteTypeEnum.LOGIN)
	@VisitLimit(sec = 5,limit = 1)
	@ResponseBody
	public ResultVO<Users> selectUser(
			@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, Object> map) {
		logger.info("&LgMode&TestController&selectUser&ApiStatistics");
		Users users = this.usersService.selectUser(map);
		return new ResultVO<Users>(WebConstant.CODE_200, WebConstant.SUCCESS, users);
	}
}
