package ml.memelau.catcher.server.vo;

import lombok.Data;

import java.util.List;

@Data
public class SendLevel {

    private Long count;

    private List<String> mailAddresses;

}
