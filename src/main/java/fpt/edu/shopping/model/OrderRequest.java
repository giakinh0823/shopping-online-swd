package fpt.edu.shopping.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
