package dal;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;
import model.Customer;

public class ProductDAO extends DBContext {

    // GET ALL PRODUCTS
    public List<Product> GetAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = """
                     SELECT [ProductID]
                           ,[CategoryID]
                           ,[ProductName]
                           ,[Image]
                           ,[Description]
                           ,[Price]
                           ,[Quantity]
                           ,[Status]
                           ,[Discount]
                           ,[CreatedBy]
                           ,[CreatedOn]
                           ,[ModifiedBy]
                           ,[ModifiedOn]
                       FROM [dbo].[Products]""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                CategoryDAO categoryDAO = new CategoryDAO();
                Category category = categoryDAO.GetCategoryById(rs.getInt("CategoryID"));
                product.setCategory(category);
                product.setProductName(rs.getString("ProductName"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getString("Status"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setCreatedBy(rs.getInt("CreatedBy"));
                product.setCreatedOn(rs.getDate("CreatedOn"));
                product.setModifiedBy(rs.getInt("ModifiedBy"));
                product.setModifiedOn(rs.getDate("ModifiedOn"));
                list.add(product);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // GET PRODUCT BY ID
    public Product GetProductById(int productId) {
        String sql = """
                     SELECT [ProductID]
                           ,[CategoryID]
                           ,[ProductName]
                           ,[Image]
                           ,[Description]
                           ,[Price]
                           ,[Quantity]
                           ,[Status]
                           ,[Discount]
                           ,[CreatedBy]
                           ,[CreatedOn]
                           ,[ModifiedBy]
                           ,[ModifiedOn]
                       FROM [dbo].[Products]
                       WHERE ProductID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                CategoryDAO categoryDAO = new CategoryDAO();
                Category category = categoryDAO.GetCategoryById(rs.getInt("CategoryID"));
                product.setCategory(category);
                product.setProductName(rs.getString("ProductName"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getString("Status"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setCreatedBy(rs.getInt("CreatedBy"));
                product.setCreatedOn(rs.getDate("CreatedOn"));
                product.setModifiedBy(rs.getInt("ModifiedBy"));
                product.setModifiedOn(rs.getDate("ModifiedOn"));
                return product;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // INSERT PRODUCT
    public void InsertProduct(Product product) {
        String sql = """
                     INSERT INTO [dbo].[Products]
                                ([CategoryID]
                                ,[ProductName]
                                ,[Image]
                                ,[Description]
                                ,[Price]
                                ,[Quantity]
                                ,[Status]
                                ,[Discount]
                                ,[CreatedBy]
                                ,[CreatedOn]
                                ,[ModifiedBy]
                                ,[ModifiedOn])
                          VALUES (?,?,?,?,?,?,?,?,?,?,?,?)""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product.getCategory().getCategoryId());
            st.setString(2, product.getProductName());
            st.setString(3, product.getImage());
            st.setString(4, product.getDescription());
            st.setDouble(5, product.getPrice());
            st.setInt(6, product.getQuantity());
            st.setString(7, product.getStatus());
            st.setDouble(8, product.getDiscount());
            st.setInt(9, product.getCreatedBy());
            st.setDate(10, product.getCreatedOn());
            st.setInt(11, product.getModifiedBy());
            st.setDate(12, product.getModifiedOn());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // UPDATE PRODUCT
    public void UpdateProduct(Product product) {
        String sql = """
                     UPDATE [dbo].[Products]
                        SET [CategoryID] = ?
                           ,[ProductName] = ?
                           ,[Image] = ?
                           ,[Description] = ?
                           ,[Price] = ?
                           ,[Quantity] = ?
                           ,[Status] = ?
                           ,[Discount] = ?
                           ,[CreatedBy] = ?
                           ,[CreatedOn] = ?
                           ,[ModifiedBy] = ?
                           ,[ModifiedOn] = ?
                      WHERE ProductID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product.getCategory().getCategoryId());
            st.setString(2, product.getProductName());
            st.setString(3, product.getImage());
            st.setString(4, product.getDescription());
            st.setDouble(5, product.getPrice());
            st.setInt(6, product.getQuantity());
            st.setString(7, product.getStatus());
            st.setDouble(8, product.getDiscount());
            st.setInt(9, product.getCreatedBy());
            st.setDate(10, product.getCreatedOn());
            st.setInt(11, product.getModifiedBy());
            st.setDate(12, product.getModifiedOn());
            st.setInt(13, product.getProductId());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // DELETE PRODUCT
    public void DeleteProduct(int productId) {
        String sql = """
                     DELETE FROM [dbo].[Products]
                           WHERE ProductID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //  DEM SO LUONG PRODUCT TRONG DATABASE 
    public int getTotalProduct() {
        String sql = "select count(*) from Products";
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

    public List<Product> pagingProduct(int index) {
        List<Product> list = new ArrayList<>();
        String sql = """
                       SELECT * FROM Products
                       ORDER BY ProductID
                       OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                CategoryDAO categoryDAO = new CategoryDAO();
                Category category = categoryDAO.GetCategoryById(rs.getInt("CategoryID"));
                product.setCategory(category);
                product.setProductName(rs.getString("ProductName"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getString("Status"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setCreatedBy(rs.getInt("CreatedBy"));
                product.setCreatedOn(rs.getDate("CreatedOn"));
                product.setModifiedBy(rs.getInt("ModifiedBy"));
                product.setModifiedOn(rs.getDate("ModifiedOn"));
                list.add(product);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
