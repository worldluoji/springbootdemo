package com.mybatis.simplemybatisdemo.mapper;
import com.mybatis.simplemybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CoffeeMapper {

    @Insert("insert into t_coffee(name, price, create_time, update_time) values(#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    Long save(Coffee coffee);

    @Select("select * from t_coffee where id=#{id}")
    Coffee findById(@Param("id") Long id);
}
