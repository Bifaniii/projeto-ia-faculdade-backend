package br.com.bifani.loginjavaprojetoia.repositories;

import br.com.bifani.loginjavaprojetoia.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IHistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(UUID userid);
}
