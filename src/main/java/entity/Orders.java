package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "orders")
public class Orders {

    @Id
    @Column (name = "orders_id")
    private Long orderid;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails>orderDetails = new ArrayList<>();



    public void setOrderid(Long orderid)
    {
        this.orderid = orderid;
    }

    public Long getOrderid()
    {
        return orderid;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
       return date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTime()
    {
        return time;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails)
    {
        this.orderDetails = orderDetails;
    }
    public List<OrderDetails> getOrderDetails()
    {
        return orderDetails;
    }



}
