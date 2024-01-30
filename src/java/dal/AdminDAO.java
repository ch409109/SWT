package dal;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO extends DBContext {

    // GET ALL ADMINS
    public List<Admin> GetAllAdmin() {
        List<Admin> list = new ArrayList<>();
        String sql = """
                     SELECT [AdminID]
                           ,[Fullname]
                           ,[Username]
                           ,[Password]
                           ,[Email]
                           ,[Phone]
                           ,[Role]
                           ,[Image]
                           ,[JoinedDate]
                           ,[Address]
                           ,[Department]
                       FROM [dbo].[Admin]""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("AdminID"));
                admin.setFullName(rs.getString("Fullname"));
                admin.setUsername(rs.getString("Username"));
                admin.setPassword(rs.getString("Password"));
                admin.setEmail(rs.getString("Email"));
                admin.setPhone(rs.getString("Phone"));
                admin.setRole(rs.getString("Role"));
                admin.setImage(rs.getString("Image"));
                admin.setJoinedDate(rs.getDate("JoinedDate"));
                admin.setAddress(rs.getString("Address"));
                admin.setDepartment(rs.getString("Department"));
                list.add(admin);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // GET ADMIN BY ID
    public Admin GetAdminById(int adminId) {
        String sql = """
                     SELECT [AdminID]
                           ,[Fullname]
                           ,[Username]
                           ,[Password]
                           ,[Email]
                           ,[Phone]
                           ,[Role]
                           ,[Image]
                           ,[JoinedDate]
                           ,[Address]
                           ,[Department]
                       FROM [dbo].[Admin]
                       WHERE AdminID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, adminId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setFullName(rs.getString("Fullname"));
                admin.setUsername(rs.getString("Username"));
                admin.setPassword(rs.getString("Password"));
                admin.setEmail(rs.getString("Email"));
                admin.setPhone(rs.getString("Phone"));
                admin.setRole(rs.getString("Role"));
                admin.setImage(rs.getString("Image"));
                admin.setJoinedDate(rs.getDate("JoinedDate"));
                admin.setAddress(rs.getString("Address"));
                admin.setDepartment(rs.getString("Department"));
                return admin;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // INSERT AN ADMIN
    public void InsertAdmin(Admin admin) {
        String sql = """
                     INSERT INTO [dbo].[Admin]
                                ([Fullname]
                                ,[Username]
                                ,[Password]
                                ,[Email]
                                ,[Phone]
                                ,[Role]
                                ,[Image]
                                ,[JoinedDate]
                                ,[Address]
                                ,[Department])
                          VALUES (?,?,?,?,?,?,?,?,?,?)""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, admin.getFullName());
            st.setString(2, admin.getUsername());
            st.setString(3, admin.getPassword());
            st.setString(4, admin.getEmail());
            st.setString(5, admin.getPhone());
            st.setString(6, admin.getRole());
            st.setString(7, admin.getImage());
            st.setDate(8, admin.getJoinedDate());
            st.setString(9, admin.getAddress());
            st.setString(10, admin.getDepartment());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // UPDATE AN ADMIN
    public void UpdateAdmin(Admin admin) {
        String sql = """
                     UPDATE [dbo].[Admin]
                        SET [Fullname] = ?
                           ,[Username] = ?
                           ,[Password] = ?
                           ,[Email] = ?
                           ,[Phone] = ?
                           ,[Role] = ?
                           ,[Image] = ?
                           ,[JoinedDate] = ?
                           ,[Address] = ?
                           ,[Department] = ?
                      WHERE AdminID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, admin.getFullName());
            st.setString(2, admin.getUsername());
            st.setString(3, admin.getPassword());
            st.setString(4, admin.getEmail());
            st.setString(5, admin.getPhone());
            st.setString(6, admin.getRole());
            st.setString(7, admin.getImage());
            st.setDate(8, admin.getJoinedDate());
            st.setString(9, admin.getAddress());
            st.setString(10, admin.getDepartment());
            st.setInt(11, admin.getAdminId());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // DELETE AN ADMIN
    public void DeleteAdmin(int adminId) {
        String sql = """
                     DELETE FROM [dbo].[Admin]
                           WHERE AdminID = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, adminId);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //  DEM SO LUONG PRODUCT TRONG DATABASE 
    public int getTotalAdmin() {
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

    public List<Admin> pagingAdmin(int index) {
        List<Admin> list = new ArrayList<>();
        String sql = """
                       SELECT * FROM Admin
                       ORDER BY AdminID
                       OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("AdminID"));
                admin.setFullName(rs.getString("Fullname"));
                admin.setUsername(rs.getString("Username"));
                admin.setPassword(rs.getString("Password"));
                admin.setEmail(rs.getString("Email"));
                admin.setPhone(rs.getString("Phone"));
                admin.setRole(rs.getString("Role"));
                admin.setImage(rs.getString("Image"));
                admin.setJoinedDate(rs.getDate("JoinedDate"));
                admin.setAddress(rs.getString("Address"));
                admin.setDepartment(rs.getString("Department"));
                list.add(admin);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
