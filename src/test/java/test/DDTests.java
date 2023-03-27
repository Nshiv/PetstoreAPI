package test;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.User;
import utilities.DataProviders;

import java.sql.SQLOutput;


public class DDTests {

    @Test(priority = 0, dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userID,String username, String fname, String lname, String useremail, String pwd, String ph)
    {
        System.out.println("Test printing for github");
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);
       // userPayload.setUserStatus(1);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 1,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username)
    {
      Response response = UserEndPoints.deleteUser(username) ;
      Assert.assertEquals(response.getStatusCode(),200);
    }
}
