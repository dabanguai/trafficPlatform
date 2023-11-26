package com.example.service;

import com.example.entity.CongestionIndex;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.CongestionIndexMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CongestionIndexService extends ServiceImpl<CongestionIndexMapper, CongestionIndex> {

    @Resource
    private CongestionIndexMapper congestionIndexMapper;

}
