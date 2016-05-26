package com.doerksen.combinatrix.resources.impl;

import com.doerksen.combinatrix.dto.UserDto;
import com.doerksen.combinatrix.resources.UserResource;
import com.doerksen.elasticsearch_service.resources.ElasticResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class UserResourceImpl implements UserResource {

    // Dropwizard by default uses Logback behind the scenes, https://dropwizard.github.io/dropwizard/0.6.2/manual/core.html#logging
    private final Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);

    ElasticResource resource;

    public UserResourceImpl(ElasticResource resource) {
        this.resource = resource;
    }

    public UserDto getUser(long id) {
        // GetResponse response = client.prepareGet("users", "user", "1").get();
        // return new UserDto((long)response.getField("id").getValue(), response.getField("user").getValue().toString(), "LastName", "fakeEmail@fakeMail.com");
        Map<String, Object> fields = null;
        try {
            fields = resource.getDocument("users", "user", String.valueOf(id));
        } catch (Exception e) {
            logger.error("test", e);
        }
        return new UserDto(Long.valueOf(fields.get("id").toString()),
                fields.get("firstName").toString(),
                fields.get("lastName").toString(),
                fields.get("email").toString());
    }
}