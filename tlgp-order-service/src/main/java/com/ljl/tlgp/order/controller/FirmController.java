package com.ljl.tlgp.order.controller;

import com.ljl.tlgp.order.entity.Firm;
import com.ljl.tlgp.order.service.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// src/main/java/com/example/demo/controller/FirmController.java
@RestController
@RequestMapping("/firms")
public class FirmController {
    private final FirmService firmService;

    @Autowired
    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    // 获取所有企业
    @GetMapping("list")
    public ResponseEntity<List<Firm>> getAllFirms() {
        return ResponseEntity.ok(firmService.getAllFirms());
    }

    // 获取所有企业详细信息
    @GetMapping("listInfo")
    public ResponseEntity<List<Firm>> getAllFirmsInfo() {
        return ResponseEntity.ok(firmService.findFirmWithInfo());
    }


    // 创建企业
    @PostMapping("create")
    public ResponseEntity<Firm> createFirm(@RequestBody Firm firm) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(firmService.createFirm(firm));
    }

    // 更新企业
    @PutMapping("update/{id}")
    public ResponseEntity<Firm> updateFirm(
            @PathVariable Long id,
            @RequestBody Firm firmDetails) {
        return ResponseEntity.ok(firmService.updateFirm(id, firmDetails));
    }

    // 删除企业
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteFirm(@PathVariable Long id) {
        try {
            firmService.deleteFirm(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "公司删除成功");
            response.put("deletedId", id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "删除失败: " + e.getMessage());
            response.put("errorId", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
