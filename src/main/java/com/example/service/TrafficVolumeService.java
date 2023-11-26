package com.example.service;

import com.example.entity.TrafficVolume;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.TrafficVolumeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TrafficVolumeService extends ServiceImpl<TrafficVolumeMapper, TrafficVolume> {

    @Resource
    private TrafficVolumeMapper trafficVolumeMapper;

}
