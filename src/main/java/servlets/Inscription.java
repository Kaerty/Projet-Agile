package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import beans.Formation;
import beans.User;

/**
 * Servlet implementation class Inscription
 */

@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean canSub = false;
		if (request.getParameter("user") != null) {
			if (BCrypt.checkpw("1",request.getParameter("user"))) { // inscription former
				System.out.println("vous etes un formateur");
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
				String date = ymd.format(cal.getTime());
				
				 if (request.getParameter("time") != null && BCrypt.checkpw(date, request.getParameter("time"))){
					 System.out.println("yay le formateur peut senregistrer");
					Formation form = new Formation();
					 ArrayList<Formation> formations = (ArrayList<Formation>) new Formation().selectAll();
					 
					 for (Formation f : formations) {
						 if (BCrypt.checkpw(String.valueOf(f.getIdFormation()), request.getParameter("form"))) {
							 System.out.println("il est dans la bonne formation");
							 form = f;
							 canSub = true;
						 }
								 
						 else
							 System.out.println("echec");
					 }
					request.setAttribute("cansub",canSub);
					request.setAttribute("form",form.getIdFormation());
					request.setAttribute("typeinscription",1);
					
				 }
				
				
			}
			else if (BCrypt.checkpw("2",request.getParameter("user"))) { // inscription intern
				System.out.println("vous etes un stagiaire");
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
				String date = ymd.format(cal.getTime());
				 if (request.getParameter("time") != null && BCrypt.checkpw(date, request.getParameter("time"))){
					 System.out.println("yay le stagiaire peut senregistrer");
					 
					 Formation form = new Formation();
					 ArrayList<Formation> formations = (ArrayList<Formation>) new Formation().selectAll();
					 
					 for (Formation f : formations) {
						 if (BCrypt.checkpw(String.valueOf(f.getIdFormation()), request.getParameter("form"))) {
							 System.out.println("il est dans la bonne formation");
							 form = f;
							 canSub = true;
						 }
								 
						 else
							 System.out.println("echec");
					 }
					request.setAttribute("cansub",canSub);
					request.setAttribute("form",form.getIdFormation());
					request.setAttribute("typeinscription",2);
					
				 }
			}
			else
				request.setAttribute("cansub",canSub);

		}
		else {
			System.out.println("Vous n'avez rien à faire ici !");
			request.setAttribute("cansub",canSub);
		}
		String cs = String.valueOf(request.getAttribute("cansub"));
		int typeInscription = (int) request.getAttribute("typeinscription");
		System.out.println("can sub ? = " + canSub + " " + typeInscription);
		this.getServletContext().getRequestDispatcher("/jsp/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				int typeInscription = Integer.parseInt(request.getParameter("idformation"));
				User user = new User(typeInscription == 1 ? false:true);
				
				user.setSurname(request.getParameter("surname"));
				user.setName(request.getParameter("name"));
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("password"));
				user.setPhone(request.getParameter("phone"));
				user.setAdress(request.getParameter("adress"));
				user.setGender(request.getParameter("gender"));
				user.setIdFormation(typeInscription);
				
				user.insert();	
				System.out.println("stagiaire ajoutée");
		
				response.sendRedirect(request.getContextPath()+"/connexion");
	
	}
}