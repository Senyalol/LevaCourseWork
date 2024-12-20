package com.Bankomat.Bankomat.Service;

import com.Bankomat.Bankomat.DTO.UserDTO.CreateUserDTO;
import com.Bankomat.Bankomat.DTO.UserDTO.ShortUserInfoDTO;
import com.Bankomat.Bankomat.DTO.UserDTO.UpdateUserDTO;
import com.Bankomat.Bankomat.Entites.User;
import com.Bankomat.Bankomat.Repository.UserRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    private final UserRepostiory userRepository;

    @Autowired
    public UserService(UserRepostiory userRepository) {
        this.userRepository = userRepository;
    }

    public List<ShortUserInfoDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    ShortUserInfoDTO userDTO = new ShortUserInfoDTO();
                    userDTO.setUser_id(user.getId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setCreatedAt(user.getCreatedAt());
                    userDTO.setPassword(user.getPassword());
                    return userDTO;
                }).toList();
    }

    public ShortUserInfoDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        ShortUserInfoDTO userDTO = new ShortUserInfoDTO();
        userDTO.setUser_id(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    public void createUser(CreateUserDTO createUserDTO) {
        User user = new User();

        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setEmail(createUserDTO.getEmail());
        user.setCreatedAt(createUserDTO.getCreatedAt());

        userRepository.save(user);
    }

    public void updateUser(int id, UpdateUserDTO updateUserDTO) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));

        // Обновляем поля только при наличии новых значений в DTO
        if (updateUserDTO.getUsername() != null) {
            userToUpdate.setUsername(updateUserDTO.getUsername());
        }
        if (updateUserDTO.getPassword() != null) {
            userToUpdate.setPassword(updateUserDTO.getPassword());
        }
        if (updateUserDTO.getEmail() != null) {
            userToUpdate.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getCreatedAt() != null) {
            userToUpdate.setCreatedAt(updateUserDTO.getCreatedAt());
        }

        userRepository.save(userToUpdate);
    }

    public void deleteUser(int id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));

        userRepository.delete(userToDelete);
    }
}