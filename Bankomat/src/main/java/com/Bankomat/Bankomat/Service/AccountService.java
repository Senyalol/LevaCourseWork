package com.Bankomat.Bankomat.Service;

import com.Bankomat.Bankomat.DTO.AccountDTO.CreateAccountDTO;
import com.Bankomat.Bankomat.DTO.AccountDTO.ShortAccountInfoDTO;
import com.Bankomat.Bankomat.DTO.AccountDTO.UpdateAccountDTO;
import com.Bankomat.Bankomat.Entites.Account;
import com.Bankomat.Bankomat.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<ShortAccountInfoDTO> getAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map(account -> {
                    ShortAccountInfoDTO accountDTO = new ShortAccountInfoDTO();
                    accountDTO.setAccount_id(account.getId());
                    accountDTO.setUser_id(account.getUser().getId());
                    accountDTO.setBank_id(account.getBank().getId());
                    accountDTO.setAccountNumber(account.getAccountNumber());
                    accountDTO.setBalance(account.getBalance());
                    accountDTO.setAccountType(account.getAccountType());
                    accountDTO.setCreatedAt(account.getCreatedAt());

                    return accountDTO;
                }).toList();
    }

    public ShortAccountInfoDTO getAccountById(int id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));

        ShortAccountInfoDTO accountDTO = new ShortAccountInfoDTO();
        accountDTO.setAccount_id(account.getId());
        accountDTO.setUser_id(account.getUser().getId());
        accountDTO.setBank_id(account.getBank().getId());
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setCreatedAt(account.getCreatedAt());

        return accountDTO;
    }

    public void createAccount(CreateAccountDTO createAccountDTO) {
        Account account = new Account();

        account.setUser(new com.Bankomat.Bankomat.Entites.User()); // Предположим, что User уже загружен по ID
        account.getUser().setId(createAccountDTO.getUser_id());

        account.setBank(new com.Bankomat.Bankomat.Entites.Bank()); // Предположим, что Bank уже загружен по ID
        account.getBank().setId(createAccountDTO.getBank_id());

        account.setAccountNumber(createAccountDTO.getAccountNumber());
        account.setBalance(createAccountDTO.getBalance());
        account.setAccountType(createAccountDTO.getAccountType());
        account.setCreatedAt(createAccountDTO.getCreatedAt());

        accountRepository.save(account);
    }

    public void updateAccount(int id, UpdateAccountDTO updateAccountDTO) {
        Account accountToUpdate = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account with ID " + id + " not found"));

        // Обновляем поля только при наличии новых значений в DTO
        if (updateAccountDTO.getUser_id() != null) {
            accountToUpdate.setUser(new com.Bankomat.Bankomat.Entites.User());
            accountToUpdate.getUser().setId(updateAccountDTO.getUser_id());
        }
        if (updateAccountDTO.getBank_id() != null) {
            accountToUpdate.setBank(new com.Bankomat.Bankomat.Entites.Bank());
            accountToUpdate.getBank().setId(updateAccountDTO.getBank_id());
        }
        if (updateAccountDTO.getAccountNumber() != null) {
            accountToUpdate.setAccountNumber(updateAccountDTO.getAccountNumber());
        }
        if (updateAccountDTO.getBalance() != null) {
            accountToUpdate.setBalance(updateAccountDTO.getBalance());
        }
        if (updateAccountDTO.getAccountType() != null) {
            accountToUpdate.setAccountType(updateAccountDTO.getAccountType());
        }
        if (updateAccountDTO.getCreatedAt() != null) {
            accountToUpdate.setCreatedAt(updateAccountDTO.getCreatedAt());
        }

        accountRepository.save(accountToUpdate);
    }

    public void deleteAccount(int id) {
        Account accountToDelete = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account with ID " + id + " not found"));

        accountRepository.delete(accountToDelete);
    }
}