package fpt.edu.shopping.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private List<Data> orders;

    @Getter
    @Setter
    @Builder
    public static class Data {
        private Long productId;
        private Long quantity;
    }
}
