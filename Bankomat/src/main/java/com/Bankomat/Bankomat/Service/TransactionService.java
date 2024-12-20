package com.Bankomat.Bankomat.Service;

import com.Bankomat.Bankomat.DTO.TransactionDTO.CreateTransactionDTO;
import com.Bankomat.Bankomat.DTO.TransactionDTO.ShortTransactionDTO;
import com.Bankomat.Bankomat.DTO.TransactionDTO.UpdateTransactionDTO;
import com.Bankomat.Bankomat.Entites.Transaction;
import com.Bankomat.Bankomat.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<ShortTransactionDTO> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream()
                .map(transaction -> {
                    ShortTransactionDTO transactionDTO = new ShortTransactionDTO();
                    transactionDTO.setTransaction_id(transaction.getId());
                    transactionDTO.setAccount_id(transaction.getAccount().getId());
                    transactionDTO.setAtm_id(transaction.getAtm().getId());
                    transactionDTO.setTransactionType(transaction.getTransactionType());
                    transactionDTO.setAmount(transaction.getAmount());
                    transactionDTO.setTransactionDate(transaction.getTransactionDate());
                    return transactionDTO;
                }).toList();
    }

    public ShortTransactionDTO getTransactionById(int id) {
        Transaction transaction = transactionRepository.findById(id);

        ShortTransactionDTO transactionDTO = new ShortTransactionDTO();
        transactionDTO.setTransaction_id(transaction.getId());
        transactionDTO.setAccount_id(transaction.getAccount().getId());
        transactionDTO.setAtm_id(transaction.getAtm().getId());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());

        return transactionDTO;
    }

    public void createTransaction(CreateTransactionDTO createTransactionDTO) {
        Transaction transaction = new Transaction();

        transaction.setAccount(new com.Bankomat.Bankomat.Entites.Account()); // Предположим, что Account уже загружен по ID
        transaction.getAccount().setId(createTransactionDTO.getAccount_id());

        transaction.setAtm(new com.Bankomat.Bankomat.Entites.Atm()); // Предположим, что ATM уже загружен по ID
        transaction.getAtm().setId(createTransactionDTO.getAtm_id());

        transaction.setTransactionType(createTransactionDTO.getTransactionType());
        transaction.setAmount(createTransactionDTO.getAmount());
        transaction.setTransactionDate(createTransactionDTO.getTransactionDate());

        transactionRepository.save(transaction);
    }

    public void updateTransaction(int id, UpdateTransactionDTO updateTransactionDTO) {
        Transaction transactionToUpdate = transactionRepository.findById(id);

        // Обновляем поля только при наличии новых значений в DTO
        if (updateTransactionDTO.getAccount_id() != null) {
            transactionToUpdate.setAccount(new com.Bankomat.Bankomat.Entites.Account());
            transactionToUpdate.getAccount().setId(updateTransactionDTO.getAccount_id());
        }
        if (updateTransactionDTO.getAtm_id() != null) {
            transactionToUpdate.setAtm(new com.Bankomat.Bankomat.Entites.Atm());
            transactionToUpdate.getAtm().setId(updateTransactionDTO.getAtm_id());
        }
        if (updateTransactionDTO.getTransactionType() != null) {
            transactionToUpdate.setTransactionType(updateTransactionDTO.getTransactionType());
        }
        if (updateTransactionDTO.getAmount() != null) {
            transactionToUpdate.setAmount(updateTransactionDTO.getAmount());
        }
        if (updateTransactionDTO.getTransactionDate() != null) {
            transactionToUpdate.setTransactionDate(updateTransactionDTO.getTransactionDate());
        }

        transactionRepository.save(transactionToUpdate);
    }

    public void deleteTransaction(int id) {
        Transaction transactionToDelete = transactionRepository.findById(id);

        transactionRepository.delete(transactionToDelete);
    }
}