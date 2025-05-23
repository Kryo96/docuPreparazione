package com.leasing.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String model;
    private String organization;
    private String agency;

    @JsonProperty("contact_info")
    private String contactInfo;

}
