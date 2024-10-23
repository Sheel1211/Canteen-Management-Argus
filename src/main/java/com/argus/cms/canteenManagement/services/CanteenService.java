package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;

import java.util.List;

public interface CanteenService {
    Canteen saveCanteen(Canteen canteen);

    List<Canteen> getAllCanteens();

    Canteen getCanteenById(Long canteenId);

    void deleteCanteenById(Long canteenId);

    Canteen updateCanteenStatusById(Long canteenId);

    Canteen updateCanteenNameById(Long canteenId, Canteen canteen);
}