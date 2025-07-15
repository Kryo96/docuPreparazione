package org.example.config;

import org.example.config.model.SalesforceConnectionDescriptor;
import org.example.config.model.AS400ConnectionDescriptor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigFile {

    @JsonProperty("serverNames")
    private List<String> serverName = new ArrayList<>();

    @JsonProperty("as400Connection")
    private AS400ConnectionDescriptor as400Connection;

    @JsonProperty("salesforceConnection")
    private SalesforceConnectionDescriptor salesforceConnection;
}

