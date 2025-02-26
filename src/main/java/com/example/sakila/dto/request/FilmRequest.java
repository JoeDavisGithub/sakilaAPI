package com.example.sakila.dto.request;


import com.example.sakila.dto.ValidationGroup;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;
import java.util.List;

import static com.example.sakila.dto.ValidationGroup.*;

@AllArgsConstructor
@Getter
public class FilmRequest {

    @NotNull(groups={Create.class})
    @Size(min = 1, max = 128)
    private final String title;

    @Size(min = 1, max = 350)
    private final String description;

    @Min(1901)
    @Max(2155)
    private final Short release_year;

    @NotNull(groups={Create.class})
    private final Byte language_id;

    @NotNull(groups={Create.class})
    @Min(1)
    @Max(127)
    private final Byte rental_duration;

    @NotNull(groups={Create.class})
    @Digits(integer=2,fraction=2)
    @DecimalMin("0.009")
    private final Float rental_rate;

    private final Short length;

    @NotNull(groups={Create.class})
    @Digits(integer=3,fraction=2)
    @DecimalMin("0.009")
    private final Float replacement_cost;

    @Pattern(regexp = "^(G|PG|PG-13|R|NC-17)$",message="Not a valid Special Feature")
    private final String rating;

    //@Pattern(regexp = "^(Trailers|Commentaries|Deleted Scenes|Behind the Scenes)$",message="Not a valid Special Feature")
    private final String special_features;

    private final List<Short> actorIDs;

}
