package com.example.sakila.dto;

import jakarta.validation.groups.Default;

public class ValidationGroup {
    public interface Create extends Default {}
    public interface Delete extends Default {}

}
