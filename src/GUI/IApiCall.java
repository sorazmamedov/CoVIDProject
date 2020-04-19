package GUI;

import org.json.JSONObject;

public interface IApiCall {

    JSONObject get(String api);
    void post();
}
