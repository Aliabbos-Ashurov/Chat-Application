package com.pdp.backend.DTO;

/**
 * The LoginDTO class represents a Data Transfer Object (DTO) for user login information.
 * It contains the username and password of a user attempting to log in.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public record  LoginDTO(String username, String password){}
