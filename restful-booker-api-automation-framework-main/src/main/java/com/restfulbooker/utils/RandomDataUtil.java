package com.restfulbooker.utils;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomDataUtil {
   private static final Faker faker = new Faker();
   private static final String [] NEEDS = {"Breakfast", "Lunch", "Dinner", "Airport Pickup"};

   public static String getRandomFirstName(){return faker.name().firstName();}
   public static String getRandomLastName(){return  faker.name().lastName();}
   public static int getRandomPrice(){return faker.number().numberBetween(100,1000);}
   public static boolean getRandomDepositPaidStatus(){return faker.bool().bool();}

   public static String  getRandomCheckinDate(){
       return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
   }

    public static String getRandomCheckoutDate() {
        return LocalDate.now().plusDays(5)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getRandomAdditionalNeeds() {
        return NEEDS[new Random().nextInt(NEEDS.length)];
    }




}
