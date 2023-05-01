package pizzaApi;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PizzaApiController {

    @Autowired
    private OrdersRepository ordersRepository;


    @GetMapping("/viewpizzasales")
    public List<OrdersDTO> viewsales() throws Exception
    {
        List<Orders> orders = ordersRepository.findAllWithPizzas();

        return orders.stream().map(order -> {
            OrdersDTO orderDto = new OrdersDTO();
            orderDto.setId(order.getOrderid());
            orderDto.setDate(order.getDate());
            orderDto.setTime(order.getTime());

            List<PizzasDTO> pizzas = order.getOrderDetails().stream().map(orderDetail -> {
                PizzasDTO pizzaDto = new PizzasDTO();
                pizzaDto.setId(orderDetail.getPizza().getPizzaid());
                pizzaDto.setSize(orderDetail.getPizza().getSize());
                pizzaDto.setPrice(orderDetail.getPizza().getPrice());
                pizzaDto.setQuantity(orderDetail.getQuantity());

                PizzaTypesDTO pizzaTypeDto = new PizzaTypesDTO();
                pizzaTypeDto.setId(orderDetail.getPizza().getPizzatype().getPizzatypeid());
                pizzaTypeDto.setName(orderDetail.getPizza().getPizzatype().getName());
                pizzaTypeDto.setCategory(orderDetail.getPizza().getPizzatype().getCategory());
                pizzaTypeDto.setIngredients(orderDetail.getPizza().getPizzatype().getIngredients());
                pizzaDto.setPizzaType(pizzaTypeDto);

                return pizzaDto;
            }).collect(Collectors.toList());

            orderDto.setPizzas(pizzas);
            return orderDto;
        }).collect(Collectors.toList());
    }

}
