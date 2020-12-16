package org.lg.pay.module.service;

import com.alibaba.fastjson.JSONObject;
import org.lg.pay.module.constants.Codes;
import org.lg.pay.module.exception.BusinessException;
import org.lg.pay.module.service.retry.QuantificationRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description
 * @ClassName TestService
 * @Author zlg
 * @date 2020.04.01 13:57
 */
@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(TestService.class);
    @Autowired
    private QuantificationRetry quantificationRetry;


    public void tt(Map<String, String> map) {
        try {
            int i = this.quantificationRetry.minGoodsnum(-1);
            System.out.println("-----------------------库存为：" + i);

//            this.quantificationRetry.excute2(map);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            logger.error("tt?" + JSONObject.toJSONString(map));
            throw new BusinessException(Codes.CODE_500, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("tt?" + JSONObject.toJSONString(map));
            throw new BusinessException(Codes.CODE_500, e.getMessage());
        }
    }

}