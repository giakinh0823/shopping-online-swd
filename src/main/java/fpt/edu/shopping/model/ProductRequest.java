package fpt.edu.shopping.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private Long id;
    private String name;
    private Long quantity;
    private String type;
    private String description;
    private Long price;
}
