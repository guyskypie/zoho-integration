package org.acme.rest.client.fruit;

import java.math.BigDecimal;

public class FruityVice {

    private String name;

    private Nutritions nutritions;

    public FruityVice(){}

    FruityVice(String name, Nutritions nutritions) {
        this.name = name;
        this.nutritions = nutritions;
    }



   /* @JsonbCreator
    public static FruityVice of(String name, Nutritions nutritions) {
        return new FruityVice(name, nutritions);
    }*/


    public String getName() {
        return name;
    }

    public Nutritions getNutritions() {
        return nutritions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNutritions(Nutritions nutritions) {
        this.nutritions = nutritions;
    }

    public static class Nutritions {

        private BigDecimal carbohydrates;

        private BigDecimal calories;

        public Nutritions(){}

        Nutritions(BigDecimal carbohydrates, BigDecimal calories) {
            this.carbohydrates = carbohydrates;
            this.calories = calories;
        }

      /*  @JsonbCreator
        public static Nutritions of(BigDecimal carbohydrates, BigDecimal calories) {
            return new Nutritions(carbohydrates, calories);
        }*/

        public BigDecimal getCarbohydrates() {
            return carbohydrates;
        }

        public BigDecimal getCalories() {
            return calories;
        }

        public void setCarbohydrates(BigDecimal carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        public void setCalories(BigDecimal calories) {
            this.calories = calories;
        }
    }

}


