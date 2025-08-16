package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    //插入菜品
    @AutoFill(value= OperationType.INSERT)
    void insert(Dish dish);

    //分页查询菜品
    Page<DishVO> getpage(DishPageQueryDTO dishPageQueryDTO);

    //根据主键查询菜品
    @Select("select * from dish where id=#{id}")
    Dish getById(Long id);

    //根据主键删除菜品
    @Delete("delete from dish where id =#{id}")
    void deleteById(Long id);

    //根据id查询菜品
    @Select("select * from dish where id=#{id}")
    Dish getDish(Long id);

    //修改菜品
    @AutoFill(value = OperationType.UPDATE)
    void updateDish(Dish dish);

    //切换菜品启售停售状态
//    @AutoFill(value = OperationType.UPDATE)
    @Update("update dish set status=#{status}  where id=#{id}")
    void updateStatus(Integer status, Long id);

    //查询菜品回显
    List<Dish> list(Dish dish);
}
