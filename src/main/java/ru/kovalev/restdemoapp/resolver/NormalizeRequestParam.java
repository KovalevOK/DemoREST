package ru.kovalev.restdemoapp.resolver;


import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface NormalizeRequestParam {
}
