package ml.memelau.catcher.server.mapper;

import java.util.List;
import ml.memelau.catcher.server.model.ErrorEventCount;
import ml.memelau.catcher.server.model.ErrorEventCountExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface ErrorEventCountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    long countByExample(ErrorEventCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int insert(ErrorEventCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int insertSelective(ErrorEventCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    List<ErrorEventCount> selectByExampleWithBLOBsWithRowbounds(ErrorEventCountExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    List<ErrorEventCount> selectByExampleWithBLOBs(ErrorEventCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    List<ErrorEventCount> selectByExampleWithRowbounds(ErrorEventCountExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    List<ErrorEventCount> selectByExample(ErrorEventCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    ErrorEventCount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ErrorEventCount record, @Param("example") ErrorEventCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") ErrorEventCount record, @Param("example") ErrorEventCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ErrorEventCount record, @Param("example") ErrorEventCountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ErrorEventCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(ErrorEventCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_count
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ErrorEventCount record);
}