package demo.example.blogspring.service;

import demo.example.blogspring.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordNotTheSameValidator implements ConstraintValidator<PasswordNotSame,User> {

  @Override
  public void initialize(PasswordNotSame constraintAnnotation) {

  }

  @Override
  public boolean isValid(User user, ConstraintValidatorContext context) {
    return user.getPassword().equalsIgnoreCase(user.getConfirmPassword());
  }
}
