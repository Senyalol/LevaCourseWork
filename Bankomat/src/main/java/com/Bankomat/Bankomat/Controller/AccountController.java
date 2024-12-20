package com.Bankomat.Bankomat.Controller;

import com.Bankomat.Bankomat.Service.AccountService;
import com.Bankomat.Bankomat.DTO.AccountDTO.CreateAccountDTO;
import com.Bankomat.Bankomat.DTO.AccountDTO.ShortAccountInfoDTO;
import com.Bankomat.Bankomat.DTO.AccountDTO.UpdateAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<ShortAccountInfoDTO> getAllAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public ShortAccountInfoDTO getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public void createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        accountService.createAccount(createAccountDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable("id") int id, @Valid @RequestBody UpdateAccountDTO updateAccountDTO) {
        try {
            accountService.updateAccount(id, updateAccountDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") int id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}