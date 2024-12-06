package com.argus.cms.batchManagement.service;

import com.argus.cms.batchManagement.entity.Batch;
import com.argus.cms.exceptions.RecordNotFoundException;

import java.util.List;

public interface BatchService {
    List<Batch> getAllBatches();

    Batch getBatchById(Long id) throws RecordNotFoundException;
}
