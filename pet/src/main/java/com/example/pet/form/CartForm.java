package com.example.pet.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartForm {

    @NotNull(message = "상품 아이디는 필수입니다.")
    private Long itemNum;

    @Min(value=1, message = "최소 주문 수량은 1개입니다.")
    @Max(value=999, message = "최대 주문 수량은 999개입니다.")
    private int count;
}
