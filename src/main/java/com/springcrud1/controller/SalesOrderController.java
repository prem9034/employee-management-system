package com.springcrud1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springcrud1.entity.SalesOrder;
import com.springcrud1.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/sales-orders")
public class SalesOrderController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SalesOrderService service;

    @PostMapping(value = "/with-file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SalesOrder createOrderWithFile(
            @RequestPart("order") String orderJson,
            @RequestPart("file") MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SalesOrder order = objectMapper.readValue(orderJson, SalesOrder.class);

        return service.createOrderWithFile(order, file);
    }

    @GetMapping
    public List<SalesOrder> getAll() {
        return service.getAllOrders();
    }
}


