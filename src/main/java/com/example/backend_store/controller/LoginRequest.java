package com.example.backend_store.controller;

public record LoginRequest(
        String username,
        String password) {
    // This record is used to represent the login request payload.
    // It contains two fields: username and password.
    // The record automatically generates the constructor, getters, equals, hashCode, and toString methods.
}
