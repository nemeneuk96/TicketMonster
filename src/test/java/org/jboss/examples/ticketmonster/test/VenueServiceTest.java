package org.jboss.examples.ticketmonster.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;
import java.lang.NullPointerException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.examples.ticketmonster.model.Venue;
import org.jboss.examples.ticketmonster.rest.RESTDeployment;
import org.jboss.examples.ticketmonster.rest.VenueService;
import org.jboss.examples.ticketmonster.util.MultivaluedHashMap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;



public class VenueServiceTest {

	@Deployment
	public static WebArchive deployment()
	{
		return RESTDeployment.deployment();
	}
	@Inject
	private VenueService venueService;
	
	
	@Test
	public void testGetVenueById() 
	{
	Venue venue = venueService.getSingleInstance(1l);
	assertNotNull(venue);
	assertEquals("Roy Thomson Hall", venue.getName());
	}
	
	@Test
	public void testPagination() 
		{
		// Test pagination logic
		MultivaluedMap<String, String> queryParameters = new MultivaluedHashMap<String,
		String>();
		queryParameters.add("first", "2");
		queryParameters.add("maxResults", "1");
		List<Venue> venues = venueService.getAll(queryParameters);
		assertNotNull(venues);
		assertEquals(1, venues.size());
		assertEquals("Sydney Opera House", venues.get(0).getName());
		}
	


}

