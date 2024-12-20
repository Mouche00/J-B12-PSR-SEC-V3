//package org.jb10pigeonskyracesecurity.utils.annotations;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Target({ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = RefExistsValidator.class)
//public @interface RefExists {
//    String message() default "Referenced document does not exist";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//    String collection();
//    String field() default "id";
//}