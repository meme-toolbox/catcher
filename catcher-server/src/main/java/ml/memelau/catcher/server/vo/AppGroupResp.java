package ml.memelau.catcher.server.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AppGroupResp {

    private Integer id;

    private String accessKey;

    private Date createTime;

    private String groupName;

    private Date updateTime;

    private Boolean deleted;

    private Rule rule;

}
