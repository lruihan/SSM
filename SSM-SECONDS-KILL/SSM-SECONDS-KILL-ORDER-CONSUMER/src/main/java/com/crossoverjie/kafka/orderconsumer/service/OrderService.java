package com.crossoverjie.kafka.orderconsumer.service;

import com.crossoverjie.kafka.orderconsumer.pojo.Stock;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 01/05/2018 14:09
 * @since JDK 1.8
 */
public interface OrderService {

    /**
     * 创建订单
     * @param sid
     *  库存ID
     * @return
     *  订单ID
     */
    int createWrongOrder(int sid) throws Exception;


    /**
     * 创建订单 乐观锁
     * @param sid
     * @return
     * @throws Exception
     */
    int createOptimisticOrder(int sid) throws Exception;


    /**
     * 创建订单 乐观锁，库存查 Redis 减小 DB 压力。
     * @param sid
     * @return
     * @throws Exception
     */
    int createOptimisticOrderUseRedis(int sid) throws Exception ;


    /**
     * 创建订单 乐观锁，库存查 Redis 减小 DB 压力。
     * 利用 Kafka 异步写订单
     * @param sid
     * @return
     * @throws Exception
     */
    void createOptimisticOrderUseRedisAndKafka(int sid) throws Exception ;

        /**
     * 创建订单 乐观锁，库存查 Redis 减小 DB 压力。
     * 利用 Kafka 异步写订单
     * @param stock
     * @return
     * @throws Exception
     */
    void createOptimisticOrderUseRedisAndKafka(Stock stock) throws Exception ;
}
