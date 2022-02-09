package com.epam.apigatewayui.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class JsonFileHandler {

    private final String jsonPath;

    public JsonFileHandler(@Value("jsonPath") String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public HashMap<String, Object> getMapFromJson() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //       return objectMapper.readValue(new FileInputStream(jsonPath), HashMap.class);
        return objectMapper.readValue(new ClassPathResource(jsonPath).getInputStream(), HashMap.class);
    }
}