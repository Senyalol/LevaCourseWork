package com.Bankomat.Bankomat.Service;

import com.Bankomat.Bankomat.DTO.BankDTO.CreateBankDTO;
import com.Bankomat.Bankomat.DTO.BankDTO.ShortBankInfoDTO;
import com.Bankomat.Bankomat.DTO.BankDTO.UpdateBankDTO;
import com.Bankomat.Bankomat.Entites.Bank;
import com.Bankomat.Bankomat.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<ShortBankInfoDTO> getBanks() {
        List<Bank> banks = bankRepository.findAll();

        return banks.stream()
                .map(bank -> {
                    ShortBankInfoDTO bankDTO = new ShortBankInfoDTO();
                    bankDTO.setBank_id(bank.getId());
                    bankDTO.setName(bank.getName());
                    bankDTO.setAddress(bank.getAddress());
                    bankDTO.setContactNumber(bank.getContactNumber());
                    return bankDTO;
                }).toList();
    }

    public ShortBankInfoDTO getBankById(int id) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank not found"));

        ShortBankInfoDTO bankDTO = new ShortBankInfoDTO();
        bankDTO.setBank_id(bank.getId());
        bankDTO.setName(bank.getName());
        bankDTO.setAddress(bank.getAddress());
        bankDTO.setContactNumber(bank.getContactNumber());

        return bankDTO;
    }

    public void createBank(CreateBankDTO createBankDTO) {
        Bank bank = new Bank();

        bank.setName(createBankDTO.getName());
        bank.setAddress(createBankDTO.getAddress());
        bank.setContactNumber(createBankDTO.getContactNumber());

        bankRepository.save(bank);
    }

    public void updateBank(int id, UpdateBankDTO updateBankDTO) {
        Bank bankToUpdate = bankRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank with ID " + id + " not found"));

        // Обновляем поля только при наличии новых значений в DTO
        if (updateBankDTO.getName() != null) {
            bankToUpdate.setName(updateBankDTO.getName());
        }
        if (updateBankDTO.getAddress() != null) {
            bankToUpdate.setAddress(updateBankDTO.getAddress());
        }
        if (updateBankDTO.getContactNumber() != null) {
            bankToUpdate.setContactNumber(updateBankDTO.getContactNumber());
        }

        bankRepository.save(bankToUpdate);
    }

    public void deleteBank(int id) {
        Bank bankToDelete = bankRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank with ID " + id + " not found"));

        bankRepository.delete(bankToDelete);
    }
}