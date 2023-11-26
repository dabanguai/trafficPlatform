package com.example.controller;

import com.example.common.Result;
import com.example.entity.TrafficVolume;
import com.example.service.TrafficVolumeService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/trafficVolume")
public class TrafficVolumeController {
    @Resource
    private TrafficVolumeService trafficVolumeService;
    @Resource
    private HttpServletRequest request;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }

    @PostMapping
    public Result<?> save(@RequestBody TrafficVolume trafficVolume) {
        return Result.success(trafficVolumeService.save(trafficVolume));
    }

    @PutMapping
    public Result<?> update(@RequestBody TrafficVolume trafficVolume) {
        return Result.success(trafficVolumeService.updateById(trafficVolume));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        trafficVolumeService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(trafficVolumeService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(trafficVolumeService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<TrafficVolume> query = Wrappers.<TrafficVolume>lambdaQuery().like(TrafficVolume::getName, name).orderByDesc(TrafficVolume::getId);;
        return Result.success(trafficVolumeService.page(new Page<>(pageNum, pageSize), query));
    }

}
