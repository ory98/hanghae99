package kr.hanghae.deploy.dto.product.service.response;

import kr.hanghae.deploy.domain.product.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ProductBestResponse {

    private List<Product> productList = new ArrayList<>();

    public ProductBestResponse(List<Product> productList) {
        this.productList = productList;
    }

    public static ProductBestResponse of(List<Product> responseList) {
        return new ProductBestResponse(responseList);
    }

}
