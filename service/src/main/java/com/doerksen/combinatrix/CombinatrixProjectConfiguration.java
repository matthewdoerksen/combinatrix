package com.doerksen.combinatrix;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CombinatrixProjectConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("elasticHost")
    private String elasticHost;

    @Valid
    @NotNull
    @JsonProperty("elasticPort")
    private String elasticPort;

    public String getElasticHost() {
        return elasticHost;
    }

    public String getElasticPort() {
        return elasticPort;
    }
}
