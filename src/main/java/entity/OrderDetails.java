package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {

    @Id
    @Column(name="order_details_id")
    private Long orderdetailsid;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_id")
    private Pizzas pizza;

    @Column(name = "quantity")
    private int quantity;


    public void setOrderdetailsid(Long orderdetailsid)
    {
        this.orderdetailsid = orderdetailsid;
    }

    public Long getOrderdetailsid()
    {
        return orderdetailsid;
    }

    public void setOrders(Orders orders)
    {
        this.orders = orders;
    }

    public Orders getOrders()
    {
        return orders;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setPizza(Pizzas pizza)
    {
        this.pizza = pizza;
    }

    public Pizzas getPizza()
    {
        return pizza;
    }



}
