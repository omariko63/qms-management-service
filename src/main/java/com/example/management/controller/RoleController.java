package com.example.management.controller;

import common.dto.RoleDto;
import com.example.management.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody RoleDto dto) {
        Map<String, Object> response = roleService.create(dto);
        boolean success = (boolean) response.get("success");
        return ResponseEntity
                .status(success ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = roleService.findAll();
        boolean success = (boolean) response.get("success");
        return ResponseEntity
                .status(success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        Map<String, Object> response = roleService.findById(id);
        boolean success = (boolean) response.get("success");
        return ResponseEntity
                .status(success ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody RoleDto dto) {
        Map<String, Object> response = roleService.update(id, dto);
        boolean success = (boolean) response.get("success");
        return ResponseEntity
                .status(success ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> response = roleService.delete(id);
        boolean success = (boolean) response.get("success");
        return ResponseEntity
                .status(success ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(response);
    }
}
