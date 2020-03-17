package ml.memelau.catcher.server.vo;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class Rule {


    private List<@Valid SendLevel> sendLevels;


}
