package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    //新增套餐
    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    //套餐分页查询
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    //根据id查询套餐
    @Select("select * from setmeal where id=#{id}")
    Setmeal getById(Long id);

    //根据id删除套餐
    @Delete("delete from setmeal where id=#{id}")
    void deleteById(Long setmealId);

    //修改套餐信息
    void update(Setmeal setmeal);

    //起售停售套餐
    @Update("update setmeal set status=#{status} where id=#{id}")
    void changeStatus(Long status, Long id);
}
