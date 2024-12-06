package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.CanteenUser;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;

public interface CanteenUserService {
     CanteenUser addCanteenManager(CanteenUser canteenUser) throws CustomException, RecordNotFoundException, DataValidationErrorException;
     CanteenUser findCanteenManagerById(Long canteenManagerId) throws RecordNotFoundException;
     CanteenUser toggleCanteenManagerById(CanteenUser canteenUser) throws CustomException,RecordNotFoundException;
     void deleteCanteenManagerReqById(CanteenUser canteenUser) throws CustomException;
     CanteenUser addCanteenOwner(CanteenUser canteenUser) throws CustomException;
}
