package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import dto.AddressDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import dto.PhonesDTO;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/ca2",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        return GSON.toJson(FACADE.getAllPersons());
    }

    @Path("id/{value}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonByID(@PathParam("value") int value) {
        return GSON.toJson(FACADE.getPerson(value));
    }
    
    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPerson(String json) {
    Person persDTO = GSON.fromJson(json, Person.class);
    Person persistedPers = FACADE.addPerson(persDTO.getEmail(),persDTO.getFirstName(), persDTO.getLastName());
    return GSON.toJson(persistedPers);
    }



    @Path("/edit")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPerson(String person) throws IOException {
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        //AddressDTO aDTO = GSON.fromJson(addressDTO, AddressDTO.class);
        //PhonesDTO pDTO = GSON.fromJson(phoneDTO, PhonesDTO.class);
        
            PersonDTO responseDTO = FACADE.editPerson(personDTO);

            return Response.ok(responseDTO).build();
        

    }


}
