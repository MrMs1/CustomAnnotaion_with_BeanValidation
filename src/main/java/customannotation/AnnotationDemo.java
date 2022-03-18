package customannotation;

public class AnnotationDemo {

	@TimeStamp(formatUnMatchMessage = "フォーマットチェックカスタムメッセージ", inCorrectDateMessage = "存在チェックカスタムメッセージ")
	private String date;

	public AnnotationDemo(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}
}
