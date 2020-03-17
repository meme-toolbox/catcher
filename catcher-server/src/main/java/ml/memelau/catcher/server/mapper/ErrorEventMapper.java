package ml.memelau.catcher.server.mapper;

import java.util.List;
import ml.memelau.catcher.server.model.ErrorEvent;
import ml.memelau.catcher.server.model.ErrorEventExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface ErrorEventMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    long countByExample(ErrorEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int insert(ErrorEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int insertSelective(ErrorEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    List<ErrorEvent> selectByExampleWithBLOBsWithRowbounds(ErrorEventExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    List<ErrorEvent> selectByExampleWithBLOBs(ErrorEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    List<ErrorEvent> selectByExampleWithRowbounds(ErrorEventExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    List<ErrorEvent> selectByExample(ErrorEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    ErrorEvent selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ErrorEvent record, @Param("example") ErrorEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") ErrorEvent record, @Param("example") ErrorEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ErrorEvent record, @Param("example") ErrorEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ErrorEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(ErrorEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ErrorEvent record);
}