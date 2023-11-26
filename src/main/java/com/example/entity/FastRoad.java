package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("拱墅区_高速路均速信息20230911")
public class FastRoad extends Model<FastRoad> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 时间点 
      */
    private String time;

    /**
      * 高速路均速历史值 
      */
    private String 高速路均速历史值;

    /**
      * 区域名称 
      */
    private String name;

    /**
      * 高速路均速 
      */
    private String 高速路均速;

    /**
      * 实时和历史趋势 
      */
    private String 实时和历史趋势;



}