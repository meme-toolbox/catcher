package ml.memelau.catcher.server.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("报警级别")
@Data
public class SendLevel {

    @ApiModelProperty("次数")
    @NotNull
    private Long count;

    @ApiModelProperty("邮件地址")
    @NotEmpty
    private List<String> mailAddresses;

}
