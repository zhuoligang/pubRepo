package com.bi.activity.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * ClassName:ConfigManager
 * 
 * @author torreswang
 * @version
 * @since Ver 1.1
 * @Date 2017 2017年8月10日 下午7:26:21
 * @see
 */
public class ConfigManager {
  /*
   * 
   * @author Administrator 属性文件全名
   */

  private static String PFILE = null;

  private static String PFILE2 = null;
  /*
   * 
   * 对应于属性文件的文件对象变量
   */
  private File m_file = null;

  /*
   * 属性文件的最后修改日期
   */

  private long m_lastModifiedTime = 0;

  /*
   * 
   * 属性文件所对应的属性对象变量
   */

  private Properties m_props = null;

  private Properties m_propsLog = null;

  /*
   * 
   * 本例可能存在的唯一的一个实例
   */

  private static ConfigManager m_instance = new ConfigManager();

  /*
   * 
   * 私有的构造子， 用以保证外界无法直接实例化
   */
  public ConfigManager() {
    // Class clazz = getClass();
    m_props = new Properties();
    setM_propsLog(new Properties());

    try {
      PFILE = Resources.getResourceURL("conf.properties").getPath();
      PFILE2 = Resources.getResourceURL("log4j.properties").getPath();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }


    m_file = new File(PFILE);
    setM_lastModifiedTime(m_file.lastModified());
    try {

      // 读log4j数据
      PropertyConfigurator.configure(PFILE2);
      // 读属性文件
      m_props.load(new FileInputStream(PFILE));
      // m_props.load(new OutputStreamWriter(new
      // FileInputStream(PFILE),"gbk"));
      // m_props.load(new InputStreamReader(new
      // FileInputStream(PFILE),"gbk"));

    } catch (Exception e) {
      System.out.println("读写文件错误" + e);
    }
  }

  /*
   * 
   * 静态工厂方法
   * 
   * @return 返还ConfigManager 类的单一实例
   */
  synchronized public static ConfigManager getInstance() {

    return m_instance;
  }

  /*
   * 
   * 读取一个特定属性
   * 
   * @param name 属性项的项名
   * 
   * @param defaultVal 属性项的默认值
   * 
   * @return 属性项的值（如此项存在），默认值（如此项不存在）
   */
  final public String getConfigItem1(String name, String defaultVal) {
    String val = m_props.getProperty(name);
    if (val == null) {
      return defaultVal.trim();
    } else {
      return val.trim();
    }
  }

  final public String getConfigItem(String name) throws Exception {
    String val = m_props.getProperty(name);
    if (val == null) {
      throw new Exception("属性文件取值有误");
    } else {
      return val.trim();
    }
  }

  public Properties getM_propsLog() {
    return m_propsLog;
  }

  public void setM_propsLog(Properties m_propsLog) {
    this.m_propsLog = m_propsLog;
  }

  public long getM_lastModifiedTime() {
    return m_lastModifiedTime;
  }

  public void setM_lastModifiedTime(long m_lastModifiedTime) {
    this.m_lastModifiedTime = m_lastModifiedTime;
  }

}
