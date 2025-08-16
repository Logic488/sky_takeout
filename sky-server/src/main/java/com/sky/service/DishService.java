package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    //新增菜品和对应的口味
    public void saveWithFlavour(DishDTO dishDTO);

    //分页查询菜品
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    //批量删除菜品
    void deleteBatch(List<Long> ids);

    //根据id查询菜品和口味
    DishVO getDishWithFlavor(Long id);

    //修改菜品以及关联口味
    void updateDishWithFlavor(DishDTO dishDTO);

    //切换菜品启售停售状态
    void updateStatus(Integer status, Long id);

    //根据分类id查询菜品
    List<Dish> list(Long categoryId);
}
