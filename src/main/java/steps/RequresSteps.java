package steps;

import api.requresApi.RequresApi;
import models.requres.User;
import org.apache.http.HttpStatus;
import utils.MapperUtils;

public class RequresSteps {

    private static final RequresApi requresApi = new RequresApi();

    public User readFromFileUser(String filePath) {
        return MapperUtils.readFromFile(filePath, User.class);
    }

    public User createNewUser(User user) {
        return requresApi.postUser(user)
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(User.class);
    }
}
