package revenue.transaction.test;

import revenue.transaction.datagateway.DataGateway;
import revenue.transaction.mapper.Mapper;
import revenue.transaction.product.Product;

import java.util.List;

public class TestTableDataGateway {
    public static void main(String[] args) {

        Mapper mapper = new Mapper(new DataGateway());
        List<Product> products = mapper.findAll();
        System.out.println(products);
    }
}
