# Introduction to Bean Validation

## Implementations

```xml
<!-- Jakarta Bean Validation API -->
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>2.0.2</version>
</dependency>

<!-- Hibernate Validator (reference implementation) -->
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.0.18.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator-annotation-processor</artifactId>
    <version>6.0.18.Final</version>
</dependency>

<!-- Apache BVal -->
<dependency>
    <groupId>org.apache.bval</groupId>
    <artifactId>bval-jsr</artifactId>
    <version>2.0.3</version>
</dependency>
```

## General usage

- Built-in constraints in `javax.validation.constraints` package
- `javax.validation.Validator` interface
- `javax.validation.ValidatorFactory` and `javax.validation.Validation`
- `@NotNull(message = "must not be null to be valid")`
- ValidationMessages.properties
- Values substitution and *Expression Language* support
- `javax.validation.groups.Default`

```java
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();
```

## Custom constraints

- Constraint composition
- `@ReportAsSingleViolation`
- `javax.validation.ConstraintValidator`
- `javax.validation.ConstraintValidatorContext`

## Spring integration

- `org.springframework.validation.annotation.Validated`
