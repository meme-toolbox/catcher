package ml.memelau.catcher.server.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SendLevel {

    @NotNull
    private Long count;

    @NotEmpty
    private List<String> mailAddresses;

}
