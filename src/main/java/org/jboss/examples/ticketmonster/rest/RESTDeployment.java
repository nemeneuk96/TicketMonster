package org.jboss.examples.ticketmonster.rest;

import org.jboss.examples.ticketmonster.model.Booking;
import org.jboss.examples.ticketmonster.service.SeatAllocationService;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.examples.ticketmonster.test.TicketMonsterDeployment;
import org.jboss.examples.ticketmonster.util.MultivaluedHashMap;

public class RESTDeployment {

	public static WebArchive deployment() {
		return TicketMonsterDeployment.deployment()
		.addPackage(Booking.class.getPackage())
		.addPackage(BaseEntityService.class.getPackage())
		.addPackage(MultivaluedHashMap.class.getPackage())
		.addPackage(SeatAllocationService.class.getPackage());
		}

}
