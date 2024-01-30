package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDAO extends DBContext {

    // GET ALL CATEGORIES
    public List<Category> GetAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = """
                     SELECT [CategoryID]
                           ,[CategoryName]
                           ,[CreatedBy]
                           ,[CreatedOn]
                           ,[ModifiedBy]
                           ,[ModifiedOn]
                       FROM [dbo].[Categories]""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCreatedBy(rs.getInt("CreatedBy"));
                category.setCreatedOn(rs.getDate("CreatedOn"));
                category.setModifiedBy(rs.getInt("ModifiedBy"));
                category.setModifiedOn(rs.getDate("ModifiedOn"));
                list.add(category);
            }
        } catch (SQLException e) {
            // Handle exception

        }

        return list;
    }

    // GET CATEGORY VIA CATEGORYID
    public Category GetCategoryById(int category_id) {
        String sql = """
                     SELECT [CategoryID]
                           ,[CategoryName]
                           ,[CreatedBy]
                           ,[CreatedOn]
                           ,[ModifiedBy]
                           ,[ModifiedOn]
                       FROM [dbo].[Categories]
                       WHERE CategoryID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, category_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCreatedBy(rs.getInt("CreatedBy"));
                category.setCreatedOn(rs.getDate("CreatedOn"));
                category.setModifiedBy(rs.getInt("ModifiedBy"));
                category.setModifiedOn(rs.getDate("ModifiedOn"));
                return category;
            }
        } catch (SQLException e) {
            // Handle exception

        }
        return null;
    }

    // INSERT A CATEGORY
    public void InsertCategory(Category category) {
        String sql = """
                     INSERT INTO [dbo].[Categories]
                                ([CategoryName], [CreatedBy], [CreatedOn], [ModifiedBy], [ModifiedOn])
                          VALUES(?, ?, ?, ?, ?)""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category.getCategoryName());
            st.setInt(2, category.getCreatedBy());
            st.setDate(3, category.getCreatedOn());
            st.setInt(4, category.getModifiedBy());
            st.setDate(5, category.getModifiedOn());
            st.executeUpdate();
        } catch (SQLException e) {
            // Handle exception

        }
    }

    // DELETE A CATEGORY
    public void DeleteCategory(int categoryId) {
        String sql = """
                     DELETE FROM [dbo].[Categories]
                           WHERE CategoryID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            st.executeUpdate();
        } catch (SQLException e) {
            // Handle exception

        }
    }

    // UPDATE A CATEGORY
    public void UpdateCategory(Category category) {
        String sql = """
                     UPDATE [dbo].[Categories]
                        SET [CategoryName] = ?,
                            [CreatedBy] = ?,
                            [CreatedOn] = ?,
                            [ModifiedBy] = ?,
                            [ModifiedOn] = ?
                      WHERE CategoryID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category.getCategoryName());
            st.setInt(2, category.getCreatedBy());
            st.setDate(3, category.getCreatedOn());
            st.setInt(4, category.getModifiedBy());
            st.setDate(5, category.getModifiedOn());
            st.setInt(6, category.getCategoryId());
            st.executeUpdate();
        } catch (SQLException e) {
            // Handle exception

        }
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> list = categoryDAO.GetAllCategory();
        for (Category category : list) {
            System.out.println(category.getCategoryName());
        }
    }
}
