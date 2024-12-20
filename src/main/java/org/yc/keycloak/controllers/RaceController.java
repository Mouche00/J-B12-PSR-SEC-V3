package org.yc.keycloak.controllers;

import jakarta.servlet.http.HttpServletRequest;

import org.yc.keycloak.dtos.RaceDTO;
import org.yc.keycloak.services.RaceService;
import org.yc.keycloak.utils.ApiResponse;
import org.yc.keycloak.utils.ResponseUtil;

import org.yc.keycloak.utils.mappers.dtos.BreederMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    @Autowired
    private BreederMapper breederMapper;
    @Autowired
    private RaceService raceService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<RaceDTO>> save(
            @RequestBody RaceDTO raceDTO,
            HttpServletRequest request) {

        return ResponseEntity.ok(ResponseUtil.success(
                raceService.save(raceDTO),
                "Race saved successfully",
                request.getRequestURI()
        ));
    }


    @PatchMapping("close/{id}")
    public ResponseEntity<ApiResponse<RaceDTO>> close(@PathVariable String id, HttpServletRequest request) {
        RaceDTO raceDTO = raceService.close(id);
        return ResponseEntity.ok(ResponseUtil.success(raceDTO, "Race closed successfully", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RaceDTO>>> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(raceService.findAll(), "Races retrieved successfully", request.getRequestURI()));
    }


    @PostMapping("save-all")
    public ResponseEntity<ApiResponse<List<RaceDTO>>> saveAll(@RequestBody List<RaceDTO> raceDTOs, HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(raceService.saveAll(raceDTOs), "Races saved successfully", request.getRequestURI()));
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<ApiResponse<RaceDTO>> update(@PathVariable String id, @RequestBody RaceDTO raceDTO, HttpServletRequest request) {
        raceDTO = raceService.update(id, raceDTO);
        return ResponseEntity.ok(ResponseUtil.success(raceDTO, "Race closed successfully", request.getRequestURI()));
    }
}