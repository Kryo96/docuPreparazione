package org.example.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sforce.ws.ConnectorConfig;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class SalesforceConnectionDescriptor {

    private static final Logger log = LoggerFactory.getLogger(SalesforceConnectionDescriptor.class);

    @JsonProperty("name")
    private String name;

    @JsonProperty("loginUrl")
    private String loginUrl = "https://login.salesforce.com";

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("clientSecret")
    private String clientSecret;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("securityToken")
    private String securityToken;

    @JsonProperty("apiVersion")
    private String apiVersion = "59.0";

    @JsonProperty("connectionTimeout")
    private int connectionTimeout = 60000;

    @JsonProperty("readTimeout")
    private int readTimeout = 120000;

    @JsonProperty("proxyHost")
    private String proxyHost;

    @JsonProperty("proxyPort")
    private Integer proxyPort;

    @JsonProperty("proxyUsername")
    private String proxyUsername;

    @JsonProperty("proxyPassword")
    private String proxyPassword;

    @JsonProperty("compression")
    private boolean compression = true;

    @JsonProperty("traceMessage")
    private boolean traceMessage = false;

    @JsonProperty("prettyPrintXml")
    private boolean prettyPrintXml = false;


    /**
     * Crea una ConnectorConfig per questa connesione Saleforce
     */
    public ConnectorConfig createConnectorConfig() {
        log.debug("Creating Salesforce connector config for: {}", name);

        ConnectorConfig config = new ConnectorConfig();

        // URL e versione API
        config.setAuthEndpoint(loginUrl + "/services/Soap/u/" + apiVersion);
        config.setServiceEndpoint(loginUrl + "/services/Soap/u/" + apiVersion);
        //config.setRestEndpoint(loginUrl + "/services/data/v" + apiVersion);

        // Credenziali
        config.setUsername(username);

        // Password + security token se fornito
        String fullPassword = password;
        if (securityToken != null && !securityToken.isEmpty()) {
            fullPassword = password + securityToken;
        }
        config.setPassword(fullPassword);

        // Timeout
        config.setConnectionTimeout(connectionTimeout);
        config.setReadTimeout(readTimeout);

        // Proxy se configurato
        if (proxyHost != null && !proxyHost.isEmpty() && proxyPort != null) {
            config.setProxy(proxyHost, proxyPort);
            if (proxyUsername != null && !proxyUsername.isEmpty()) {
                config.setProxyUsername(proxyUsername);
                config.setProxyPassword(proxyPassword);
            }
        }

        // Altre opzioni
        config.setCompression(compression);
        config.setTraceMessage(traceMessage);
        config.setPrettyPrintXml(prettyPrintXml);

        // Abilita il session ID per le chiamate REST
//        config.setSessionRenewer((connectorConfig, sessionRenewalHeader) -> {
//            log.debug("Session renewal requested for: {}", name);
//            // Il rinnovo viene gestito automaticamente dal framework
//        });

        log.debug("Salesforce connector config created for: {}", name);

        return config;
    }

}
