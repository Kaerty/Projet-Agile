package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import beans.Admin;
import beans.User;

import java.security.SecureRandom; 

	


/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
       // utiliser bon-abc
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		this.getServletContext().getRequestDispatcher("/jsp/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String mdp = request.getParameter("mdp");
		
		Admin a = new Admin();
		a.select();
		if (a.getEmail().equals(id)) { // essaye de log en admin
			if (BCrypt.checkpw(mdp,a.getPassword())) { // admin connecté
				System.out.println("Admin connecté");
				
			}
			else { // admin trompé de mdp
				
			}
		} 
		else {	// un stagiaire ou un formateur
		User f = new User();
		f.setIntern(false);
		ArrayList<User> formers = (ArrayList<User>) f.selectAll();
		boolean exist = false;
		for (User u : formers) {
			if (u.getEmail().equals(id) || u.getSurname().equals(id)) {
				f.setIdUser(u.getIdUser());
				exist = true;
				f = u;
			}
		}
			
		if (exist && BCrypt.checkpw(mdp,f.getPassword())) { // le formateur est connecté
			System.out.println("formateur connecté");
			HttpSession s = request.getSession(true);
			
			
		}
		else {
		f.setIntern(true);
		ArrayList<User> interns = (ArrayList<User>) f.selectAll();
		
		for (User u : interns) {
			if (u.getEmail().equals(id) || u.getSurname().equals(id)) {
				f.setIdUser(u.getIdUser());
				exist = true;
				f = u;
			}
		}
			if (exist && BCrypt.checkpw(mdp,f.getPassword())) { // le stagiaire est connecté
				System.out.println("stagiaire connecté");
				HttpSession s = request.getSession(true);
				s.setAttribute("iduser",f.getIdUser());
				s.setAttribute("idformation",f.getIdFormation());
				response.sendRedirect(request.getContextPath()+"/menu_formations");
				
			}
			else {
				System.out.println("connexion non possible");
			}
		
		}
		}
	}
	
	

}
