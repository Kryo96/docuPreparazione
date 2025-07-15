package org.example.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.config.model.AS400ConnectionDescriptor;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigFile {

    @JsonProperty("serverNames")
    private List<String> serverName = new ArrayList<>();

    @JsonProperty("as400Connection")
    private AS400ConnectionDescriptor as400Connection;
}
