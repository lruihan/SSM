package com.crossoverJie.seconds.kill.api.service2.impl;

import com.crossoverJie.seconds.kill.api.service2.StockService;
import com.crossoverjie.kafka.orderconsumer.mapper.StockMapper;
import com.crossoverjie.kafka.orderconsumer.pojo.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 30/04/2018 22:39
 * @since JDK 1.8
 */
@Service("DBStockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public int getStockCount(int id) {
        Stock ssmStock = stockMapper.selectByPrimaryKey(id);
        return ssmStock.getCount();
    }

    @Override
    public Stock getStockById(int id) {
        return stockMapper.selectByPrimaryKey(id) ;
    }

    @Override
    public int updateStockById(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock) ;
    }

    @Override
    public int updateStockByOptimistic(Stock stock) {
        return stockMapper.updateByOptimistic(stock);
    }
}
