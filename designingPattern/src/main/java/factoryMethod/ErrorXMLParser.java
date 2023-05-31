package factoryMethod;

public class ErrorXMLParser implements XMLParser{

	@Override
	public String parse() {
		System.out.println("Error XML Parsing...");
		return "Error XML parser";
	}

}
