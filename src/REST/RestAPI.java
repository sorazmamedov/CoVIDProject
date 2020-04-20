package REST;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestAPI implements IApiCall{
    private static RestAPI restAPI = null;
    private URI uri;

    private RestAPI() { }

    public static RestAPI getInstance() {
        if (restAPI == null) {
            restAPI = new RestAPI();
        }
        return restAPI;
    }

    @Override
    public JSONObject get(String api) {
        uri = URI.create(api);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(JSONObject::new)
                .join();
    }

    @Override
    public void post() {

    }
}
