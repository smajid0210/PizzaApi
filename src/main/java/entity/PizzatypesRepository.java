package entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzatypesRepository extends JpaRepository<Pizzatypes, String> {
}
