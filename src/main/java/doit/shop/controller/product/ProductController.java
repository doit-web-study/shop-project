package doit.shop.controller.product;

import doit.shop.controller.ListWrapper;
import doit.shop.controller.product.dto.ProductIdResponse;
import doit.shop.controller.product.dto.ProductInfoResponse;
import doit.shop.controller.product.dto.ProductRegisterRequest;
import doit.shop.controller.product.dto.ProductUpdateRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController implements ProductControllerDocs {

    @PostMapping
    public ProductIdResponse registerProduct(@RequestBody ProductRegisterRequest productRegisterRequest) {
        return null;
    }

    @PutMapping("/{productId}")
    public ProductIdResponse updateProduct(@PathVariable Long productId,
                                           @RequestBody ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {

    }

    @GetMapping("/{productId}")
    public ProductInfoResponse getProduct(@PathVariable Long productId) {
        return null;
    }

    @GetMapping
    public ListWrapper<ProductInfoResponse> getProductList(@RequestParam(defaultValue = "0") Long pageNumber,
                                                           @RequestParam(required = false) String keyword,
                                                           @RequestParam(required = false) Long categoryId) {
        return null;
    }
}
