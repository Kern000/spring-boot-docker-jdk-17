package com.kern000.coders_practice.user;

public record Address (
    String street,
    String suite,
    String city,
    String zipcode,
    Geo geo
){
    
}
