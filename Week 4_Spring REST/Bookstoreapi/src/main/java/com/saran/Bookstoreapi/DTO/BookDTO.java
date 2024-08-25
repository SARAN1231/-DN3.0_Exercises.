package com.saran.Bookstoreapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class BookDTO extends RepresentationModel<BookDTO> { // since using Hateoas
    private String title;
    private String author;
    @JsonProperty("Cost") // for changing the  field name if needed
    private Integer price;
}
