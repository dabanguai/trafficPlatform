package com.example.controller;

import com.example.common.Result;
import com.example.entity.FastRoad;
import com.example.service.FastRoadService;
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
@RequestMapping("/api/fastRoad")
public class FastRoadController {
    @Resource
    private FastRoadService fastRoadService;
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
    public Result<?> save(@RequestBody FastRoad fastRoad) {
        return Result.success(fastRoadService.save(fastRoad));
    }

    @PutMapping
    public Result<?> update(@RequestBody FastRoad fastRoad) {
        return Result.success(fastRoadService.updateById(fastRoad));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        fastRoadService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(fastRoadService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(fastRoadService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<FastRoad> query = Wrappers.<FastRoad>lambdaQuery().like(FastRoad::getName, name).orderByDesc(FastRoad::getId);;
        return Result.success(fastRoadService.page(new Page<>(pageNum, pageSize), query));
    }

}
