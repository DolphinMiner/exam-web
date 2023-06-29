package com.whc.data.mapper;

import com.whc.data.entity.DemoData;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
@Repository
public interface DataMapper {

    /**
     * query specific id
     *
     * @param id id
     * @return data
     */
    @Select("select * from demo where id = #{id}")
    @ResultType(com.whc.data.entity.DemoData.class)
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="col_a", property="colA", jdbcType= JdbcType.INTEGER),
            @Result(column="col_b", property="colB", jdbcType= JdbcType.INTEGER),
            @Result(column="col_c", property="colC", jdbcType= JdbcType.INTEGER)
    })
    DemoData queryById(@Param("id") long id);


    /**
     * query all
     *
     * @return data
     */
    @Select("select * from demo")
    @ResultType(com.whc.data.entity.DemoData.class)
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="col_a", property="colA", jdbcType= JdbcType.INTEGER),
            @Result(column="col_b", property="colB", jdbcType= JdbcType.INTEGER),
            @Result(column="col_c", property="colC", jdbcType= JdbcType.INTEGER)
    })
    ArrayList<DemoData> queryAll();


    /**
     * insert a piece of data
     *
     * @param colA colA
     * @param colB colB
     * @param colC colC
     * @return insert result
     */
    @Insert("insert into demo" + "(col_a,col_b,col_c)" + "values(#{colA},#{colB},#{colC})")
    boolean insert(@Param("colA") String colA,
                   @Param("colB") boolean colB,
                   @Param("colC") Timestamp colC);


    /**
     * update specific id
     *
     * @param id   id
     * @param colA colA
     * @param colB colB
     * @param colC colC
     * @return update result
     */
    @Update("update demo col_a = #{colA}, col_b = #{colB}, col_c = #{colC} where id = #{id}")
    boolean updateById(@Param("id") long id,
                       @Param("colA") String colA,
                       @Param("colB") boolean colB,
                       @Param("colC") Timestamp colC);


    /**
     * delete
     *
     * @param id id
     * @return delete result
     */
    @Delete("delete from demo where where id = #{id}")
    boolean deleteById(@Param("id") long id);

}
