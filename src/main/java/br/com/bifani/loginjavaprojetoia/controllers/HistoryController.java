package br.com.bifani.loginjavaprojetoia.controllers;

import br.com.bifani.loginjavaprojetoia.model.History;
import br.com.bifani.loginjavaprojetoia.model.User;
import br.com.bifani.loginjavaprojetoia.model.dtos.HistoryRequest;
import br.com.bifani.loginjavaprojetoia.model.enums.Role;
import br.com.bifani.loginjavaprojetoia.services.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public ResponseEntity<List<History>> getHistory(@AuthenticationPrincipal User user) {
        List<History> histories;
        
        if (user.getRole() == Role.ADMIN) {
            histories = historyService.getAllHistory();
        } else {
            histories = historyService.getHistoryByUserId(user.getId());
        }
        
        return ResponseEntity.ok(histories);
    }

    @PostMapping
    public ResponseEntity<History> saveHistory(@RequestBody HistoryRequest request, @AuthenticationPrincipal User user) {
        History history = History.builder()
                .chat(request.chat())
                .user(user)
                .build();
        History saved = historyService.createHistory(history);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id, @AuthenticationPrincipal User user) {
        History history = historyService.findById(id);

        if (user.getRole() != Role.ADMIN && !history.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        historyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

