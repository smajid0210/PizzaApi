package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizzatypes")
public class Pizzatypes {

    @Id
    @Column (name = "pizza_type_id")
    private String pizzatypeid;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "ingredients")
    private String ingredients;


    public void setPizzatypeid(String pizzatypeid)
    {
        this.pizzatypeid = pizzatypeid;
    }

    public String getPizzatypeid()
    {
        return pizzatypeid;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public void setIngredients(String ingredients)
    {
        this.ingredients = ingredients;
    }

    public String getIngredients()
    {
        return ingredients;
    }


}
