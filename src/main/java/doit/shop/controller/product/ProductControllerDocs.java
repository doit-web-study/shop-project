package doit.shop.controller.product;

import doit.shop.controller.ListWrapper;
import doit.shop.controller.product.dto.ProductIdResponse;
import doit.shop.controller.product.dto.ProductInfoResponse;
import doit.shop.controller.product.dto.ProductRegisterRequest;
import doit.shop.controller.product.dto.ProductUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "상품 관련 API")
public interface ProductControllerDocs {

    @Operation(summary = "상품 등록", description = "상품을 등록한다.")
    @ApiResponse(responseCode = "200", description = "상품 등록 성공")
    @ApiResponse(responseCode = "400", description = "상품 등록 실패")
    ProductIdResponse registerProduct(
            @Schema(description = "상품 등록 정보", implementation = ProductRegisterRequest.class)
            ProductRegisterRequest productRegisterRequest);

    @Operation(summary = "상품 수정", description = "상품을 수정한다.")
    @ApiResponse(responseCode = "200", description = "상품 수정 성공")
    @ApiResponse(responseCode = "400", description = "상품 수정 실패")
    ProductIdResponse updateProduct(
            @Schema(description = "상품 식별 ID", example = "1")
            Long productId,
            @Schema(description = "상품 수정 정보", implementation = ProductUpdateRequest.class)
            ProductUpdateRequest productUpdateRequest
    );

    @Operation(summary = "상품 삭제", description = "상품을 삭제한다.")
    @ApiResponse(responseCode = "200", description = "상품 삭제 성공")
    @ApiResponse(responseCode = "400", description = "상품 삭제 실패")
    void deleteProduct(
            @Schema(description = "상품 식별 ID", example = "1")
            Long productId
    );

    @Operation(summary = "상품 단건 조회", description = "상품을 조회한다.")
    @ApiResponse(responseCode = "200", description = "상품 조회 성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "상품 조회 실패")
    ProductInfoResponse getProduct(
            @Schema(description = "상품 식별 ID", example = "1")
            Long productId
    );

    @Operation(summary = "상품 리스트 조회", description = "상품 리스트를 조회한다.")
    @ApiResponse(responseCode = "200", description = "상품 리스트 조회 성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "상품 리스트 조회 실패")
    ListWrapper<ProductInfoResponse> getProductList(
            @Schema(description = "페이지 번호", example = "1")
            Long pageNumber,
            @Schema(description = "검색어", example = "빼빼로")
            String keyword,
            @Schema(description = "카테고리 식별 ID", example = "1")
            Long categoryId
    );
}
