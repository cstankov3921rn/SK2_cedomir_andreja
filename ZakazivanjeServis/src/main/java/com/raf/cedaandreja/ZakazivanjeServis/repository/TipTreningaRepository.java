package com.raf.cedaandreja.ZakazivanjeServis.repository;

import com.raf.cedaandreja.ZakazivanjeServis.domain.FiskulturnaSala;
import com.raf.cedaandreja.ZakazivanjeServis.domain.TipTreninga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipTreningaRepository  extends JpaRepository<TipTreninga, Long> {
    Optional<TipTreninga> findTipTreningaByNaziv(String naziv);

    List<TipTreninga> findByFiskulturnaSala(FiskulturnaSala fiskulturnaSala);

    void deleteAllByFiskulturnaSala(FiskulturnaSala fiskulturnaSala);
}
