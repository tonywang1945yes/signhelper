package backend.converter.application;

import backend.entity.application.Activity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityListSerializer extends JsonSerializer<List<Activity>> {

    @Override
    public void serialize(List<Activity> activities, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<String, Activity[]> res = new HashMap<>();
        res.put("activities",activities.stream().toArray(Activity[]::new));
        jsonGenerator.writeObject(res);

    }
}
