package br.com.bifani.loginjavaprojetoia.repositories;

import br.com.bifani.loginjavaprojetoia.model.History;
import br.com.bifani.loginjavaprojetoia.model.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHistoryRepository extends JpaRepository<History, Id> {
    List<History> findByUser(User user);
}
