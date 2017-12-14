package com.ejie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.pojo.TbItem;
import com.ejie.service.ItemService;

/**
 * 商品管理Controller
* @ClassName: ItemController
* @Description: TODO  商品管理
* @author Administrator
* @date 2017年8月25日下午6:32:49
*
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem=itemService.getItemById(itemId);
		return tbItem;
	}
	
}
