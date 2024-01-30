package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerDAO extends DBContext {

    // GET ALL CUSTOMERS
    public List<Customer> GetAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String sql = """
                     SELECT [CustomerID]
                           ,[FullName]
                           ,[Phone]
                           ,[Email]
                           ,[Image]
                           ,[Username]
                           ,[Password]
                           ,[Address]
                       FROM [dbo].[Customers]""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setFullName(rs.getString("FullName"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                customer.setImage(rs.getString("Image"));
                customer.setUsername(rs.getString("Username"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddress(rs.getString("Address"));
                list.add(customer);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // GET CUSTOMER VIA ID
    public Customer GetCustomerById(int customerId) {
        String sql = """
                    SELECT [CustomerID]
                          ,[FullName]
                          ,[Phone]
                          ,[Email]
                          ,[Image]
                          ,[Username]
                          ,[Password]
                          ,[Address]
                      FROM [dbo].[Customers]
                      WHERE CustomerID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setFullName(rs.getString("Fullname"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                customer.setImage(rs.getString("Image"));
                customer.setUsername(rs.getString("Username"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddress(rs.getString("Address"));
                return customer;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // INSERT A CUSTOMER
    public void InsertCustomer(Customer customer) {
        String sql = """
                     INSERT INTO [dbo].[Customers]
                                ([FullName]
                                ,[Phone]
                                ,[Email]
                                ,[Image]
                                ,[Username]
                                ,[Password]
                                ,[Address])
                          VALUES(?,?,?,?,?,?,?)""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customer.getFullName());
            st.setString(2, customer.getPhone());
            st.setString(3, customer.getEmail());
            st.setString(4, customer.getImage());
            st.setString(5, customer.getUsername());
            st.setString(6, customer.getPassword());
            st.setString(7, customer.getAddress());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // UPDATE A CUSTOMER
    public void UpdateCustomer(Customer customer) {
        String sql = """
                     UPDATE [dbo].[Customers]
                                            SET [FullName] = ?
                                               ,[Phone] = ?
                                               ,[Email] = ?
                                               ,[Image] = ?
                                               ,[Username] = ?
                                               ,[Password] = ?
                                               ,[Address] = ?
                                          WHERE CustomerID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customer.getFullName());
            st.setString(2, customer.getPhone());
            st.setString(3, customer.getEmail());
            st.setString(4, customer.getImage());
            st.setString(5, customer.getUsername());
            st.setString(6, customer.getPassword());
            st.setString(7, customer.getAddress());
            st.setInt(8, customer.getCustomerId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // DELETE A CUSTOMER
    public void DeleteCustomer(int customerId) {
        String sql = """
                    DELETE FROM [dbo].[Customers]
                          WHERE CustomerID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerId);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //  DEM SO LUONG CUSTOMER TRONG DATABASE 
    public int getTotalCustomer() {
        String sql = "select count(*) from Customers";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Customer> pagingCustomer(int index) {
        List<Customer> list = new ArrayList<>();
        String sql = """
                       SELECT * FROM Customers
                       ORDER BY CustomerID
                       OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setFullName(rs.getString("Fullname"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                customer.setImage(rs.getString("Image"));
                customer.setUsername(rs.getString("Username"));
                customer.setPassword(rs.getString("Password"));
                customer.setAddress(rs.getString("Address"));
                list.add(customer);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> list = customerDAO.pagingCustomer(1);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
