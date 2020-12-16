package com.lg.web.module.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lg.web.module.annotation.OperateNote;
import com.lg.web.module.annotation.VisitLimit;
import com.lg.web.module.bean.po.Users;
import com.lg.web.module.bean.vo.ResultVO;
import com.lg.web.module.constant.NoteTypeEnum;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.exception.BusinessException;
import com.lg.web.module.mongodb.TestMongoDB;
import com.lg.web.module.service.MsService;
import com.lg.web.module.service.SecretService;
import com.lg.web.module.service.UsersService;
import com.lg.web.module.thread.ThreadB;
import com.lg.web.module.util.SpringContextUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/test/")
public class TestController {
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private UsersService usersService;

	public static void main(String[] args) {

		// for (int i = 0; i < 1; i++) {
		// Long timeStart = System.currentTimeMillis();
		// String url =
		// "http://192.168.31.28:8088/center/front/pass/verifyOldUserFirstLogin";
		//// String url =
		// "http://192.168.31.28:8088/center/front/pass/getBizTokenForLivingContrast";
		// Map<String, Object> sendMap = new HashMap<String, Object>();
		//// sendMap.put("mobile", RandomUtil.getRandomNum3(1l,
		// 1000000000000l));
		// sendMap.put("mobile", "13688192543");
		// String resultStr = RestTemplateUtil.postForEntity(url,
		// JSONObject.toJSONString(sendMap));
		// System.out.println("resultStr is : " + resultStr);
		// Long timeEnd = System.currentTimeMillis();
		// System.out.println("RestTemplateUtil time used : " + (timeEnd -
		// timeStart));
		// //1次 1411
		// //100次 3079
		// }

		// for (int i = 0; i < 1; i++) {
		// Long timeStart = System.currentTimeMillis();
		// String url =
		// "http://192.168.31.28:8088/center/front/pass/verifyOldUserFirstLogin";
		//// String url =
		// "http://192.168.31.28:8088/center/front/pass/getBizTokenForLivingContrast";
		// Map<String, Object> sendMap = new HashMap<String, Object>();
		//// sendMap.put("mobile", RandomUtil.getRandomNum3(1l,
		// 1000000000000l));
		// sendMap.put("mobile", "59875173");
		// String resultStr = OkHttpUtil.postForJson(url,
		// JSONObject.toJSONString(sendMap));
		// System.out.println("resultStr is : " + resultStr);
		// Long timeEnd = System.currentTimeMillis();
		// System.out.println("OkHttpUtil time used : " + (timeEnd -
		// timeStart));
		// }
		TestMongoDB tm = new TestMongoDB();
		Object find = tm.find("kk");
		System.out.println(find);

	}

	@Autowired
	private TestMongoDB testMongoDB;

	@ApiOperation(value = "test————zlg", notes = "无")
	@RequestMapping(value = "test", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO<Object> test() {
		logger.info("&LgMode&TestController&test&ApiStatistics");
		Object find = testMongoDB.find("kk");
		System.out.println(find);
		return new ResultVO<Object>(WebConstant.CODE_200, WebConstant.SUCCESS, find);
	}

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

	/**
	 * 跳转到websocket页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "websocket")
	@ApiIgnore
	public ModelAndView websocket(@Param(value = "paramKey") String paramKey) {
		return new ModelAndView("websocket", "str", paramKey);
	}
	
	@ApiOperation(value = "netty-websocket测试建立链接方法————zlg", notes = "建立链接名		name（必填）")
	@RequestMapping(value = "testNettyWs", method = RequestMethod.POST)
	@VisitLimit(sec = 5,limit = 1)
	@ResponseBody
	public ResultVO<String> testNettyWs(@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, Object> map){
		logger.info("&LgMode&TestController&testNettyWs&ApiStatistics");
		this.usersService.testNettyWs(map);
		return new ResultVO<String>(WebConstant.CODE_200, WebConstant.SUCCESS, "链接建立成功！");
	}
	
	@ApiOperation(value = "netty-websocket测试主动断开链接方法————zlg", notes = "断开链接名		name（必填）")
	@RequestMapping(value = "testNettyWs2", method = RequestMethod.POST)
	@VisitLimit(sec = 5,limit = 1)
	@ResponseBody
	public ResultVO<String> testNettyWs2(@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, Object> map){
		logger.info("&LgMode&TestController&testNettyWs2&ApiStatistics");
		this.usersService.testNettyWs2(map);
		return new ResultVO<String>(WebConstant.CODE_200, WebConstant.SUCCESS, "成功断开链接！");
	}

	@ApiOperation(value = "netty-websocket测试客户端主动发送消息方法————zlg", notes = "链接名		name（必填）<br>消息内容    msg（必填）")
	@RequestMapping(value = "testNettyWs3", method = RequestMethod.POST)
	@VisitLimit(sec = 5,limit = 1)
	@ResponseBody
	public ResultVO<String> testNettyWs3(@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, Object> map){
		logger.info("&LgMode&TestController&testNettyWs3&ApiStatistics");
		this.usersService.testNettyWs3(map);
		return new ResultVO<String>(WebConstant.CODE_200, WebConstant.SUCCESS, "消息发送成功！");
	}
	@ApiOperation(value = "testSys————zlg", notes = "无")
	@RequestMapping(value = "testSys", method = RequestMethod.POST)
	@ResponseBody
	public void testSys() throws Exception {
		ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) appCtx.getBean("redisTemplate",
				RedisTemplate.class);
		System.out.println("开始");

		MsService service = new MsService();

		for (int i = 1; i <= 100; i++) {
			ThreadB threadA = new ThreadB(service, redisTemplate, "MSKEY");
			threadA.start();
		}
	}

	@ApiOperation(value = "测试抢购redis写入————zlg", notes = "键    msKey（必填）<br>值    msValue（必填）")
	@RequestMapping(value = "set2Redis", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO<String> set2Redis(
			@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, Object> map) {
		logger.info("&LgMode&TestController&set2Redis&ApiStatistics");
		String resultStr = "写入失败";
		try {
			resultStr = this.usersService.set2Redis(map);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			logger.error("set2Redis?" + JSONObject.toJSONString(map));
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("set2Redis?" + JSONObject.toJSONString(map));
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		}
		return new ResultVO<String>(WebConstant.CODE_200, WebConstant.SUCCESS, resultStr);
	}

	@ApiOperation(value = "高级实名认证图片上传————zlg", notes = "用户id    memberId（必填）<br>" + "身份证正面照片    idcardPicFront（必填）<br>"
			+ "身份证反面照片    idcardPicBack（必填）<br>" + "手持身份证及币和id照片    idcardPicOnhand（必填）")
	@RequestMapping(value = "certificationImages", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO<String> certificationImages(@Param("memberId") String memberId,
			@Param("idcardPicFront") MultipartFile idcardPicFront, @Param("idcardPicBack") MultipartFile idcardPicBack,
			@Param("idcardPicOnhand") MultipartFile idcardPicOnhand) {
		logger.info("&LgMode&TestController&certificationImages&ApiStatistics");
		String resultStr = null;
		try {
//			logger.info("------------idcardPicFront=" + idcardPicFront);
//			logger.info("------------idcardPicBack=" + idcardPicBack);
//			logger.info("------------idcardPicOnhand=" + idcardPicOnhand);
			resultStr = this.usersService.certificationImages(memberId, idcardPicFront, idcardPicBack, idcardPicOnhand);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			logger.error("certificationImages?{\"memberId\":\"" + memberId + "\"}");
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("certificationImages?{\"memberId\":\"" + memberId + "\"}");
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		}
		return new ResultVO<String>(WebConstant.CODE_200, resultStr, WebConstant.SUCCESS);
	}
	
	@Autowired
	private SecretService secretService;

	Map<String, String> testMap = new HashMap<String, String>();

	@ApiOperation(value = "测试加密————zlg", notes = "无")
	@RequestMapping(value = "/test1", method = RequestMethod.POST)
	@ResponseBody
	public String test1(
			@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, String> map) {
		String encryptParameter = secretService.encryptParameter(map);
		// System.out.println("数据加密后。。。。。。" + encryptParameter);
		testMap.put("ciphertext", encryptParameter);
		return encryptParameter;
	}

	@ApiOperation(value = "测试解密————zlg", notes = "无")
	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	@ResponseBody
	public String test2() {
		String decryptpParameter = JSONObject.toJSONString(secretService.decryptpParameter(testMap));
		System.out.println("数据解密后。。。。。。" + decryptpParameter);
		return decryptpParameter;
	}

	@ApiOperation(value = "测试解密指定密文————zlg", notes = "无")
	@RequestMapping(value = "/test3", method = RequestMethod.POST)
	@ResponseBody
	public String test3(
			@RequestBody @ApiParam(name = "条件map", value = "jeson格式map", required = true) Map<String, String> map) {
		System.out.println("原始传入数据：" + map);
		Map<String, String> map_ = secretService.decryptpParameter(map);
		String decryptpParameter = JSONObject.toJSONString(map_);
		System.out.println("数据解密后。。。。。。" + decryptpParameter);
		return decryptpParameter;
	}
}
