package com.istic.rest;

import java.util.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.Response.Status;

@Path("/etudiants")
public class EtudiantRest {

	private static ArrayList<Etudiant> liste = new ArrayList<Etudiant>();

	public EtudiantRest() {
		if (liste.size() == 0) {
			liste.add(new Etudiant(1, "Ashref", 12.5));
			liste.add(new Etudiant(2, "Ahmed", 14));
			liste.add(new Etudiant(3, "Rayen", 15));
		}
	}

	// method to get all students
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		if (liste.size() == 0)
			return Response.status(Status.NO_CONTENT).entity(liste).build();
		else
			return Response.ok().entity(liste).build();
	}

	// method to get a student by ID
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEtudiantById(@PathParam("id") int id) {
		for (Etudiant etudiant : liste) {
			if (etudiant.getId() == id) {
				return Response.ok().entity(etudiant).build();
			}
		}
		return Response.status(Status.NOT_FOUND).entity("Student with ID " + id + " not found").build();
	}

	// method to create a student
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEtudiant(Etudiant etudiant) {
		liste.add(etudiant);
		return Response.status(Status.CREATED).entity("Student created with ID " + etudiant.getId()).build();
	}

	// New method to update a student
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEtudiant(@PathParam("id") int id, Etudiant updatedEtudiant) {
		for (int i = 0; i < liste.size(); i++) {
			Etudiant etudiant = liste.get(i);
			if (etudiant.getId() == id) {
				updatedEtudiant.setId(id);
				liste.set(i, updatedEtudiant);
				return Response.ok().entity("Student with ID " + id + " updated").build();
			}
		}
		return Response.status(Status.NOT_FOUND).entity("Student with ID " + id + " not found").build();
	}

	// method to delete a student
	@DELETE
	@Path("/{id}")
	public Response deleteEtudiant(@PathParam("id") int id) {
		for (Etudiant etudiant : liste) {
			if (etudiant.getId() == id) {
				liste.remove(etudiant);
				return Response.ok().entity("Student with ID " + id + " deleted").build();
			}
		}
		return Response.status(Status.NOT_FOUND).entity("Student with ID " + id + " not found").build();
	}
}
