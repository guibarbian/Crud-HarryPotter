package com.db.Crud_HarryPotter.Repository;

import com.db.Crud_HarryPotter.Model.Bruxo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BruxoRepository extends JpaRepository<Bruxo, Long> {
}
