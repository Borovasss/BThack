package com.ferra13671.bthack;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HudInfo {
   boolean enabled() default false;

   float y() default 10.0F;

   float x() default 10.0F;

   float height() default 0.0F;

   String name();

   float width() default 0.0F;
}
