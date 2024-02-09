package sqa.petstore.constants;

import com.google.gson.Gson;
import net.serenitybdd.screenplay.Actor;
import org.json.JSONObject;
import sqa.petstore.models.pet.PostPetModel;
import sqa.petstore.models.user.PostUserModel;
import sqa.petstore.questions.pet.BuildPet;
import sqa.petstore.questions.user.BuildPutBodyAndGetResponseUser;
import sqa.petstore.utils.ExcelReader;
import sqa.petstore.utils.JsonReader;

import java.util.Map;

public class Constants {

    public static String getExpectedJsonResponseUser(String key, Integer index) {
        String expectedJson = JsonReader.readJson("src/test/resources/dataTest/user/GetResponse.json");
        JSONObject expectedJsonObject = new JSONObject(expectedJson);
        return String.valueOf(expectedJsonObject.getJSONArray(key).getJSONObject(index));
    }

    public static String infoResponse(Actor actor, Integer index, String page){
        Gson gson = new Gson();
        Map<String, String> excelData;
        if (page.equals("Sheet1")) {
            excelData = constanstExcelPet(index, page);
            PostPetModel petInfoResponse = actor.asksFor(BuildPet.was(excelData.get("id"), excelData.get("nameCategory"), excelData.get("namePet")));
            return gson.toJson(petInfoResponse);
        } else if (page.equals("Users") || page.equals("UsersPut")) {
            excelData = constanstExcelUser(index, page);
            PostUserModel userInfoResponse = actor.asksFor(BuildPutBodyAndGetResponseUser.was(excelData.get("id"), excelData.get("username"), excelData.get("firstName"), excelData.get("lastName"), excelData.get("email"), excelData.get("password"), "+57 " + excelData.get("phone"), excelData.get("userStatus")));
            return gson.toJson(userInfoResponse);
        }

        else {
            return "No se ha especificado una página válida";
        }
    }

    public static Map<String, String> constanstExcelUser(Integer index, String page) {

        return ExcelReader.parseXlsx("src/test/resources/dataTest/user/excelDataUser.xlsx", index, page);
    }

    public static Map<String, String> constanstExcelPet(Integer index, String page) {

        return ExcelReader.parseXlsx("src/test/resources/dataTest/pet/excelDataPet.xlsx", index, page);
    }

}
