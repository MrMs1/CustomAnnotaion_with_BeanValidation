package customannotation;

public class AnnotationDemo {

	@TimeStamp
	private String date;

	public AnnotationDemo(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}
}
