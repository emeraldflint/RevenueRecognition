package revenue.transaction.script;

import revenue.transaction.datagateway.DataGateway;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionScript {

    private DataGateway dataGateway;

    public TransactionScript(DataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    public void calculateRevenue(long contractId) {
        try (ResultSet contractsResult = dataGateway.findOne(contractId)) {
            if (!contractsResult.next()) {
                throw new RuntimeException("Contract not exist with id:" + contractId);
            }

            BigDecimal revenue = contractsResult.getBigDecimal("revenue");
            LocalDate dateSigned = contractsResult.getDate("dateSigned").toLocalDate();
            String type = contractsResult.getString("type");

            if ("WORDPROCESSOR".equals(type)) {
                dataGateway.insert(
                        contractId, revenue, dateSigned);
            } else if ("SPREADSHEET".equals(type)) {
                // booked one-third today
                dataGateway.insert(
                        contractId, revenue.divide(new BigDecimal(3)), dateSigned);
                // booked one-third in sixty days
                dataGateway.insert(
                        contractId, revenue.divide(new BigDecimal(3)), dateSigned.plusDays(60));
                // booked one-third in ninety days
                dataGateway.insert(
                        contractId, revenue.divide(new BigDecimal(3)), dateSigned.plusDays(90));
            } else if ("DATABASE".equals(type)) {
                // booked one-third today
                dataGateway.insert(
                        contractId, revenue.divide(new BigDecimal(3)), dateSigned);
                // booked one-third in thirty days
                dataGateway.insert(
                        contractId, revenue.divide(new BigDecimal(3)), dateSigned.plusDays(30));
                // booked one-third in sixty days
                dataGateway.insert(
                        contractId, revenue.divide(new BigDecimal(3)), dateSigned.plusDays(60));

            } else {
                throw new RuntimeException("Unknown product type:" + type);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
