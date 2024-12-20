package com.Bankomat.Bankomat.Service;

import com.Bankomat.Bankomat.DTO.AtmDTO.CreateATMDTO;
import com.Bankomat.Bankomat.DTO.AtmDTO.ShortATMInfoDTO;
import com.Bankomat.Bankomat.DTO.AtmDTO.UpdateATMDTO;
import com.Bankomat.Bankomat.Entites.Atm;
import com.Bankomat.Bankomat.Repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AtmService {

    private final AtmRepository atmRepository;

    @Autowired
    public AtmService(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    public List<ShortATMInfoDTO> getATMs() {
        List<Atm> atms = atmRepository.findAll();

        return atms.stream()
                .map(atm -> {
                    ShortATMInfoDTO atmDTO = new ShortATMInfoDTO();
                    atmDTO.setAtm_id(atm.getId());
                    atmDTO.setLocation(atm.getLocation());
                    atmDTO.setBank_id(atm.getBank().getId());
                    atmDTO.setStatus(atm.getStatus());
                    atmDTO.setLastMaintenance(atm.getLastMaintenance());
                    return atmDTO;
                }).toList();
    }

    public ShortATMInfoDTO getATMById(int id) {
        Atm atm = atmRepository.findById(id);

        ShortATMInfoDTO atmDTO = new ShortATMInfoDTO();
        atmDTO.setAtm_id(atm.getId());
        atmDTO.setLocation(atm.getLocation());
        atmDTO.setBank_id(atm.getBank().getId());
        atmDTO.setStatus(atm.getStatus());
        atmDTO.setLastMaintenance(atm.getLastMaintenance());

        return atmDTO;
    }

    public void createATM(CreateATMDTO createATMDTO) {
        Atm atm = new Atm();

        atm.setLocation(createATMDTO.getLocation());
        atm.setBank(new com.Bankomat.Bankomat.Entites.Bank()); // Предположим, что Bank уже загружен по ID
        atm.getBank().setId(createATMDTO.getBank_id());
        atm.setStatus(createATMDTO.getStatus());
        atm.setLastMaintenance(createATMDTO.getLastMaintenance());

        atmRepository.save(atm);
    }

    public void updateATM(int id, UpdateATMDTO updateATMDTO) {
        Atm atmToUpdate = atmRepository.findById(id);

        // Обновляем поля только при наличии новых значений в DTO
        if (updateATMDTO.getLocation() != null) {
            atmToUpdate.setLocation(updateATMDTO.getLocation());
        }
        if (updateATMDTO.getBank_id() != null) {
            atmToUpdate.setBank(new com.Bankomat.Bankomat.Entites.Bank());
            atmToUpdate.getBank().setId(updateATMDTO.getBank_id());
        }
        if (updateATMDTO.getStatus() != null) {
            atmToUpdate.setStatus(updateATMDTO.getStatus());
        }
        if (updateATMDTO.getLastMaintenance() != null) {
            atmToUpdate.setLastMaintenance(updateATMDTO.getLastMaintenance());
        }

        atmRepository.save(atmToUpdate);
    }

    public void deleteATM(int id) {
        Atm atmToDelete = atmRepository.findById(id);

        atmRepository.delete(atmToDelete);
    }
}