package com.epam.apigatewayui.service.impl;

import com.epam.apigatewayui.service.ICommandService;
import com.epam.apigatewayui.utility.JsonFileHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;

@Component
public class CommandServiceImpl implements ICommandService {
    public static final String JSON = "/factory/command.json";
    public static final String ROLE = "role";
    private final JsonFileHandler jsonFileHandler;

    public CommandServiceImpl(@Value(value = JSON) JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
    }

    /**
     * Methods provides a getting of the role type from json file of the specific command.
     *
     * @param command String name of the command.
     * @return a role of the specific command.
     */
    @Override
    public String getCommandRole(String command) throws IOException {

        return ((LinkedHashMap) jsonFileHandler.getMapFromJson().get(command)).get(ROLE).toString();
    }
}
