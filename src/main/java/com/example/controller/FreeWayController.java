package com.example.controller;

import com.example.common.Result;
import com.example.entity.FreeWay;
import com.example.service.FreeWayService;
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
@RequestMapping("/api/freeWay")
public class FreeWayController {
    @Resource
    private FreeWayService freeWayService;
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
    public Result<?> save(@RequestBody FreeWay freeWay) {
        return Result.success(freeWayService.save(freeWay));
    }

    @PutMapping
    public Result<?> update(@RequestBody FreeWay freeWay) {
        return Result.success(freeWayService.updateById(freeWay));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        freeWayService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(freeWayService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(freeWayService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<FreeWay> query = Wrappers.<FreeWay>lambdaQuery().like(FreeWay::getName, name).orderByDesc(FreeWay::getId);;
        return Result.success(freeWayService.page(new Page<>(pageNum, pageSize), query));
    }

}
