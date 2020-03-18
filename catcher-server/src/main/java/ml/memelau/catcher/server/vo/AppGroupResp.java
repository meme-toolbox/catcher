package ml.memelau.catcher.server.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "应用组")
@Data
public class AppGroupResp {

    @ApiModelProperty
    private Integer id;

    @ApiModelProperty("accessKey")
    private String accessKey;

    private Date createTime;

    @ApiModelProperty("应用组名称")
    private String groupName;

    private Date updateTime;

    private Boolean deleted;

    @ApiModelProperty("报警规则")
    private Rule rule;

}
