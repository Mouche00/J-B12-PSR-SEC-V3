//package org.jb10pigeonskyracesecurity.utils.annotations;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.jb10pigeonskyracesecurity.utils.ReflectionUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//public class RefExistsValidator implements ConstraintValidator<RefExists, Object> {
//    private final MongoTemplate mongoTemplate;
//    private final ReflectionUtil reflectionUtil;
//
//    private String collection;
//    private String field;
//
//    @Autowired
//    public RefExistsValidator(MongoTemplate mongoTemplate, ReflectionUtil reflectionUtil) {
//        this.mongoTemplate = mongoTemplate;
//        this.reflectionUtil = reflectionUtil;
//    }
//
//    @Override
//    public void initialize(RefExists constraintAnnotation) {
//        this.collection = constraintAnnotation.collection();
//        this.field = constraintAnnotation.field();
//    }
//
//    @Override
//    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
//        System.out.println("test");
//        Object id = reflectionUtil.extractFieldByName(object, field);
//        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(id.toString())), collection);
//    }
//}
