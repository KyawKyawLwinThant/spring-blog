package demo.example.blogspring.service;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;



@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordNotTheSameValidator.class)
public @interface PasswordNotSame {

  String message() default "{com.mycompany.first.cup.PasswordNotSame}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
