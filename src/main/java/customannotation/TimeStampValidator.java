package customannotation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeStampValidator implements ConstraintValidator<TimeStamp, String> {

	private String formatUnMatchMessage;
	private String inCorrectDateMessage;

	@Override
	public void initialize(TimeStamp timeStamp) {
		this.formatUnMatchMessage = timeStamp.formatUnMatchMessage();
		this.inCorrectDateMessage = timeStamp.inCorrectDateMessage();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// nullの場合はチェックしない
		if (value == null) {
			return true;
		}

		// 日付フォーマットチェック
		if (value.matches(
				"^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])\\s([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$") == false) {
			changeErrorMessage(context, formatUnMatchMessage);
			return false;
		}

		// 日付存在チェック
		try {
			LocalDate.parse(value,
					DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT));
		} catch (DateTimeParseException e) {
			changeErrorMessage(context, inCorrectDateMessage);
			return false;
		}
		return true;
	}

	// エラーメッセージの変更を行う
	void changeErrorMessage(ConstraintValidatorContext context, String message) {
		context.disableDefaultConstraintViolation(); // デフォルトのConstraintViolationを無効にする。
		context.buildConstraintViolationWithTemplate(message) // テンプレートにより設定したいエラーメッセージでConstraintViolationを作成。
			.addConstraintViolation();
	}

}
