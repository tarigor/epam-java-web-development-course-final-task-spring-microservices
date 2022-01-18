package com.epam.commonuserservice.types;

/**
 * Provides an ENUMs of the available user's typeis.
 */
public enum UserType {
    ADMIN("Administrator"),
    CLIENT("Client");
    String userDescription;

    UserType() {
    }

    UserType(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserDescription() {
        return userDescription;
    }
}
