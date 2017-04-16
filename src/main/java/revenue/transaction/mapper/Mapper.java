package revenue.transaction.mapper;

import revenue.transaction.datagateway.DataGateway;
import revenue.transaction.product.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private DataGateway dataGateway;

    public Mapper(DataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        try {
            ResultSet allProducts = dataGateway.findAllProducts();
            while (allProducts.next()) {
                result.add(new Product(allProducts.getInt("id"), allProducts.getString("name"), allProducts.getString("type")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
