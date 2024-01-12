package kr.hanghae.deploy.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProductInfo {
    private String productNumber;
    private Long stock;

    public OrderProductInfo(String productNumber, Long stock) {
        this.productNumber = productNumber;
        this.stock = stock;
    }
}