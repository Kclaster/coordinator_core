package com.coordinator.core.general.main.annotations;

import org.immutables.value.Value;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static org.immutables.value.Value.Style.ImplementationVisibility.PUBLIC;

@Target(value = {TYPE})
@Retention(CLASS)
@Value.Style(
        get = { "is*", "get*"},
        init = "with*",
        optionalAcceptNullable = true,
        jdkOnly = true,
        visibility = PUBLIC,
        stagedBuilder = true
)
public @interface BuilderStyle {
}
