package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    //新增套餐
    void addSetmeal(SetmealDTO setmealDTO);
    //套餐分页查询
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);
    //删除套餐
    void deleteSetmealWithDish(List<Long> ids);
    //根据id查询套餐
    SetmealVO getSetmealByIdWithDish(Long id);
    //修改套餐
    void update(SetmealDTO setmealDTO);
    //起售停售套餐
    void changeStatus(Long status, Long id);
}
