package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 
* @ClassName: EasyUIDataGridResult
* @Description: TODO(这里用一句话描述这个类的作用)
* @author Administrator
* @date 2018年2月24日下午4:50:32
*
 */
public class EasyUIDataGridResult implements Serializable{

	private long total;
	private List rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
