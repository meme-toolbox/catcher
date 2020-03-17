package ml.memelau.catcher.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table error_event_log
 */
public class ErrorEventLog implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_log.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_log.event_id
     *
     * @mbg.generated
     */
    private Integer eventId;

    /**
     * Database Column Remarks:
     *   处理时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_log.handling_time
     *
     * @mbg.generated
     */
    private Date handlingTime;

    /**
     * Database Column Remarks:
     *   处理人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_log.handler_id
     *
     * @mbg.generated
     */
    private Integer handlerId;

    /**
     * Database Column Remarks:
     *   日志详情
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_event_log.detail
     *
     * @mbg.generated
     */
    private String detail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table error_event_log
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_log.id
     *
     * @return the value of error_event_log.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
     *
     * @mbg.generated
     */
    public ErrorEventLog withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_log.id
     *
     * @param id the value for error_event_log.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_log.event_id
     *
     * @return the value of error_event_log.event_id
     *
     * @mbg.generated
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
     *
     * @mbg.generated
     */
    public ErrorEventLog withEventId(Integer eventId) {
        this.setEventId(eventId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_log.event_id
     *
     * @param eventId the value for error_event_log.event_id
     *
     * @mbg.generated
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_log.handling_time
     *
     * @return the value of error_event_log.handling_time
     *
     * @mbg.generated
     */
    public Date getHandlingTime() {
        return handlingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
     *
     * @mbg.generated
     */
    public ErrorEventLog withHandlingTime(Date handlingTime) {
        this.setHandlingTime(handlingTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_log.handling_time
     *
     * @param handlingTime the value for error_event_log.handling_time
     *
     * @mbg.generated
     */
    public void setHandlingTime(Date handlingTime) {
        this.handlingTime = handlingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_log.handler_id
     *
     * @return the value of error_event_log.handler_id
     *
     * @mbg.generated
     */
    public Integer getHandlerId() {
        return handlerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
     *
     * @mbg.generated
     */
    public ErrorEventLog withHandlerId(Integer handlerId) {
        this.setHandlerId(handlerId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_log.handler_id
     *
     * @param handlerId the value for error_event_log.handler_id
     *
     * @mbg.generated
     */
    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_event_log.detail
     *
     * @return the value of error_event_log.detail
     *
     * @mbg.generated
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
     *
     * @mbg.generated
     */
    public ErrorEventLog withDetail(String detail) {
        this.setDetail(detail);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_event_log.detail
     *
     * @param detail the value for error_event_log.detail
     *
     * @mbg.generated
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
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
        ErrorEventLog other = (ErrorEventLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEventId() == null ? other.getEventId() == null : this.getEventId().equals(other.getEventId()))
            && (this.getHandlingTime() == null ? other.getHandlingTime() == null : this.getHandlingTime().equals(other.getHandlingTime()))
            && (this.getHandlerId() == null ? other.getHandlerId() == null : this.getHandlerId().equals(other.getHandlerId()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEventId() == null) ? 0 : getEventId().hashCode());
        result = prime * result + ((getHandlingTime() == null) ? 0 : getHandlingTime().hashCode());
        result = prime * result + ((getHandlerId() == null) ? 0 : getHandlerId().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_event_log
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
        sb.append(", eventId=").append(eventId);
        sb.append(", handlingTime=").append(handlingTime);
        sb.append(", handlerId=").append(handlerId);
        sb.append(", detail=").append(detail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}