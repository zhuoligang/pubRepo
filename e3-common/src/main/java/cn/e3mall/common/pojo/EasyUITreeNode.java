package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * 
* @ClassName: EasyUITreeNode
* @Description: TODO(这里用一句话描述这个类的作用)
* @author Administrator
* @date 2018年2月26日上午11:19:31
*
 */
public class EasyUITreeNode implements Serializable{
	
	private long id;
	private String text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
