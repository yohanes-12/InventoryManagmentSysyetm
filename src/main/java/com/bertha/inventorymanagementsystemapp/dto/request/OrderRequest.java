package com.bertha.inventorymanagementsystemapp.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderRequest(
        LocalDate orderDate,
        Integer orderQuality,
        BigDecimal price,
        List<Long> productId,
        Long customerId
) {
}
