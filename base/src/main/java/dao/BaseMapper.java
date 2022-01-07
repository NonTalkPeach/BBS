package dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface BaseMapper<Entity> {
    public String table = "";

    @Select("Select * from " + table)
    public List<Entity> selectAll();
    @Select("Select * from " + table + "where #{param} = #{value}")
    public Entity selectOneByParam(String param,Object value);
}
