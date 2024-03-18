package io.hexa24.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * LocalDataAdapter를 구현하여 Gson에서 사용할 수 있게 추가 <br>
 * (시간 없이 날짜만 표현해 주는 java.time.LocalDate의 경우, Gson에서 기본적으로 지원하지 않음.) <br>
 * 단. Gson 객체를 생성 할 때, registerTypeAdapter(LocalDate.class, new LocalDateAdapter())를 추가 필요.
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString());
    }
}