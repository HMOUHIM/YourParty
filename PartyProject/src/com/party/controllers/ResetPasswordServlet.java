package com.party.controllers;
 
import java.io.IOException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.party.dao.UserDao;
import com.party.models.Mailer;
 

@WebServlet("/reset_password")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
  
    private UserDao userDao;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        userDao = new UserDao();
    }
 
    public ResetPasswordServlet() {
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String page = "forgot.jsp";
        request.getRequestDispatcher(page).forward(request, response);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	if(request.getParameter("backLoginReset") != null) {
    		response.sendRedirect("login");
    	}
    	if(request.getParameter("resetPassword")!= null) {
    		String email = request.getParameter("email");
            String recipient = request.getParameter("email");
            String subject = "Your Password has been reset";
     
            HttpSession session = request.getSession(true);
            String newPassword = userDao.resetCustomerPassword(recipient);
     
            String content = "Hi, this is your new password: " + newPassword;
            content += "\nNote: for security reason, "
                    + "you must change your password after logging in.";
            
            String message = " ";
     
            try {
                Mailer.send(email,subject, content);
                message = "Your password has been reset. Please check your e-mail.";
               
            } catch (Exception ex) {
                ex.printStackTrace();
                message = "There were an error: " + ex.getMessage();
               
            } finally {
            	
            	request.setAttribute("message", message);
                request.getRequestDispatcher("messageForgotPassword.jsp").forward(request, response);
            }
    		
    	}
    	
    }
 
}