package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzasDTO {

    private String id;
    private int quantity;
    private String size;
    private String price;
    private PizzaTypesDTO pizzaType;
}
