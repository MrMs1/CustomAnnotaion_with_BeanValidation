package customannotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class AnnotationDemoTest {

	private static final String DATE = "date";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	private String getValidateResultMessage(String fieldName, String value) {
		return validator
				.validateValue(AnnotationDemo.class, fieldName, value)
				.iterator()
				.next()
				.getMessage();
	}

	private boolean hasValidateResult(String fieldName, String value) {
		return validator
				.validateValue(AnnotationDemo.class, fieldName, value)
				.iterator()
				.hasNext();
	}

	@Nested
	class TimeStampアノテーション {

		@Nested
		class nullチェックは行わない {
			@Test
			@DisplayName("dateがnullのとき、バリエーション結果は存在しない")
			void dateがnullのときバリエーション結果は存在しない() {
				// 検証
				assertEquals(true, hasValidateResult(DATE, null));
			}
		}

		@Nested
		class 日付フォーマットチェックを行う {
			@Test
			@DisplayName("dateが2022/01/01のとき、バリエーション結果のメッセージは「存在する日付を指定してください。」である")
			void dateが年月日のみのときバリエーション結果のメッセージが存在する() {
				// 検証
				assertEquals("yyyy/MM/dd HH:mm:ss形式で設定してください。", getValidateResultMessage(DATE, "2022/01/01"));
			}
		}

		@Nested
		class 日付存在チェックを行う {
			@Test
			@DisplayName("dateが2022/01/01 11:11:11のとき、バリエーション結果は存在しない")
			void dateが存在する日付のときバリエーション結果は存在しない() {
				// 検証
				assertEquals(false, hasValidateResult(DATE, "2022/01/01 11:11:11"));
			}

			@Test
			@DisplayName("dateが2022/02/31 11:11:11のとき、バリエーション結果のメッセージは「存在する日付を指定してください。」である")
			void dateが存在しない日付のときバリエーション結果のメッセージが存在する() {
				// 検証
				assertEquals("存在する日付を指定してください。", getValidateResultMessage(DATE, "2022/02/31 11:11:11"));
			}
		}

	}
}
