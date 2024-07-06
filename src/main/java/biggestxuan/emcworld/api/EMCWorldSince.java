package biggestxuan.emcworld.api;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(value = RUNTIME)
@EMCWorldSince("0.9.0")
public @interface EMCWorldSince {
    String value();
}
