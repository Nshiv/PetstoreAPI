package test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import payload.User;
import io.restassured.response.Response;


public class UserTests {

    Faker faker;
    User userPayload;

    public Logger logger;
    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setUsername(faker.name().username());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = (Logger) LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostUser()
    {
        logger.info("--- creating user-----");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
        logger.info("--- user created-----");
    }

    @Test(priority = 2)
    public void testGetUserbyName()
    {
       logger.info("----reading user info-----");
       Response response=UserEndPoints.readUser(this.userPayload.getUsername());
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 3)
    public void updateUserByName()
    {
        logger.info("----Updating user info-----");
        //updated data for user
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        //checking data after updation
        Response responseAfter = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 4)
    public void deleteUser()
    {
        Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
