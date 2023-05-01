package processor;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Importcsvtodb {

    private final OrdersRepository ordersRepository;
    private final PizzasRepository pizzasRepository;
    private final PizzatypesRepository pizzatypesRepository;

    private final OrderDetailsRepository orderdetailsRepository;


    @Autowired
    public Importcsvtodb(OrdersRepository ordersRepository, OrderDetailsRepository orderDetailsRepository, PizzasRepository pizzasRepository, PizzatypesRepository pizzatypesRepository) {
        this.ordersRepository = ordersRepository;
        this.orderdetailsRepository = orderDetailsRepository;
        this.pizzasRepository = pizzasRepository;
        this.pizzatypesRepository = pizzatypesRepository;
    }

    @Transactional
    public void loadCsvToDb() throws IOException {

        Reader reader = new FileReader("pizza_types.csv");

        System.out.println("pizzatypes import");
        List<Pizzatypes> pizzatypestotal = new ArrayList<>();
        List<String[]> pizzatypeslist = readcsv(reader);


        for (String[] row : pizzatypeslist) {
            Pizzatypes pizzatypes = new Pizzatypes();
            pizzatypes.setPizzatypeid(row[0]);
            pizzatypes.setName(row[1]);
            pizzatypes.setCategory(row[2]);
            pizzatypes.setIngredients(row[3]);
            pizzatypestotal.add(pizzatypes);
        }
        pizzatypesRepository.saveAll(pizzatypestotal);
        pizzatypesRepository.flush();

        reader = new FileReader("pizzas.csv");

        System.out.println("pizzas import");
        List<Pizzas> pizzastotal = new ArrayList<>();
        List<String[]> pizzaslist = readcsv(reader);


        for (String[] row : pizzaslist) {
            Pizzas pizzas = new Pizzas();
            pizzas.setPizzaid(row[0]);
            Optional<Pizzatypes> thispizzatypes = pizzatypesRepository.findById(row[1]);

            if(thispizzatypes.isPresent())
                pizzas.setPizzatype(thispizzatypes.get());

            pizzas.setSize(row[2]);
            pizzas.setPrice(row[3]);
            pizzastotal.add(pizzas);
        }
        pizzasRepository.saveAll(pizzastotal);
        pizzasRepository.flush();

        reader = new FileReader("orders.csv");

        System.out.println("orders import");
        List<Orders> orderstotal = new ArrayList<>();
        List<String[]> orderslist = readcsv(reader);


        for (String[] row : orderslist) {
            Orders orders = new Orders();
            orders.setOrderid(Long.parseLong(row[0]));
            orders.setDate(row[1]);
            orders.setTime(row[2]);
            orderstotal.add(orders);
        }
        ordersRepository.saveAll(orderstotal);
        ordersRepository.flush();

        reader = new FileReader("order_details.csv");

        System.out.println("orderdetails import");
        List<OrderDetails> orderdetailstotal = new ArrayList<>();
        List<String[]> orderdetailslist = readcsv(reader);


        for (String[] row : orderdetailslist) {
            OrderDetails orderdetails = new OrderDetails();
            orderdetails.setOrderdetailsid(Long.parseLong(row[0]));
            Optional<Orders> thisorders = ordersRepository.findById(Long.parseLong(row[1]));
            if(thisorders.isPresent())
               orderdetails.setOrders(thisorders.get());

            Optional<Pizzas> thispizzas = pizzasRepository.findById(row[2]);

            if(thispizzas.isPresent())
               orderdetails.setPizza(thispizzas.get());
            orderdetails.setQuantity(Integer.valueOf(row[3]));

            orderdetailstotal.add(orderdetails);
        }
        orderdetailsRepository.saveAll(orderdetailstotal);
        orderdetailsRepository.flush();

        System.out.println("all import done");

    }


    public List<String[]> readcsv(Reader reader)
    {
        CSVReader csvReader = null;
        List<String[]> userRows = new ArrayList<>();

        try {
            csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();
            userRows = csvReader.readAll();
        }

        catch(IOException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        catch(CsvException c)
        {
            System.out.println(c);
            c.printStackTrace();
        }
        finally
        {
            if(csvReader!=null)
            {
                try {
                    csvReader.close();
                }
                catch(IOException ex)
                {
                    System.out.println(ex);
                    ex.printStackTrace();
                }
            }
            return userRows;
        }

    }


}
