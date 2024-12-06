package com.argus.cms.batchManagement.controller;

import com.argus.cms.batchManagement.entity.Batch;
import com.argus.cms.batchManagement.service.BatchService;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/batches")
public class BatchController {
    private final BatchService batchService;

    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() {
        List<Batch> batches = batchService.getAllBatches();
        return ResponseEntity.ok(batches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Batch> getBatchById(@PathVariable Long id) throws RecordNotFoundException {
        Batch batch = batchService.getBatchById(id);
        return ResponseEntity.ok(batch);
    }
}