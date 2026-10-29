package com.ferra13671.bthack;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleInfo {
   String category();

   String name();

   boolean enabled() default false;

   boolean quitKeybindToggling() default false;
}
