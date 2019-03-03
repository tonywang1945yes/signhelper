package backend.converter.application;

import backend.entity.application.FamilyParticularItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyParticularListSerializer extends JsonSerializer<List<FamilyParticularItem>> {

    @Override
    public void serialize(List<FamilyParticularItem> familyParticularItems, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<String, FamilyParticularItem[]> res = new HashMap<>();
        res.put("activities", familyParticularItems.stream().toArray(FamilyParticularItem[]::new));
        jsonGenerator.writeObject(res);
    }
}
