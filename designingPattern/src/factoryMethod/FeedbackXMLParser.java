package factoryMethod;

public class FeedbackXMLParser implements XMLParser{

	@Override
	public String parse() {
		System.out.println("Feedback XML Parsing..");
		return "Feedback XML Parser";
	}

}
