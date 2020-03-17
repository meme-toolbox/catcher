package ml.memelau.catcher.server.mapper;

import java.util.List;
import ml.memelau.catcher.server.model.ErrorEventComment;
import ml.memelau.catcher.server.model.ErrorEventCommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface ErrorEventCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    long countByExample(ErrorEventCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int insert(ErrorEventComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int insertSelective(ErrorEventComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    List<ErrorEventComment> selectByExampleWithBLOBsWithRowbounds(ErrorEventCommentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    List<ErrorEventComment> selectByExampleWithBLOBs(ErrorEventCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    List<ErrorEventComment> selectByExampleWithRowbounds(ErrorEventCommentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    List<ErrorEventComment> selectByExample(ErrorEventCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    ErrorEventComment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ErrorEventComment record, @Param("example") ErrorEventCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") ErrorEventComment record, @Param("example") ErrorEventCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ErrorEventComment record, @Param("example") ErrorEventCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ErrorEventComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(ErrorEventComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ErrorEventComment record);
}