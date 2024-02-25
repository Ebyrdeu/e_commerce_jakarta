package dev.ebyrdeu.e_commerce_jakarta.utils;

import java.util.function.Consumer;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("You can not instantiate util class");
    }

    public static <T> void isNotNull(Consumer<T> consumer, T value) {
        if (value != null) consumer.accept(value);
    }
}
