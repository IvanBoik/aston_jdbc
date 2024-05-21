package com.boiko.aston_jdbc.controller;

import com.boiko.aston_jdbc.model.PassportData;
import com.boiko.aston_jdbc.repository.PassportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passports")
@RequiredArgsConstructor
public class PassportController {
    private final PassportRepository passportRepository;

    @PostMapping("/")
    public ResponseEntity<Boolean> add(@RequestBody PassportData passportData) {
        return ResponseEntity.ok(passportRepository.insert(passportData));
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> update(@RequestBody PassportData passportData) {
        return ResponseEntity.ok(passportRepository.update(passportData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(passportRepository.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassportData> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(passportRepository.getByID(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PassportData>> getAll() {
        return ResponseEntity.ok(passportRepository.getAll());
    }
}
