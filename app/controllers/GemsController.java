package controllers;

import java.io.PrintWriter;

import models.Gems;
import models.GemsList;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GemsController extends Controller {
	// More code will be added here

	@BodyParser.Of(BodyParser.Json.class)
	public static Result storeGem() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.err.println("POST Data");
			JsonNode json = request().body().asJson();
			System.err.println("json payload: " + json);
			Gems newGem = mapper.readValue(json.toString(), Gems.class);
			GemsList theList = GemsList.getInstance();
			newGem = theList.addGem(newGem);
			
			 JsonNode result = Json.toJson(newGem);
			 PrintWriter writer = new PrintWriter("TryGiovanni.json");
			System.out.println("Create: " + newGem);
			writer.write(result.toString());
			return created(result);
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest("Missing information");
		}

	}

	public static Result getGem(Long id) {
		// DEBUG
		System.err.println("GET on id: " + id);

		ObjectNode result = Json.newObject();
		GemsList theList = GemsList.getInstance();
		Gems gem = theList.getGemById(id);
		if (gem == null) {
			return notFound(); // 404
		} else {
			result.put("Gems", Json.toJson(gem));
			return ok(result);

		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateGem(Long id) {
		ObjectMapper mapper = new ObjectMapper();
		try {

			JsonNode json = request().body().asJson();
			Gems updGem = mapper.readValue(json.toString(), Gems.class);
			GemsList theList = GemsList.getInstance();
			updGem = theList.updateGem(updGem);
			if (updGem == null) {
				return notFound(); // 404
			} else {
				ObjectNode result = Json.newObject();
				System.out.println("update: " + updGem);
				result.put("Gems", Json.toJson(updGem));
				return ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest("Missing information");
		}
	}

	public static Result deleteGem(Long id) {
		GemsList theList = GemsList.getInstance();
		boolean erased = theList.deleteGem(id);
		System.out.println("delete:" + erased);
		if (erased) {
			// This is code 204 - OK with no content to return
			return noContent();
		} else {
			return notFound("Person not found");
		}

	}

	public static Result getAllGems() {

		GemsList theList = GemsList.getInstance();
		Gems[] C = theList.getAllGems();
		if (C == null) {
			System.out.println("Not Found");
			return notFound(); // 404
		} else {
			JsonNode result = Json.toJson(C);
			System.out.println("Got all contacts.");
			return ok(result);
		}
	}
}