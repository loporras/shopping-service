package academy.digitallab.store.shopping.messages;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder

public class ErrorMessages {
    private String code;
    private List<Map<String, String>> messages;

}
