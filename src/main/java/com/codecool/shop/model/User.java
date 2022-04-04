package com.codecool.shop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class User {

    private UUID id = UUID.randomUUID();
    private String name;
    private String email;
    private String password;
    private Role role;
}
