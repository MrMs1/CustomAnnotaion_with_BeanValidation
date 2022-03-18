package customannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeStampValidator.class)
@Documented
@Repeatable(TimeStamp.List.class)
public @interface TimeStamp {

	String message() default "デフォルトメッセージ";

	String formatUnMatchMessage() default "yyyy/MM/dd HH:mm:ss形式で設定してください。";

	String inCorrectDateMessage() default "存在する日付を指定してください。";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.FIELD, ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		TimeStamp[] value();
	}
}
