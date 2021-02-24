package com.party.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.party.dao.EvenementDao;
import com.party.dao.ReservationDao;
import com.party.models.Evenement;

/**
 * Servlet implementation class welcome
 */
@WebServlet("/welcome")
public class welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private EvenementDao evenementDao;
	private ReservationDao reservationDao;
	public void init() {
		reservationDao = new ReservationDao();
		evenementDao = new EvenementDao();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> listDates = new ArrayList<String>();
		listDates = reservationDao.getListDateReservations();
		System.out.println("hellooooooooooooooooooooooooooo");
		request.setAttribute( "listDates", listDates);
		
		List<Evenement> evenementList = new ArrayList();
        evenementList = evenementDao.AfficherEvenement();
        request.setAttribute("evenementList", evenementList);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Evenement> evenementList = new ArrayList();
        evenementList = evenementDao.AfficherEvenement();
        request.setAttribute("evenementList", evenementList);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
	}
	

}
