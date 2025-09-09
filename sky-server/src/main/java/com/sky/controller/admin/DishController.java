package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/admin//dish")
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;


    //新增菜品以及对应口味
    @PostMapping
    @ApiOperation("新增菜品")
    public Result saveDish(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavour(dishDTO);

        //清理缓存数据
        String key = "dish_"+dishDTO.getCategoryId();
        cleanCache(key);

        return Result.success();
    }

    //菜品分页查询
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询菜品");
        PageResult p = dishService.page(dishPageQueryDTO);
        return Result.success(p);
    }

    //删除菜品
    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result deleteDish(@RequestParam List<Long> ids){
        log.info("批量删除菜品：{}", ids);
        dishService.deleteBatch(ids);

        //将所有的菜品缓存数据清除，所有以dish_开头的key
        cleanCache("dish_*");

        return Result.success();
    }

    //根据id查询菜品和口味
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getDish(@PathVariable Long id){
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getDishWithFlavor(id);
        return Result.success(dishVO);
    }

    //修改菜品以及关联口味
    @PutMapping
    @ApiOperation("修改菜品")
    public Result updateDish(@RequestBody DishDTO dishDTO){
        log.info("修改菜品:{}", dishDTO);
        dishService.updateDishWithFlavor(dishDTO);

        //将所有的菜品缓存数据清除，所有以dish_开头的key
        cleanCache("dish_*");

        return Result.success();
    }

    //菜品启售停售
    @PostMapping("/status/{status}")
    @ApiOperation("切换菜品启售停售状态")
    public Result updateStatus(@PathVariable Integer status, Long id){
        log.info("切换菜品启售停售状态：{},{}", status,id);
        dishService.updateStatus(status,id);

        //将所有的菜品缓存数据清除，所有以dish_开头的key
        cleanCache("dish_*");

        return Result.success();
    }

    //根据分类id查询菜品
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(@RequestParam Long categoryId){
        log.info("根据分类id查询菜品：{}", categoryId);
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    //清除缓存数据
    private  void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

}
