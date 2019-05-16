package com.rafa.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE }) // Diz onde a annotacao poderá ser utilizada
@Retention(RetentionPolicy.RUNTIME) // validacao em tempo de execução
@Constraint(validatedBy = {}) // validação através de uma classe //ainda será feito - linha sem função por enquanto
@Pattern(regexp="([a-zA-Z]{2}\\d{4})?") // padrão
public @interface SKU
{
	@OverridesAttribute(constraint = Pattern.class, name="message") // sobrescrevendo o atributo de mensagem do bean validation
	String message() default "SKU deve seguir o padrão XX9999";
	
	Class<?>[] groups() default {}; // precisa sobrescrever - usado pra agrupar validacoes por grupo - ainda nao esta sendo implementado
	Class<? extends Payload>[] payload() default {}; //precisa sobrescrever - serve pra definir niveis de erro - naop é mto usado e nao sera implementado
}
