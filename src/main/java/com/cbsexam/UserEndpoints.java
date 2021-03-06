package com.cbsexam;

import com.google.gson.Gson;
import controllers.UserController;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;
import utils.Encryption;
import utils.Log;

//Alle de endpoints som er fra userklassen, bruker path "user"
@Path("user")
public class UserEndpoints {

  /**
   * @param idUser
   * @return Responses
   */
  // her bruker man bath user/iduser
  @GET
  @Path("/{idUser}")
  public Response getUser(@PathParam("idUser") int idUser) {

    // Use the ID to get the user from the controller.
    User user = UserController.getUser(idUser);

    // TODO: Add Encryption to JSON : FIX
    // Convert the user object to json in order to return the object
    String json = new Gson().toJson(user);

    /* SLIK KAN MAN KALLE PÅ METODEN I UTILS ENCRYPTION, OG KYRPTERE STRING AV JSON
     json = Encryption.encryptDecryptXOR(json);
                                                  */

    /* Man kunne forestille seg at det skal returneres noe annet?? Hvis man ikke kunne finne det id, så kan brugeren ha noe annet en noe tomt svar. Gjelde de andre endpoints hvis det er, showcase */
    // Return the user with the status code 200
    // TODO: What should happen if something breaks down?
    return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
  }

  /** @return Responses */
  @GET
  @Path("/")
  public Response getUsers() {

    // Write to log that we are here
    Log.writeLog(this.getClass().getName(), this, "Get all users", 0);

    // Get a list of users
    ArrayList<User> users = UserController.getUsers();

    // TODO: Add Encryption to JSON :FIX

    // Transfer users to json in order to return it to the user
    String json = new Gson().toJson(users);


    /* SLIK KAN MAN KALLE PÅ METODEN I UTILS ENCRYPTION, OG KYRPTERE STRING AV JSON
     json = Encryption.encryptDecryptXOR(json);
                                                  */


    // Return the users with the status code 200
    return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json).build();
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createUser(String body) {

    // Read the json from body and transfer it to a user class
    User newUser = new Gson().fromJson(body, User.class);

    // Use the controller to add the user
    User createUser = UserController.createUser(newUser);

    // Get the user back with the added ID and return it to the user
    String json = new Gson().toJson(createUser);

    // Return the data to the user
    if (createUser != null) {
      // Return a response with status 200 and JSON as type
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
    } else {
      return Response.status(400).entity("Could not create user").build();
    }
  }
  // TRE Endpoints som ikke er laget enda, implementer logikken, endpointet er der,
  // Hva skal man ha som funksjonalitet!! LOGIN, DELETE OG UPDATE

  // TODO: Make the system able to login users and assign them a token to use throughout the system.
  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response loginUser(String x) {

    // Return a response with status 200 and JSON as type
    return Response.status(400).entity("Endpoint not implemented yet").build();
  }


  // TODO: Make the system able to delete users
  public Response deleteUser(String x) {

    // Return a response with status 200 and JSON as type
    return Response.status(400).entity("Endpoint not implemented yet").build();
  }

  // TODO: Make the system able to update users
  public Response updateUser(String x) {

    // Return a response with status 200 and JSON as type
    return Response.status(400).entity("Endpoint not implemented yet").build();
  }
}
