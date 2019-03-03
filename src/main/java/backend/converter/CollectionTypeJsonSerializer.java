package backend.converter;


import backend.converter.application.ActivityListSerializer;
import backend.converter.application.FamilyParticularListSerializer;
import backend.entity.application.Activity;
import backend.entity.application.FamilyParticularItem;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

//custom serializer for CollectionType
public class CollectionTypeJsonSerializer extends SimpleSerializers {

    @Override
    public JsonSerializer<?> findCollectionSerializer(SerializationConfig config,
                                                      CollectionType type,
                                                      BeanDescription beanDesc,
                                                      TypeSerializer elementTypeSerializer,
                                                      JsonSerializer<Object> elementValueSerializer) {
        //if the collection is of type Activity of FamilyParticularItem, then use custom collection serializer
        if (isKnownClassListType(type, Activity.class))
            return new ActivityListSerializer();
        else if (isKnownClassListType(type, FamilyParticularItem.class))
            return new FamilyParticularListSerializer();

        return findSerializer(config, type, beanDesc);
    }


    private boolean isKnownClassListType(CollectionType type, Class target) {
        CollectionType languageStringArrayListType = TypeFactory.defaultInstance()
                .constructCollectionType(ArrayList.class, target);

        CollectionType languageStringListType = TypeFactory.defaultInstance()
                .constructCollectionType(List.class, target);

        return (type.equals(languageStringListType) || type.equals(languageStringArrayListType));
    }
}