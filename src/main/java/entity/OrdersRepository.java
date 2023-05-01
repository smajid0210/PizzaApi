package entity;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o JOIN FETCH o.orderDetails od JOIN FETCH od.pizza p JOIN FETCH p.pizzatype")
    List<Orders> findAllWithPizzas();
}
