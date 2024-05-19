package com.ohgiraffers.dosirak.admin.order.controller;

import com.ohgiraffers.dosirak.admin.order.model.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/*")
public class OrderRestController {
    Map<String, Object> response = new HashMap<>();

    @PostMapping("admin/orderStatus/updateStatus")
    public ResponseEntity<Map<String, Object>> orderListSelected(@RequestBody OrderDTO status) {
        try {
            response.put("message", "주문상태 변경 성공");
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "주문상태 변경 실패");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
