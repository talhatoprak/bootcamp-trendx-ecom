package com.trendx.ecomm.subscriptonservice.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PriceChangeModelDeserializer  extends StdDeserializer<PriceChangeModel> {

    protected PriceChangeModelDeserializer(Class<?> vc) {
        super(vc);
    }

    protected PriceChangeModelDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected PriceChangeModelDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public PriceChangeModel deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
     return null;
    }
}
