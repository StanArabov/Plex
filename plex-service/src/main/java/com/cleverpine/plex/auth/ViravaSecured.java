package com.cleverpine.plex.auth;

import com.cleverpine.plex.auth.roles.Resources;
import com.cleverpine.viravaspringhelper.dto.ScopeType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViravaSecured {
    Resources resource();

    ScopeType[] scope();

    String resourceIdParamName() default "";

    boolean requireAllResourceIds() default false;
}
