package ua.at.mamdouh.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import ua.at.mamdouh.utils.DbHelper;
import ua.at.mamdouh.utils.Hit;

@Path("/")
public class RMRESTService {

	@POST
	@Path("/RMservice")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crunchifyREST(InputStream incomingData) {

		StringBuilder crunchifyBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + crunchifyBuilder.toString());

		JSONObject obj = new JSONObject(crunchifyBuilder);

		String sender = null;
		int read = 0;
		try {

			sender = obj.getString("id");
			read = obj.getInt("read");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("here...........................");

		System.out.println(obj);

		DbHelper.getInstance().insert(sender, read);

		// return HTTP response 200 in case of success
		return Response.status(200).entity(crunchifyBuilder.toString()).build();
	}

	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "RM Service Successfully started..";

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/getLast")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getLastRESTService(InputStream incomingData) {

		String result = "Suppose Get Last........";

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

	// --------------------------

	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(@PathParam("year") int year, @PathParam("month") int month,
			@PathParam("day") int day) {

		String date = year + "/" + month + "/" + day;

		return Response.status(200).entity("getUserHistory is called, year/month/day : " + date).build();

	}

	// ------------------------

	@GET
	@Path("/query")
	public Response getUsers(@QueryParam("from") int from, @QueryParam("to") int to,
			@QueryParam("orderBy") List<String> orderBy) {

		return Response.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to + ", orderBy" + orderBy.toString())
				.build();

	}

	// ---------- my job ----------------------------

	// -------- get status if someone send something .........................

	@GET
	@Path("/check")
	public Response getStatus(@QueryParam("from") String from) {

		boolean res = false;

		if (from.equals("R") && Hit.getInstance().isM()) {

			res = true;
		}

		if (from.equals("M") && Hit.getInstance().isR()) {

			res = true;
		}
		
		//---------------  count ----------------------------------------
		
		int count = 0;

		if (from.equals("M")) {

			count = Hit.getInstance().getCountR();
		}

		if (from.equals("R")) {

			count = Hit.getInstance().getCountM();
		}
		
		String strBool = Boolean.toString(res);
		
		String strCount = Integer.toString(count);
		
		return Response.status(200).entity(strBool + ":" + strCount).build();
		
//		return Response.status(200).entity("check from : " + from + " , status:" + res).build();

	}

	// -------- send hit to server .........................

	@GET
	@Path("/send")
	public Response setSend(@QueryParam("from") String from) {

		if (from.equals("R")) {

			Hit.getInstance().setR(true);
			
			Hit.getInstance().setCountR(Hit.getInstance().getCountR()+1);
		}

		if (from.equals("M")) {

			Hit.getInstance().setM(true);
			
			Hit.getInstance().setCountM(Hit.getInstance().getCountM()+1);
		}

		return Response.status(200).entity("sent from : " + from).build();
		
///		return Response.status(200).entity(from).build();

	}
	
	//------------------  count R and M ----------------
	
	
	@GET
	@Path("/count")
	public Response getCount(@QueryParam("to") String to) {
		
		int count = 0;

		if (to.equals("M")) {

			count = Hit.getInstance().getCountR();
		}

		if (to.equals("R")) {

			count = Hit.getInstance().getCountM();
		}

//		return Response.status(200).entity("count to : " + to + " , is:" + count).build();
		
		String str = Integer.toString(count);
		
		return Response.status(200).entity(str).build();

	}
	
	
	//--------------- reset count ----------------------------
	
	@GET
	@Path("/reset")
	public Response resetCount(@QueryParam("from") String from) {

		if (from.equals("R")) {
			
			Hit.getInstance().resetCountM();
			
		}

		if (from.equals("M")) {

			Hit.getInstance().resetCountR();
		}

		return Response.status(200).entity("reset  : " + from).build();

	}
	

}