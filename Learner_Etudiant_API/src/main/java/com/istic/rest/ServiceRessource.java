package com.istic.rest;

import java.util.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Stateless
@Path("learners")
public class ServiceRessource {

	@PersistenceContext(unitName = "TP2_EX1")
	private EntityManager em;

	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response getAllLearners() {
		List<Learner> learners = new ArrayList<Learner>();
		TypedQuery<Learner> query = em.createNamedQuery("Learner.findAll", Learner.class);
		learners = query.getResultList();

		if (learners.size() != 0) {
			return Response.status(Status.OK).entity(learners).build();
		} else {
			return Response.status(Status.OK).entity("There is no saved learner in the DB").build();
		}
	}

	// get learner by ID
	@GET
	@Path("{id}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response getLearnerById(@PathParam("id") int id) {
		Learner learner = em.createNamedQuery("Learner.findById", Learner.class).setParameter("learnerId", id)
				.getSingleResult();
		if (learner != null) {
			return Response.status(Status.OK).entity(learner).build();
		} else {
			return Response.status(Status.NOT_FOUND).entity("Learner with ID " + id + " not found").build();
		}
	}

	// create a learner
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response createLearner(Learner learner) {
		em.createNamedQuery("Learner.create").setParameter("name", learner.getName())
				.setParameter("email", learner.getEmail()).setParameter("city", learner.getCity()).executeUpdate();
		return Response.status(Status.CREATED).entity("Learner created successfully").build();
	}

	// update learner data
	@PUT
	@Path("{id}")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response updateLearner(@PathParam("id") int id, Learner updatedLearner) {
		int rowsUpdated = em.createNamedQuery("Learner.update").setParameter("learnerId", id)
				.setParameter("name", updatedLearner.getName()).setParameter("email", updatedLearner.getEmail())
				.setParameter("city", updatedLearner.getCity()).executeUpdate();

		if (rowsUpdated > 0) {
			return Response.status(Status.OK).entity("Learner with ID " + id + " updated successfully").build();
		} else {
			return Response.status(Status.NOT_FOUND).entity("Learner with ID " + id + " not found").build();
		}
	}

	// delete a learner
	@DELETE
	@Path("{id}")
	public Response deleteLearner(@PathParam("id") int id) {
		int rowsDeleted = em.createNamedQuery("Learner.delete").setParameter("learnerId", id).executeUpdate();

		if (rowsDeleted > 0) {
			return Response.status(Status.NO_CONTENT).build();
		} else {
			return Response.status(Status.NOT_FOUND).entity("Learner with ID " + id + " not found").build();
		}
	}

}
