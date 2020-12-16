/**
 * ServerEncoder.java
 * com.ejie.compliance.util
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年1月26日 		dengkaixuan@ejie365.com
 *
 * Copyright (c) 2018, EJie All Rights Reserved.
*/

package com.bi.activity.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.aspectj.weaver.BCException;
import org.springframework.web.socket.WebSocketHandler;

import com.bi.activity.util.JsonUtil;



/**
 * ClassName:ServerEncoder（Describe this Class）
 * @author   dengkaixuan@ejie365.com
 * @version  Ver 1.0
 * @Date	 2018年1月26日		上午11:41:43
 * @see 	 
 */
public class ServerEncoder implements Encoder.Text<WebSocketHandler> {

    @Override
    public void destroy() {
      // TODO Auto-generated method stub
    }
  
    @Override
    public void init(EndpointConfig arg0) {
      // TODO Auto-generated method stub
    }
  
    @Override
    public String encode(WebSocketHandler arg) throws EncodeException {
      try {
          return JsonUtil.toJson(arg);
      } catch (BCException e) {
          e.printStackTrace();
      }
      return null;
   } 
  
}

