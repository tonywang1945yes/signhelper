package backend.config;

import backend.converter.CollectionTypeJsonSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.ArrayList;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {
//    @Bean
//    public ObjectMapper jsonObjectMapper() {
//        ArrayList<Module> modules = new ArrayList<>();
//
//        //CollectionType Serialization
//        SimpleModule collectionTypeSerializerModule = new SimpleModule();
//        collectionTypeSerializerModule.setSerializers(new CollectionTypeJsonSerializer());
//        modules.add(collectionTypeSerializerModule);
//
//        return Jackson2ObjectMapperBuilder.json()
//                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
//                .modules(modules)
//                .build();
//    }

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
        jackson2ObjectMapperBuilder.timeZone(TimeZone.getDefault());
//        jackson2ObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        jackson2ObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return jackson2ObjectMapperBuilder;
    }
}
