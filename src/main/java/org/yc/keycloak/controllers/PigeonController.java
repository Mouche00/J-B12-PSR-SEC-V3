package org.yc.keycloak.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.yc.keycloak.dtos.PigeonDTO;
import org.yc.keycloak.services.PigeonService;
import org.yc.keycloak.utils.ApiResponse;
import org.yc.keycloak.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pigeons")
public class PigeonController {
    private final PigeonService pigeonService;

    @Autowired
    public PigeonController(PigeonService pigeonService) {
        this.pigeonService = pigeonService;
    }

    @PostMapping("save")
    public ResponseEntity<ApiResponse<PigeonDTO>> save(@RequestBody @Valid PigeonDTO pigeonDTO, HttpServletRequest request) {
        String breederId = (String) request.getSession().getAttribute("breederId");
        PigeonDTO createdPigeon = pigeonService.save(pigeonDTO.withBreederId(breederId));
        return ResponseEntity.ok(ResponseUtil.success(createdPigeon, "Pigeon saved successfully", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PigeonDTO>>> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(pigeonService.findAll(), "Pigeons retrieved successfully", request.getRequestURI()));
    }

    @PostMapping("save-all")
    public ResponseEntity<ApiResponse<List<PigeonDTO>>> saveAll(@RequestBody List<PigeonDTO> pigeonDTOs, HttpServletRequest request) {
        String breederId = (String) request.getSession().getAttribute("breederId");
        pigeonDTOs = pigeonDTOs.stream().map(pigeonDTO -> pigeonDTO.withBreederId(breederId)).toList();
        return ResponseEntity.ok(ResponseUtil.success(pigeonService.saveAll(pigeonDTOs), "Pigeons saved successfully", request.getRequestURI()));
    }
}
