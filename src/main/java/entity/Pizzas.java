package entity;

import jakarta.persistence.*;

@Entity
@Table (name = "pizzas")
public class Pizzas {

    @Id
    @Column (name = "pizza_id")
    private String pizzaid;

    @ManyToOne
    @JoinColumn(name = "pizza_type_id")
    private Pizzatypes pizzatype;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private String price;

    public void setPizzaid(String pizzaid)
    {
        this.pizzaid = pizzaid;
    }

    public String getPizzaid()
    {
        return pizzaid;
    }

    public void setPizzatype(Pizzatypes pizzatype)
    {
        this.pizzatype = pizzatype;
    }

    public Pizzatypes getPizzatype()
    {
        return pizzatype;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getSize()
    {
        return size;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getPrice()
    {
        return price;
    }


}
