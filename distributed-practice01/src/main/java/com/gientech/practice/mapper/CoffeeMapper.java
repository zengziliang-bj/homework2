package com.gientech.practice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gientech.practice.model.Coffee;

@Mapper
public interface CoffeeMapper {

	@Select("select * from t_coffee order by id desc")
	List<Coffee> findAllWithParamById(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

	List<Coffee> selectByPrimaryKeys(@Param("ids") Long[] ids, @Param("pageNum") int pageNum,
			@Param("pageSize") int pageSize);

	List<Coffee> testAppendSql(@Param("appendSql") String appendSql);

	Coffee selectByPrimaryKey(Long id);

	int insertSelective(Coffee record);

	int updateByPrimaryKeySelective(Coffee record);

	@Delete({ "delete from t_coffee", "where ID = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);
}
