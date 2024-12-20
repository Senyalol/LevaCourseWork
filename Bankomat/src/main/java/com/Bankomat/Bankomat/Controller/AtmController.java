package com.Bankomat.Bankomat.Controller;

import com.Bankomat.Bankomat.Service.AtmService;
import com.Bankomat.Bankomat.DTO.AtmDTO.CreateATMDTO;
import com.Bankomat.Bankomat.DTO.AtmDTO.ShortATMInfoDTO;
import com.Bankomat.Bankomat.DTO.AtmDTO.UpdateATMDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/atms")
public class AtmController {

    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    public List<ShortATMInfoDTO> getAllATMs() {
        return atmService.getATMs();
    }

    @GetMapping("/{id}")
    public ShortATMInfoDTO getATMById(@PathVariable int id) {
        return atmService.getATMById(id);
    }

    @PostMapping
    public void createATM(@RequestBody CreateATMDTO createATMDTO) {
        atmService.createATM(createATMDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateATM(@PathVariable("id") int id, @Valid @RequestBody UpdateATMDTO updateATMDTO) {
        try {
            atmService.updateATM(id, updateATMDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteATM(@PathVariable("id") int id) {
        try {
            atmService.deleteATM(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}