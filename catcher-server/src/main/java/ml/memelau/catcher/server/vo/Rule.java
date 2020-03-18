package ml.memelau.catcher.server.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@ApiModel("报警规则")
@Data
public class Rule {


    private List<@Valid SendLevel> sendLevels;


}
