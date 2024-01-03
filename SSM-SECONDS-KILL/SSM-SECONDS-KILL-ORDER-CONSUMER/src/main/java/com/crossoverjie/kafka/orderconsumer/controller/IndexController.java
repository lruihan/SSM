package com.crossoverjie.kafka.orderconsumer.controller;

// import com.crossoverjie.distributed.annotation.ControllerLimit;
// import com.crossoverjie.distributed.annotation.SpringControllerLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crossoverjie.distributed.annotation.SpringControllerLimit;
import com.crossoverjie.kafka.orderconsumer.service.OrderService;
import com.crossoverjie.kafka.orderconsumer.service.StockService;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 15/04/2018 21:58
 * @since JDK 1.8
 */
@RestController
// @RequestMapping(value = "/")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

    @RequestMapping("/health")
    @ResponseBody
    public String health() {
        logger.info("heath");
        return "OK";
    }

    // @RequestMapping("/getStockNum")
    // @ResponseBody
    // public String getStockNum() {
    //     int currentCount = 0;
    //     try {
    //         currentCount = stockService.getCurrentCount();
    //     } catch (Exception e) {
    //         logger.error("Exception",e);
    //     }
    //     logger.info("currentCount={}", currentCount);
    //     return String.valueOf(currentCount);
    // }


    @RequestMapping("/createWrongOrder/{sid}")
    @ResponseBody
    public String createWrongOrder(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createWrongOrder(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }


    /**
     * 乐观锁更新库存
     * @param sid
     * @return
     */
    @RequestMapping("/createOptimisticOrder/{sid}")
    @ResponseBody
    public String createOptimisticOrder(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createOptimisticOrder(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存 限流
     * @param sid
     * @return
     */
    @SpringControllerLimit(errorCode = 200)
    @RequestMapping("/createOptimisticLimitOrder/{sid}")
    @ResponseBody
    public String createOptimisticLimitOrder(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createOptimisticOrder(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存 限流 库存改为查询 Redis 提高性能
     * @param sid
     * @return
     */
    @SpringControllerLimit(errorCode = 200,errorMsg = "request has limited")
    @RequestMapping("/createOptimisticLimitOrderByRedis/{sid}")
    @ResponseBody
    public String createOptimisticLimitOrderByRedis(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createOptimisticOrderUseRedis(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }

    /**
     * 乐观锁更新库存 限流 库存改为查询 Redis 提高性能
     * 异步创建订单 Kafka
     * @param sid
     * @return
     */
    // @SpringControllerLimit
    @RequestMapping("/createOptimisticLimitOrderByRedisAndKafka/{sid}")
    @ResponseBody
    public String createOptimisticLimitOrderByRedisAndKafka(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            orderService.createOptimisticOrderUseRedisAndKafka(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }
}
