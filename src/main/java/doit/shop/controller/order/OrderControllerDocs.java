package doit.shop.controller.order;

import doit.shop.controller.PageResponse;
import doit.shop.controller.order.dto.OrderRegisterRequest;
import doit.shop.controller.order.dto.OrderTotalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order", description = "주문 관련 API")
public interface OrderControllerDocs {

    @Operation(summary = "주문 등록", description = "주문을 등록한다.")
    @ApiResponse(responseCode = "200", description = "주문 등록 성공")
    @ApiResponse(responseCode = "400", description = "주문 등록 실패")
    void registerOrder(
            @Schema(description = "주문 등록 정보", implementation = OrderRegisterRequest.class)
            OrderRegisterRequest orderRegisterRequest
    );

    @Operation(summary = "주문 취소", description = "주문을 취소한다.")
    @ApiResponse(responseCode = "200", description = "주문 취소 성공")
    @ApiResponse(responseCode = "400", description = "주문 취소 실패")
    void cancelOrder(
            @Schema(description = "주문 식별 ID", example = "1")
            Long orderId
    );

    @Operation(summary = "주문 단건 조회", description = "주문을 조회한다.")
    @ApiResponse(responseCode = "200", description = "주문 조회 성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "주문 조회 실패")
    OrderTotalResponse getOrder(
            @Schema(description = "주문 식별 ID", example = "1")
            Long orderId
    );

    @Operation(summary = "주문 리스트 조회", description = "내 주문 리스트를 조회한다.")
    @ApiResponse(responseCode = "200", description = "주문 리스트 조회 성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "주문 리스트 조회 실패")
    PageResponse<OrderTotalResponse> getOrderList(
            @Schema(description = "페이지 번호", example = "0")
            Integer pageNumber
    );

}
