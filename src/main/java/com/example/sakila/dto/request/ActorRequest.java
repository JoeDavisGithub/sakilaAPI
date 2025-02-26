package com.example.sakila.dto.request;


import com.example.sakila.dto.ValidationGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.example.sakila.dto.ValidationGroup.*;

@AllArgsConstructor
@Getter
public class ActorRequest {

    @NotNull(groups={Create.class})
    @Size(min = 1, max = 45)
    private final String firstName;

    @NotNull(groups={Create.class})
    @Size(min = 1, max = 45)
    private final String lastName;

    @NotNull(groups={Create.class})
    private final List<Short> filmIds;
}
