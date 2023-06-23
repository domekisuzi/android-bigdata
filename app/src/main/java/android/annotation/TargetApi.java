package android.annotation;


import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Indicates that Lint should treat this type as targeting a given API level, no matter what the
 project target is. */
@Target({TYPE, METHOD, CONSTRUCTOR, FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface TargetApi {
    /**
     * This sets the target api level for the type..
     */
    int value();
}

