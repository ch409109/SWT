package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.sql.Date;
import model.Product;

@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("productId");
        int productId;
        ProductDAO productDAO = new ProductDAO();
        try {
            productId = Integer.parseInt(id_raw);
            Product product = productDAO.GetProductById(productId);
            request.setAttribute("product", product);
            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String quantity_raw = request.getParameter("quantity");
        String status = request.getParameter("status");
        String category = request.getParameter("category");
        String price_raw = request.getParameter("price");
        String discount_raw = request.getParameter("discount");
        String desc = request.getParameter("description");
        int quantity, productId;
        double price, discount;
        Timestamp modifiedOn = new Timestamp(System.currentTimeMillis());

        try {
            ProductDAO productDAO = new ProductDAO();
            Date modifiedDate = new Date(modifiedOn.getTime());
            quantity = Integer.parseInt(quantity_raw);
            price = Double.parseDouble(price_raw);
            discount = Double.parseDouble(discount_raw);
            productId = Integer.parseInt(id_raw);

            Product oldProduct = productDAO.GetProductById(productId);

            Product updatedProduct = new Product();
            updatedProduct.setProductId(productId);
            updatedProduct.setCategory(oldProduct.getCategory());
            updatedProduct.setProductName(productName);
            updatedProduct.setQuantity(quantity);
            updatedProduct.setStatus(status);
            updatedProduct.setPrice(price);
            updatedProduct.setDiscount(discount);
            updatedProduct.setDescription(desc);

            updatedProduct.setImage(oldProduct.getImage());

            updatedProduct.setCreatedBy(oldProduct.getCreatedBy());
            updatedProduct.setCreatedOn(oldProduct.getCreatedOn());
            updatedProduct.setModifiedBy(oldProduct.getModifiedBy());
            updatedProduct.setModifiedOn(modifiedDate);

            productDAO.UpdateProduct(updatedProduct);
            response.sendRedirect("ListProductServlet");

        } catch (NumberFormatException e) {
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
