package com.crossoverjie.kafka.orderconsumer.dao;

import com.crossoverjie.kafka.orderconsumer.pojo.StockOrder;
import com.crossoverjie.kafka.orderconsumer.pojo.StockOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockOrderMapper {
    int countByExample(StockOrderExample example);

    int deleteByExample(StockOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockOrder record);

    int insertSelective(StockOrder record);

    List<StockOrder> selectByExample(StockOrderExample example);

    StockOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockOrder record, @Param("example") StockOrderExample example);

    int updateByExample(@Param("record") StockOrder record, @Param("example") StockOrderExample example);

    int updateByPrimaryKeySelective(StockOrder record);

    int updateByPrimaryKey(StockOrder record);
}