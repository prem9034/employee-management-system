package com.springcrud1.service;

import com.springcrud1.entity.SalesOrder;
import com.springcrud1.entity.SalesOrderItem;
import com.springcrud1.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderRepository repo;

    public SalesOrder createOrderWithFile(SalesOrder order, MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            order.setOrderNumber(generateOrderNumber());
            order.setOrderDate(LocalDate.now());
            order.setStatus("CREATED");

            // Save file to local folder
            String uploadDir = "C:/UploadFile/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String filePath = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            String fileName = System.currentTimeMillis() + "_" + filePath;

            file.transferTo(new File(filePath));

            // Save file info in DB
            order.setFileName(fileName);
            order.setFilePath(filePath);

            // Set parent reference
            for (SalesOrderItem item : order.getItems()) {
                item.setSalesOrder(order);
            }

            return repo.save(order);

        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
    public String generateOrderNumber() {

        int year = LocalDate.now().getYear();

        long count = repo.countByYear(year) + 1;

        // Format: SO/2026/01
        return String.format("SO/%d/%02d", year, count);
    }
    public List<SalesOrder> getAllOrders() {
        return repo.findAll();
    }


}
