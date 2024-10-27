package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.CanteenManager;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;

public interface CanteenManagerService {
    public CanteenManager addCanteenManager(CanteenManager canteenManager) throws CustomException, RecordNotFoundException, DataValidationErrorException;
    public CanteenManager findCanteenManagerById(Long canteenManagerId) throws RecordNotFoundException;
    public CanteenManager toggleCanteenManagerById(CanteenManager canteenManager) throws CustomException,RecordNotFoundException;
    public void deleteCanteenManagerReqById(CanteenManager canteenManager) throws CustomException;


}
