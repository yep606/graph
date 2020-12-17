package base.service;

import base.util.PaymentResponse;
import base.util.RestTemplateResponseErrorHandler;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Value("${STATUS_PAGE_URL}")
    private String STATUS_PAGE_URL;
    private final RestTemplate restTemplate;

    @Autowired
    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    public PaymentResponse getPaymentInfo(Long userId) {
        ResponseEntity<String> s =  this.restTemplate.getForEntity(STATUS_PAGE_URL, String.class, userId);
        if(s.getStatusCode() == HttpStatus.OK){
            Gson gson = new Gson();
            System.out.println("success");
            System.out.println(s);
            PaymentResponse[] paymentResponses = gson.fromJson(s.getBody(), PaymentResponse[].class);

           return paymentResponses[0];
        }
        return null;
    }
}


