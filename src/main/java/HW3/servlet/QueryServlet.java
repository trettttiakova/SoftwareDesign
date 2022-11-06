package HW3.servlet;

import HW3.dto.Product;
import HW3.repository.ProductRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryServlet extends HttpServlet {
    ProductRepository productRepository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            Product maxPriceProduct = productRepository.getProductWithMaxPrice();
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with max price: </h1>");
            response.getWriter().println(
                maxPriceProduct.getName() + "\t" + maxPriceProduct.getPrice() + "</br>"
            );
            response.getWriter().println("</body></html>");
        } else if ("min".equals(command)) {
            Product minPriceProduct = productRepository.getProductWithMinPrice();
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with min price: </h1>");
            response.getWriter().println(
                minPriceProduct.getName() + "\t" + minPriceProduct.getPrice() + "</br>"
            );
            response.getWriter().println("</body></html>");
        } else if ("sum".equals(command)) {
            Long sum = productRepository.getProductPricesSum();
            response.getWriter().println("<html><body>");
            response.getWriter().println("Summary price: ");
            response.getWriter().println(sum);
            response.getWriter().println("</body></html>");
        } else if ("count".equals(command)) {
            Integer count = productRepository.getQuantity();
            response.getWriter().println("<html><body>");
            response.getWriter().println("Number of products: ");
            response.getWriter().println(count);
            response.getWriter().println("</body></html>");
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
