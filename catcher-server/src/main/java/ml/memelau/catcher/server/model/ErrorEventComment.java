package ml.memelau.catcher.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table error_event_comment
 */
public class ErrorEventComment implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_comment.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_comment.error_event_id
     *
     * @mbg.generated
     */
    private Integer errorEventId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_comment.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   备注内容（markdown）
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_comment.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_comment.id
     *
     * @return the value of error_event_comment.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    public ErrorEventComment withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_comment.id
     *
     * @param id the value for error_event_comment.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_comment.error_event_id
     *
     * @return the value of error_event_comment.error_event_id
     *
     * @mbg.generated
     */
    public Integer getErrorEventId() {
        return errorEventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    public ErrorEventComment withErrorEventId(Integer errorEventId) {
        this.setErrorEventId(errorEventId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_comment.error_event_id
     *
     * @param errorEventId the value for error_event_comment.error_event_id
     *
     * @mbg.generated
     */
    public void setErrorEventId(Integer errorEventId) {
        this.errorEventId = errorEventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_comment.create_time
     *
     * @return the value of error_event_comment.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    public ErrorEventComment withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_comment.create_time
     *
     * @param createTime the value for error_event_comment.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_comment.content
     *
     * @return the value of error_event_comment.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    public ErrorEventComment withContent(String content) {
        this.setContent(content);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_comment.content
     *
     * @param content the value for error_event_comment.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ErrorEventComment other = (ErrorEventComment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getErrorEventId() == null ? other.getErrorEventId() == null : this.getErrorEventId().equals(other.getErrorEventId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getErrorEventId() == null) ? 0 : getErrorEventId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_comment
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", errorEventId=").append(errorEventId);
        sb.append(", createTime=").append(createTime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}