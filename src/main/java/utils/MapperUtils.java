package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;

public class MapperUtils {

    @SneakyThrows
    public static <T> T readFromFile(String path, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), tClass);
    }

}
