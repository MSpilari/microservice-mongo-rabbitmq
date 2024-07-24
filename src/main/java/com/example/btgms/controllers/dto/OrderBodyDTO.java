package com.example.btgms.controllers.dto;

import java.util.List;

public record OrderBodyDTO(Long orderId, Long customerId, List<OrderBodyItemDTO> items) {

}
