package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("路段车流量每日汇总信息20230911")
public class TrafficVolume extends Model<TrafficVolume> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 道路编号 
      */
    private String 道路编号;

    /**
      * 自增ID 
      */
    private String 自增ID;

    /**
      * 车流量 
      */
    private String 车流量;

    /**
      * 道路名称 
      */
    private String name;

    /**
      * 日期 
      */
    private String 日期;

}