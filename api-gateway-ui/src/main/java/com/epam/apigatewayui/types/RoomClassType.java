package com.epam.apigatewayui.types;

/**
 * Provides an ENUMs of the available room's classes.
 */
public enum RoomClassType {
    SINGLE("single"),
    DOUBLE("double"),
    SUITE("suite"),
    DELUXE("deluxe");
    String description;

    RoomClassType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
