package base.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PaymentResponse implements Serializable {

    private Long id;
    @JsonProperty("token")
    private String token;
    private String siteId;
    private Long time;
    private Double amount;


}

