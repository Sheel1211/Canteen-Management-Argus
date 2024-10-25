package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.exceptions.RecordNotFoundException;

import java.util.List;

public interface CanteenService {
    Canteen saveCanteen(Canteen canteen);

    List<Canteen> getAllCanteens();

    Canteen getCanteenById(Long canteenId) throws RecordNotFoundException;

    void deleteCanteenById(Long canteenId) throws RecordNotFoundException;

    Canteen updateCanteenStatusById(Long canteenId) throws RecordNotFoundException;

    Canteen updateCanteenNameById(Long canteenId, Canteen canteen) throws RecordNotFoundException;
}