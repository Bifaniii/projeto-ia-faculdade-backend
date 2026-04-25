package br.com.bifani.loginjavaprojetoia.services;

import br.com.bifani.loginjavaprojetoia.model.History;
import br.com.bifani.loginjavaprojetoia.repositories.IHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HistoryService {
    private final IHistoryRepository repository;

    public HistoryService(IHistoryRepository repository) {
        this.repository = repository;
    }

    public List<History> getHistoryByUserId(UUID userId) {
        return repository.findByUserId(userId);
    }

    public List<History> getAllHistory() {
        return repository.findAll();
    }

    @Transactional
    public History createHistory(History history) {
        return repository.save(history);
    }

    @Transactional
    public History save(History history) {
        return repository.save(history);
    }

    public History findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("History not found with id: " + id));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("History not found with id: " + id);
        }
        repository.deleteById(id);
    }
}


