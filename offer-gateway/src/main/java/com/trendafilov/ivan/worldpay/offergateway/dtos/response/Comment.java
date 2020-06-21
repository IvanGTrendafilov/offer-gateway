package com.trendafilov.ivan.worldpay.offergateway.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    private Long commentId;
    private String comment;
    private String userName;
    private Date dateCreated;
    private Long offerId;
}
