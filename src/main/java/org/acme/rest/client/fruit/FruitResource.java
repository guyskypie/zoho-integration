package org.acme.rest.client.fruit;


import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

@Path("/fruits")
public class FruitResource {

    @RestClient
    FruityViceService fruityViceService;

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit", "winter"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit", "summer"));
        fruits.add(new Fruit("Watermelon", "Tropical fruit", "summer"));
    }

    @GET
    public Set<Fruit> list() {
        return fruits;
    }


    @GET
    @Path("season")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FruitDTO> fruits(@QueryParam("season") String season) {
        if (season != null) {
            FruityVice apple = fruityViceService.getFruitByName("Apple");
            FruitDTO appleDto = FruitDTO.of(fruits.iterator().next(), fruityViceService.getFruitByName("apple"));
            List<FruitDTO> fruitsWithAddedInfo =  findBySeason(season).stream()
                    .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name)))
                    .collect(Collectors.toList());
            return fruitsWithAddedInfo;
        }
 
        return fruits.stream()
                .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name)))
                .collect(Collectors.toList());
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }


    private List<Fruit> findBySeason(String season){
        List<Fruit> seasonalFruits = fruits.stream().
                filter(f -> f.season.equals(season)).
                collect(Collectors.toList());
        return seasonalFruits;
    }
}
