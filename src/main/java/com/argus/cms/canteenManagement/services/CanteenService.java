package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.users.entities.Users;

import java.util.List;

public interface CanteenService {
    Canteen saveCanteen(Canteen canteen) throws RecordNotFoundException, DataValidationErrorException;

    List<Canteen> getAllCanteens();

    Canteen ownerRequestToGetCanteenOwnership(Long canteenId, Long userId) throws RecordNotFoundException;

    Canteen getCanteenById(Long canteenId) throws RecordNotFoundException;

    void deleteCanteenById(Long canteenId) throws RecordNotFoundException;

    Canteen updateCanteenStatusById(Long canteenId) throws RecordNotFoundException;

    Canteen updateCanteenNameById(Long canteenId, Canteen canteen) throws RecordNotFoundException;

    Canteen findByUser(Users user);
}