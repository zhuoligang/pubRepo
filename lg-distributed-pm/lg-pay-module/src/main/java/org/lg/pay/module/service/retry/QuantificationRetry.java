package org.lg.pay.module.service.retry;

import org.lg.pay.module.constants.Codes;
import org.lg.pay.module.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @Description
 * @ClassName QuantificationRetry
 * @Author zlg
 * @date 2020.03.31 16:52
 */
@Service
public class QuantificationRetry {
    private final static Logger logger = LoggerFactory.getLogger(QuantificationRetry.class);
    private static ThreadLocal<Integer> retryTimesInThread = new ThreadLocal<Integer>(){
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Integer initialValue()
        {
//            System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return 0;
        }
    };
    private final int totalNum = 100000;


    @Retryable(value = BusinessException.class, maxAttempts = 4, backoff = @Backoff(delay = 1000L, multiplier = 1.5))
    public int minGoodsnum(int num) {
        logger.info("减库存开始" + LocalTime.now());
        logger.info("执行序列号->" + retryTimesInThread.get());
        retryTimesInThread.set(retryTimesInThread.get() + 1);
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("illegal");
//            throw new BusinessException(Codes.CODE_500, "illegal");
        }
        if (num <= 0) {
            throw new BusinessException(Codes.CODE_500,"数量不对");
        }
        logger.info("减库存执行结束" + LocalTime.now());
        return totalNum - num;
    }

    @Recover
    public int recover(BusinessException e) {
        logger.warn("减库存失败！！！" + LocalTime.now());
        logger.error("【【recover】】", e.getMessage());
        logger.error(e.getMessage(), e);
        return totalNum;
    }
}