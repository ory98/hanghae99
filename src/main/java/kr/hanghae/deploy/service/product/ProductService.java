package kr.hanghae.deploy.service.product;

import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.domain.product.ProductRepository;
import kr.hanghae.deploy.dto.product.service.response.ProductBestResponse;
import kr.hanghae.deploy.dto.product.service.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> getProductList() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::of).collect(Collectors.toList());
    }

    public ProductBestResponse getProductBest() {
        return ProductBestResponse.of(null);
    }
}
