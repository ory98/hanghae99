package kr.hanghae.deploy.service.product;

import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.domain.orderproduct.OrderProductRepository;
import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.domain.product.ProductRepository;
import kr.hanghae.deploy.dto.product.service.response.ProductBestResponse;
import kr.hanghae.deploy.dto.product.service.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductList() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::of).collect(Collectors.toList());
    }

    public ProductBestResponse getProductBest() {

        // 조회 시 - 오늘 00시에 오늘 날짜 기준으로 4일전 기록 다 가져오기 > where 절 넣기
         List<OrderProduct> orderProductList = orderProductRepository.findAll();

         // orderby

        return ProductBestResponse.of(null);
    }
}
