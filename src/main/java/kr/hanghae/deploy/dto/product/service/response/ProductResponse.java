package kr.hanghae.deploy.dto.product.service.response;

import kr.hanghae.deploy.domain.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {

    private String productNumber;
    private String name;
    private Long price;
    private Long stock;

    @Builder
    public ProductResponse(String productNumber, String name, Long price, Long stock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .productNumber(product.getProductNumber())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
