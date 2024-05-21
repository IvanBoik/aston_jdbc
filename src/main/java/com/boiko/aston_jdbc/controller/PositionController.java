package com.boiko.aston_jdbc.controller;

import com.boiko.aston_jdbc.model.Position;
import com.boiko.aston_jdbc.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionRepository positionRepository;

    @PostMapping("/")
    public ResponseEntity<Boolean> add(@RequestBody String positionName) {
        Position position = new Position();
        position.setName(positionName);
        return ResponseEntity.ok(positionRepository.insert(position));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody String positionName) {
        Position position = new Position(id, positionName);
        return ResponseEntity.ok(positionRepository.update(position));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(positionRepository.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(positionRepository.getByID(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Position>> getAll() {
        return ResponseEntity.ok(positionRepository.getAll());
    }
}
