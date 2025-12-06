package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.repository;

import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {
    List<CartaoEntity> findByBandeira(String bandeira);
    List<CartaoEntity> findByTitular(String titular);
}