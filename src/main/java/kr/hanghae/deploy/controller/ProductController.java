package kr.hanghae.deploy.controller;

import kr.hanghae.deploy.dto.ApiResponse;
import kr.hanghae.deploy.dto.product.service.response.ProductBestResponse;
import kr.hanghae.deploy.dto.product.service.response.ProductResponse;
import kr.hanghae.deploy.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public ApiResponse<List<ProductResponse>> getProductList() {
        return ApiResponse.ok(productService.getProductList());
    }

    @GetMapping("/product/best")
    public ApiResponse<ProductBestResponse> getProductBest() {
        return ApiResponse.ok(productService.getProductBest());
    }

}
