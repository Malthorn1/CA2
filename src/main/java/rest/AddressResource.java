package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AddressDTO;
import dto.PersonDTO;
import entities.Person;
import exceptions.AddressNotFoundException;
import exceptions.PersonNotFoundException;
import facades.AddressFacade;
import utils.EMF_Creator;
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
@Path("address")
public class AddressResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca2",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final AddressFacade FACADE =  AddressFacade.getFacadeExample(EMF);
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
        long count = FACADE.getAddressCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
//    
//    @GET
//    @Path("{number}")
//    @Produces({MediaType.APPLICATION_JSON}) 
//    public String getPersonByPhoneNumber(@PathParam("number") String number) {
//        return GSON.toJson(FACADE.getPersonByPhoneNumber(number)); 
//    }
    
    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_JSON}) 
    public String addAddress(String address){
        AddressDTO aDTO = GSON.fromJson(address, AddressDTO.class);
        AddressDTO perAddress = FACADE.addAddress(aDTO.getStreet(), aDTO.getAdditionalInfo(), aDTO.getpDTOID());
        return GSON.toJson(perAddress);
    }
    
    @POST
    @Path("add/{id}")
    @Produces({MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_JSON}) 
    public String addAddressToId(@PathParam("id") int id, String address){
        AddressDTO aDTO = GSON.fromJson(address, AddressDTO.class);
        aDTO.setpDTOID(id);
        AddressDTO perAddress = FACADE.addAddress(aDTO.getStreet(), aDTO.getAdditionalInfo(), aDTO.getpDTOID());
        return GSON.toJson(perAddress);
    }
    
    
    @PUT
    @Path("/edit/{value}")
    @Produces({MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_JSON}) 
    public Response editAddress(@PathParam("value") int value, String addressDTO) throws AddressNotFoundException{
        AddressDTO aDTO = GSON.fromJson(addressDTO, AddressDTO.class);
        aDTO.setpDTOID((value));
        AddressDTO responseDTO = FACADE.editAddress(aDTO);
        return Response.ok(responseDTO).build();
    }
    
    
 
 
}
