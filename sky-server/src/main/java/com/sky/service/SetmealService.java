package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

public interface SetmealService {
    //新增套餐
    void addSetmeal(SetmealDTO setmealDTO);
    //套餐分页查询
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);
}
