package com.Bankomat.Bankomat.Controller;

import com.Bankomat.Bankomat.Service.TransactionService;
import com.Bankomat.Bankomat.DTO.TransactionDTO.CreateTransactionDTO;
import com.Bankomat.Bankomat.DTO.TransactionDTO.ShortTransactionDTO;
import com.Bankomat.Bankomat.DTO.TransactionDTO.UpdateTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<ShortTransactionDTO> getAllTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/{id}")
    public ShortTransactionDTO getTransactionById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public void createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO) {
        transactionService.createTransaction(createTransactionDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTransaction(@PathVariable("id") int id, @Valid @RequestBody UpdateTransactionDTO updateTransactionDTO) {
        try {
            transactionService.updateTransaction(id, updateTransactionDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") int id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}