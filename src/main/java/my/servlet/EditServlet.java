/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my.model.DataConnect;

/**
 *
 * @author Admin
 */
public class EditServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String method = request.getMethod();
            if (method.equalsIgnoreCase("get")) {
                String id = request.getParameter("id");
                
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    conn = DataConnect.connect();

                    ps = conn.prepareStatement("select * from users where id=" + id);
//                ps.setInt(1, Integer.parseInt(id));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        String country = rs.getString("country");
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet EditServlet</title>");
                        out.println("</head>");
                        out.println("<body>"
                                + "<h1>Update user</h1>"
                                + "<form action=\"EditServlet\" method=\"POST\">\n"
                                + "<input type ='hidden' name ='id' value =" + id + " />"
                                + "            <table border=\"0\">\n"
                                + "                <tbody>\n"
                                + "                    <tr>\n"
                                + "                        <td>Name</td>\n"
                                + "                        <td>\n"
                                + "                            <input type=\"text\" name=\"username\" value=" + rs.getString("name") + " />\n"
                                + "                        </td>\n"
                                + "                    </tr>\n"
                                + "                    <tr>\n"
                                + "                        <td>Password</td>\n"
                                + "                        <td>\n"
                                + "                            <input type=\"password\" name = 'password' value=" + rs.getString("password") + " />\n"
                                + "                        </td>\n"
                                + "\n"
                                + "                    </tr>\n"
                                + "                    <tr>\n"
                                + "                        <td>Email</td>\n"
                                + "                        <td>\n"
                                + "                            <input type=\"text\" value=" + rs.getString("email") + " />\n"
                                + "                        </td>\n"
                                + "                    </tr>\n"
                                + "                    <tr>\n"
                                + "                        <td>Country</td>\n"
                                + "                        <td>\n"
                                + "                            <select name=\"country\">\n"
                                + "                                <option value ='Vietnamese' " + (country.equals("Vietnamese")?"selected":"") + " >Vietnamese</option>\n"
                                + "                                <option value ='American' " + (country.equals("American")?"selected":"") + " >American</option>\n"
                                + "                                <option value ='Japanese' " + (country.equals("Japanese")?"selected":"") + " >Japanese</option>\n"
                                + "                                <option value ='Chinese' " + (country.equals("Chinese")?"selected":"") + " >Chinese</option>\n"
                                + "                            </select>\n"
                                + "                        </td>\n"
                                + "                    </tr>\n"
                                + "                </tbody>\n"
                                + "            </table>\n"
                                + "            <input type=\"submit\" value=\"Edit & Save\" />\n"
                                + "        </form><br>  \n"
                                + "        <a href=\"ViewServlet\">View users</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    conn.close();
                } catch (Exception e) {
                    out.println("Loi " + e.toString());
                }
            } else if (method.equalsIgnoreCase("post")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String country = request.getParameter("country");
                String id = request.getParameter("id");

                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = DataConnect.connect();

                    ps = conn.prepareStatement("update users set name=?, password=?, email=?, country=? where id=?");
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, email);
                    ps.setString(4, country);
                    ps.setInt(5, Integer.parseInt(id));

                    int kq = ps.executeUpdate();
                    if (kq > 0) {
                        out.println("<h2>Sửa user thành công</h2>");
                        request.getRequestDispatcher("ViewServlet").include(request, response);
                    } else {
                        out.println("<h2>Sửa user thất bại</h2>");
                    }
                    conn.close();
                } catch (Exception e) {
                    out.println("Loi " + e.toString());
                    out.print("Sửa user thất bại");
                }
            }
            /* TODO output your page here. You may use following sample code. */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
