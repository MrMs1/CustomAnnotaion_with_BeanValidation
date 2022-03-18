package customannotation;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class AnnotationDemoTest {

	@Test
	@DisplayName("dateが2022/01/01 11:11:11のとき、バリエーション結果のメッセージはnull")
	void dateが存在する日付のときバリエーション結果のメッセージはnull() {
		// 準備
		AnnotationDemo target = new AnnotationDemo("2022/01/01 11:11:11");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// 実行
		Set<ConstraintViolation<AnnotationDemo>> constraintViolations = validator.validate(target);
		// 検証
		assertEquals(0, constraintViolations.size());
	}

	@Test
	@DisplayName("dateがnullのとき、バリエーション結果のメッセージはnull")
	void dateがnullのときバリエーション結果のメッセージはnull() {
		// 準備
		AnnotationDemo target = new AnnotationDemo(null);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// 実行
		Set<ConstraintViolation<AnnotationDemo>> constraintViolations = validator.validate(target);
		// 検証
		assertEquals(0, constraintViolations.size());
	}

	@Test
	@DisplayName("dateが2022/01/01のとき、バリエーション結果のメッセージは「存在する日付を指定してください。」である")
	void dateが年月日のみのときバリエーション結果のメッセージが存在する() {
		// 準備
		AnnotationDemo target = new AnnotationDemo("2022/01/01");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// 実行
		Set<ConstraintViolation<AnnotationDemo>> constraintViolations = validator.validate(target);
		// 検証
		assertEquals(1, constraintViolations.size());
		assertEquals("yyyy/MM/dd HH:mm:ss形式で設定してください。", constraintViolations.iterator().next().getMessage());
	}

	@Test
	@DisplayName("dateが2022/02/31 11:11:11のとき、バリエーション結果のメッセージは「存在する日付を指定してください。」である")
	void dateに存在しない秒が含まれているときバリエーション結果のメッセージが存在する() {
		// 準備
		AnnotationDemo target = new AnnotationDemo("2022/02/31 11:11:11");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// 実行
		Set<ConstraintViolation<AnnotationDemo>> constraintViolations = validator.validate(target);
		// 検証
		assertEquals(1, constraintViolations.size());
		assertEquals("存在する日付を指定してください。", constraintViolations.iterator().next().getMessage());
	}

}
