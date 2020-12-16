package com.bi.activity.util;

import java.util.List;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author zlg 分页封装
 * 
 * @param <T>
 */
@ApiModel(value = "ZPageUtil<T>对象", description = "分页封装对象ZPageUtil<T>")
public class ZPageUtil<T> {
  /**
   * 分页查询到的数据集合
   */
  @ApiModelProperty(value = "分页查询到的数据集合")
  private List<T> list;
  /**
   * 查询页码
   */
  @ApiModelProperty(value = "查询页码")
  private int currentPage;
  /**
   * 每页显示条数
   */
  @ApiModelProperty(value = "每页显示条数")
  private int pageSize;
  /**
   * 总条数
   */
  @ApiModelProperty(value = "总条数")
  private int count;
  /**
   * 总页数
   */
  @ApiModelProperty(value = "总页数")
  private int totalPage;
  /**
   * 查询起始下标
   */
  @ApiModelProperty(value = "查询起始下标")
  private int dataIndex;

  public ZPageUtil(List<T> list, int currentPage, int pageSize, int count) {

    this.list = list;
    this.count = count;
    // 设置默认每页显示条数
    if (pageSize == 0) {
      this.pageSize = 10;
    } else {
      this.pageSize = pageSize;
    }
    // 计算总页数
    this.totalPage =
        count % this.pageSize == 0 ? count / this.pageSize : (count / this.pageSize) + 1;
    // 判断当前页不能显示小于第一页，不能大于总页数
    if (currentPage <= 1) {
      this.currentPage = 1;
      this.dataIndex = 0;
    } else if (currentPage > this.totalPage) {
      this.currentPage = this.totalPage;
      this.dataIndex = (this.currentPage - 1) * this.pageSize;
    } else {
      this.currentPage = currentPage;
      this.dataIndex = (this.currentPage - 1) * this.pageSize;
    }

  }

  public ZPageUtil(int currentPage, int pageSize, int count) {
    this.count = count;
    // 设置默认每页显示条数
    if (pageSize == 0) {
      this.pageSize = 10;
    } else {
      this.pageSize = pageSize;
    }
    // 计算总页数
    this.totalPage =
        count % this.pageSize == 0 ? count / this.pageSize : (count / this.pageSize) + 1;
    // 判断当前页不能显示小于第一页，不能大于总页数
    if (currentPage <= 1) {
      this.currentPage = 1;
      this.dataIndex = 0;
    } else if (currentPage > this.totalPage) {
      this.currentPage = this.totalPage;
      this.dataIndex = (this.currentPage - 1) * this.pageSize;
      if (this.currentPage == 0) {
        this.dataIndex = 0;
      }
    } else {
      this.currentPage = currentPage;
      this.dataIndex = (this.currentPage - 1) * this.pageSize;
    }

  }

  /**
   * 
   * newMap:(Describe the function of this method)
   * 
   * @Description: TODO 根据传入参数得到分页查询封装map
   * @param count
   * @param map
   * @return
   * @throws
   */
  public static <T> ZPageUtil<T> newMap(Map<String, Object> map, Class<T> targetClass) {
    // 总条数
    int count = 0;
    // 当前页数
    int currentPage = 1;
    // 每页显示条数
    int pageSize = 10;

    if (!"".equals(map.get("currentPage")) && map.get("currentPage") != null) {
      currentPage = Integer.parseInt(map.get("currentPage").toString());
    }
    if (!"".equals(map.get("pageSize")) && map.get("pageSize") != null) {
      pageSize = Integer.parseInt(map.get("pageSize").toString());
    }
    if (!"".equals(map.get("count")) && map.get("count") != null) {
      count = Integer.parseInt(map.get("count").toString());
    }
    // 得到分页数据
    ZPageUtil<T> zp = new ZPageUtil<T>(currentPage, pageSize, count);
    System.out.println("this result_cache is :" + zp);

    map.put("dataIndex", zp.getDataIndex());
    map.put("pageSize", zp.getPageSize());
    return (ZPageUtil<T>) zp;

  }


  public int getCount() {
    return count;
  }



  public void setCount(int count) {
    this.count = count;
  }



  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public ZPageUtil() {

  }

  public int getDataIndex() {
    return dataIndex;
  }


  public void setDataIndex(int dataIndex) {
    this.dataIndex = dataIndex;
  }


  @Override
  public String toString() {
    return "ZPageUtil [list=" + list + ", currentPage=" + currentPage + ", totalPage=" + totalPage
        + ", pageSize=" + pageSize + ", count=" + count + ", dataIndex=" + dataIndex + "]";
  }


}
