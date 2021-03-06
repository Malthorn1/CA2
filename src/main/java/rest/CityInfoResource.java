package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityInfoDTO;
import dto.PersonDTO;
import exceptions.CityInfoNotFoundException;
import facades.CityInfoFacade;
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
@Path("cityinfo")
public class CityInfoResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca2",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final CityInfoFacade FACADE =  CityInfoFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCityInfoCount() {
        long count = FACADE.getCityInfoCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    
    @GET
    @Path("/zipcode/{zipcode}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getPersonByZipcode(@PathParam("zipcode") int zipcode) throws CityInfoNotFoundException {
        return GSON.toJson(FACADE.getPersonsByCityInfo(zipcode)); 
    }
    
    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_JSON}) 
    public String addCityInfo(String cityInfo){
        CityInfoDTO cDTO = GSON.fromJson(cityInfo, CityInfoDTO.class);
        System.out.println(cDTO.getCity() + "City her");
        CityInfoDTO cityDTO = FACADE.AddCityInfo(cDTO.getZipCode(), cDTO.getCity(), cDTO.getcDTOID());
        return GSON.toJson(cityDTO);
        
    }
    
    @POST
    @Path("add/{id}")
    @Produces({MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_JSON}) 
    public String addCityInfoWithId(@PathParam("id") int id,String cityInfo){
        CityInfoDTO cDTO = GSON.fromJson(cityInfo, CityInfoDTO.class);
        cDTO.setcDTOID(id);
        CityInfoDTO cityDTO = FACADE.AddCityInfo(cDTO.getZipCode(), cDTO.getCity(), cDTO.getcDTOID());
        return GSON.toJson(cityDTO);
    }
    
    
    @PUT
    @Path("/edit/{value}")
    @Produces({MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_JSON}) 
    public Response editCityInfo(@PathParam("value") int value, String cityInfoDTO) throws CityInfoNotFoundException{
        CityInfoDTO cDTO = GSON.fromJson(cityInfoDTO, CityInfoDTO.class);
        
        CityInfoDTO responseDTO = FACADE.editCityInfo(cDTO, value);
        return Response.ok(responseDTO).build();
    }
 
 
}
