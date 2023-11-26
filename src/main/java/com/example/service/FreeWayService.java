package com.example.service;

import com.example.entity.FreeWay;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.FreeWayMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FreeWayService extends ServiceImpl<FreeWayMapper, FreeWay> {

    @Resource
    private FreeWayMapper freeWayMapper;

}
