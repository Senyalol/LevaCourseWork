package com.Bankomat.Bankomat.Controller;

import com.Bankomat.Bankomat.Service.UserService;
import com.Bankomat.Bankomat.DTO.UserDTO.CreateUserDTO;
import com.Bankomat.Bankomat.DTO.UserDTO.ShortUserInfoDTO;
import com.Bankomat.Bankomat.DTO.UserDTO.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<ShortUserInfoDTO> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ShortUserInfoDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") int id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            userService.updateUser(id, updateUserDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}