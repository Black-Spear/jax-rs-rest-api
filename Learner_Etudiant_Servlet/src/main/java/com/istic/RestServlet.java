package com.istic;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

/**
 * Servlet implementation class RestServlet
 */
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Client client = ClientBuilder.newBuilder().build();
		WebTarget base = client.target("http://localhost:8080/Learner_Etudiant_API/RestApi/etudiants/");
		String id = request.getParameter("id");
		WebTarget target = base.path("/" + id);

		Response resp = target.request().get();
		if (resp.getStatus() != 200) {
			String errorEntity = null;
			if (resp.hasEntity()) {
				resp.bufferEntity();
				errorEntity = resp.readEntity(String.class);
				response.getWriter().append(errorEntity);
			}
		} else {
			resp.bufferEntity();
			JsonObject jsonResp = resp.readEntity(JsonObject.class);
			response.getWriter().append(jsonResp.toString());
		}

		response.getWriter().append("\nServed at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.getParameter("paramName")
		String id = request.getParameter("id");
		double moyenne = Double.parseDouble(request.getParameter("moyenne"));
		String nom = request.getParameter("nom");

		// Create a JSON object to send to your REST service
		JsonObject json = Json.createObjectBuilder().add("id", id).add("moyenne", moyenne).add("nom", nom).build();

		// Make a POST request to your REST service
		Client client = ClientBuilder.newBuilder().build();
		WebTarget base = client.target("http://localhost:8080/Learner_Etudiant_API/RestApi/etudiants/");
		Response resp = base.request().post(Entity.json(json));

		// Check the response and handle it as needed
		if (resp.getStatus() == 201) {
			// Successful creation
			response.getWriter().append("Student created successfully");
		} else {
			// Handle errors or response as needed
			String errorEntity = null;
			if (resp.hasEntity()) {
				resp.bufferEntity();
				errorEntity = resp.readEntity(String.class);
				response.getWriter().append(errorEntity);
			}
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		double moyenne = Double.parseDouble(request.getParameter("moyenne"));
		String nom = request.getParameter("nom");

		JsonObject json = Json.createObjectBuilder().add("id", id).add("moyenne", moyenne).add("nom", nom).build();

		Client client = ClientBuilder.newBuilder().build();
		WebTarget base = client.target("http://localhost:8080/Learner_Etudiant_API/RestApi/etudiants");
		WebTarget target = base.path(id); // Assuming ID is the resource identifier

		Response resp = target.request().put(Entity.json(json));

		if (resp.getStatus() == 200) {
			response.getWriter().append("Student with ID " + id + " updated successfully");
		} else {
			String errorEntity = null;
			if (resp.hasEntity()) {
				resp.bufferEntity();
				errorEntity = resp.readEntity(String.class);
				response.getWriter().append(errorEntity);
			}
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		Client client = ClientBuilder.newBuilder().build();
		WebTarget base = client.target("http://localhost:8080/Learner_Etudiant_API/RestApi/etudiants");
		WebTarget target = base.path(id); // Assuming ID is the resource identifier

		Response resp = target.request().delete();

		if (resp.getStatus() == 204) {
			response.getWriter().append("Student with ID " + id + " deleted successfully");
		} else {
			String errorEntity = null;
			if (resp.hasEntity()) {
				resp.bufferEntity();
				errorEntity = resp.readEntity(String.class);
				response.getWriter().append(errorEntity);
			}
		}
	}

}
