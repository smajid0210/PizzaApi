package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersDTO {

    private Long id;
    private String date;
    private String time;
    private List<PizzasDTO> pizzas;

}
