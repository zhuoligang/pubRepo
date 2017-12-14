package com.ejie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.mapper.TbItemMapper;
import com.ejie.pojo.TbItemExample.Criteria;
import com.ejie.pojo.TbItem;
import com.ejie.pojo.TbItemExample;
import com.ejie.service.ItemService;

/**
 * 商品管理Service
* @ClassName: ItemServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author Administrator
* @date 2017年8月25日下午6:04:56
*
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	@Override
	public TbItem getItemById(long itemid) {
		//根据主键查询
		//TbItem tbItem=itemMapper.selectByPrimaryKey(itemid);
		
		TbItemExample example=new TbItemExample();
		Criteria criteria=example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemid);
		//执行查询
		List<TbItem> list=itemMapper.selectByExample(example);
		if(list !=null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
