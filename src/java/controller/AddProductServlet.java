package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Timestamp;
import java.util.Calendar;
import java.sql.Date;
import model.Category;
import model.Product;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ListCategoryServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String productName = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            double price = Double.parseDouble(request.getParameter("price"));
            String image = request.getParameter("ImageUpload");
            int createdBy = Integer.parseInt(request.getParameter("creator"));
            String status = request.getParameter("status");
            String description = request.getParameter("description");

            Product product = new Product();
            product.setProductName(productName);
            product.setQuantity(quantity);

            Category category = new Category();
            category.setCategoryId(categoryId);
            product.setCategory(category);

            product.setPrice(price);
            product.setImage(null);
            product.setCreatedBy(createdBy);
            product.setStatus(status);
            product.setDescription(description);

            product.setCreatedOn(new Date(System.currentTimeMillis()));

            ProductDAO productDAO = new ProductDAO();
            productDAO.InsertProduct(product);

            response.sendRedirect("ListProductServlet");
        } catch (IOException | NumberFormatException e) {
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
