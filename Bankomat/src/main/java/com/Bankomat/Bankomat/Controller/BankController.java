package com.Bankomat.Bankomat.Controller;

import com.Bankomat.Bankomat.Service.BankService;
import com.Bankomat.Bankomat.DTO.BankDTO.CreateBankDTO;
import com.Bankomat.Bankomat.DTO.BankDTO.ShortBankInfoDTO;
import com.Bankomat.Bankomat.DTO.BankDTO.UpdateBankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<ShortBankInfoDTO> getAllBanks() {
        return bankService.getBanks();
    }

    @GetMapping("/{id}")
    public ShortBankInfoDTO getBankById(@PathVariable int id) {
        return bankService.getBankById(id);
    }

    @PostMapping
    public void createBank(@RequestBody CreateBankDTO createBankDTO) {
        bankService.createBank(createBankDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBank(@PathVariable("id") int id, @Valid @RequestBody UpdateBankDTO updateBankDTO) {
        try {
            bankService.updateBank(id, updateBankDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable("id") int id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}