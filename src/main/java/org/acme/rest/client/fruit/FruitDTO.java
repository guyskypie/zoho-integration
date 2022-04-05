package org.acme.rest.client.fruit;

import java.math.BigDecimal;

public class FruitDTO {

    private String name;

    private String description;

    private String season;

    private BigDecimal carbohydrates;

    private BigDecimal calories;

    private FruitDTO(String name, String description, String season, BigDecimal carbohydrates, BigDecimal calories) {
        this.name = name;
        this.season = season;
        this.description = description;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
    }

    public static FruitDTO of(Fruit fruit, FruityVice fruityVice) {
        return new FruitDTO(
                fruit.name,
                fruit.description,
                fruit.season,
                fruityVice.getNutritions().getCarbohydrates(),
                fruityVice.getNutritions().getCalories());
    }

    public String getName() {
        return name;
    }

    public String getSeason() {
        return season;
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }

    public BigDecimal getCalories() {
        return calories;
    }

}


