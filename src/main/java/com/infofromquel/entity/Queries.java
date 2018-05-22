package com.infofromquel.entity;

/**
 * Enum for Queries
 * @author Serhii Zhuravlov
 */
public enum Queries {

    FIND_ALL_USERS_HQL ("FROM User");


    private String querie;

    Queries(String querie) {
        this.querie = querie;
    }

    public String getQuerie() {
       return querie;
    }
}
