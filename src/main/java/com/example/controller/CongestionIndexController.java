package com.example.controller;

import com.example.common.Result;
import com.example.entity.CongestionIndex;
import com.example.service.CongestionIndexService;
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
@RequestMapping("/api/congestionIndex")
public class CongestionIndexController {
    @Resource
    private CongestionIndexService congestionIndexService;
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
    public Result<?> save(@RequestBody CongestionIndex congestionIndex) {
        return Result.success(congestionIndexService.save(congestionIndex));
    }

    @PutMapping
    public Result<?> update(@RequestBody CongestionIndex congestionIndex) {
        return Result.success(congestionIndexService.updateById(congestionIndex));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        congestionIndexService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(congestionIndexService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(congestionIndexService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<CongestionIndex> query = Wrappers.<CongestionIndex>lambdaQuery().like(CongestionIndex::getName, name).orderByDesc(CongestionIndex::getId);;
        return Result.success(congestionIndexService.page(new Page<>(pageNum, pageSize), query));
    }

}
