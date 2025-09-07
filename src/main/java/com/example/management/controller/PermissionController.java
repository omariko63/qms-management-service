package com.example.management.controller;

import com.example.management.dto.PermissionDTO;
import com.example.management.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPermissions() {
        return ResponseEntity.ok(
                Map.of(
                        "data", permissionService.getAllPermissions()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPermissionById(@PathVariable Integer id) {
        PermissionDTO dto = permissionService.getPermissionById(id);
        if (dto != null) {
            return ResponseEntity.ok(
                    Map.of(
                            "timestamp", LocalDateTime.now(),
                            "status", HttpStatus.OK.value(),
                            "message", "Permission fetched successfully",
                            "data", dto
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "message", "Permission with ID " + id + " not found"
                )
        );
    }

//    @PostMapping
//    public ResponseEntity<Map<String, Object>> createPermission(@RequestBody PermissionDTO dto) {
//        PermissionDTO created = permissionService.createPermission(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                Map.of(
//                        "status", HttpStatus.CREATED.value(),
//                        "message", "Permission created successfully"
//                )
//        );
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> updatePermission(@PathVariable Integer id, @RequestBody PermissionDTO dto) {
//        PermissionDTO updated = permissionService.updatePermission(id, dto);
//        if (updated != null) {
//            return ResponseEntity.ok(
//                    Map.of(
//                            "status", HttpStatus.OK.value(),
//                            "message", "Permission updated successfully"
//                    )
//            );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                Map.of(
//                        "timestamp", LocalDateTime.now(),
//                        "status", HttpStatus.NOT_FOUND.value(),
//                        "message", "Permission with ID " + id + " not found"
//                )
//        );
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> deletePermission(@PathVariable Integer id) {
//        if (permissionService.deletePermission(id)) {
//            return ResponseEntity.ok(
//                    Map.of(
//                            "timestamp", LocalDateTime.now(),
//                            "status", HttpStatus.OK.value(),
//                            "message", "Permission deleted successfully",
//                            "id", id
//                    )
//            );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                Map.of(
//                        "timestamp", LocalDateTime.now(),
//                        "status", HttpStatus.NOT_FOUND.value(),
//                        "message", "Permission with ID " + id + " not found"
//                )
//        );
//    }
}
