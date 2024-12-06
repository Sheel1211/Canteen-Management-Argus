package com.argus.cms.batchManagement.service;

import com.argus.cms.batchManagement.entity.Batch;
import com.argus.cms.batchManagement.repository.BatchRepository;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BatchServiceImpl implements BatchService {
    private final BatchRepository batchRepository;

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    @Override
    public Batch getBatchById(Long id) throws RecordNotFoundException {
        return batchRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Batch with ID " + id + " not found"));
    }
}