package org.yc.keycloak.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.yc.keycloak.dtos.CompetitionDTO;
import org.yc.keycloak.services.CompetitionService;
import org.yc.keycloak.utils.ApiResponse;
import org.yc.keycloak.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    private CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping("save")
    public ResponseEntity<ApiResponse<CompetitionDTO>> save(@RequestBody CompetitionDTO competitionDTO, HttpServletRequest request) {
        competitionDTO = competitionService.save(competitionDTO);
        return ResponseEntity.ok(ResponseUtil.success(competitionDTO, "Competition saved successfully", request.getRequestURI()));
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<ApiResponse<CompetitionDTO>> update(@PathVariable String id, @RequestBody CompetitionDTO competitionDTO, HttpServletRequest request) {
        competitionDTO = competitionService.update(id, competitionDTO);
        return ResponseEntity.ok(ResponseUtil.success(competitionDTO, "Competition updated successfully", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompetitionDTO>>> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(competitionService.findAll(), "Competitions retrieved successfully", request.getRequestURI()));
    }

    @PostMapping("save-all")
    public ResponseEntity<ApiResponse<List<CompetitionDTO>>> saveAll(@RequestBody List<CompetitionDTO> competitionDTOS, HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(competitionService.saveAll(competitionDTOS), "Competitions saved successfully", request.getRequestURI()));
    }

    @PatchMapping("close")
    public ResponseEntity<ApiResponse<CompetitionDTO>> close(@PathVariable String id, @RequestBody CompetitionDTO competitionDTO, HttpServletRequest request) {
        competitionDTO = competitionService.update(id, competitionDTO);
        return ResponseEntity.ok(ResponseUtil.success(competitionDTO, "Competition closed successfully", request.getRequestURI()));
    }
}
