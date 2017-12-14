package com.ejie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
* @ClassName: PageController 页面跳转控制
* @Description: TODO(这里用一句话描述这个类的作用)
* @author Administrator
* @date 2017年11月1日上午10:25:16
*
 */
@Controller
public class PageController {
	
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
	
}
