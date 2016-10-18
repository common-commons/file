# Common-Commons File Library

File is a Java library with a PhatFile wrapper providing capability to write, parse and validate files in the following formats:

* JSON (org.json.JSONObject and com.fasterxml.jackson.databind.JsonNode supported)
* XML (org.w3c.dom.Document supported)
* Free Format Text Fies
* CSV file (comming soon)

## Usage Examples

File Validations

```java
try{
	PhatFile phatFileObject = new PhatFile("SomeFile");
	Validators validators = new Validators();
	
	//Get the content of the file as a string
	String fileContent = phatFileObject.getContentAsString();
	
	//Get the file format of the file parsed.
	FileFormats fileFormat =  validators.determineFileType(phatJsonFile);
	
	//Validate if the content within a file is valid for a specific file type
	boolean isValidJsonContent = validators.validate(fileContent, FileFormats.JSON);
	boolean isValidXmlContent = validators.validate(fileContent, FileFormats.XML);
	boolean isValidCsvContent = validators.validate(fileContent, FileFormats.CSV);
	
} catch (IOException e) {
	//Handle IOException
} catch (ParserConfigurationException e) {
	//Handle ParserConfigurationException
} catch (SAXException e) {
	//Handle ParserConfigurationException
}
```

Manipulating JSON content with com.fasterxml.jackson.databind.JsonNode

```java
try{
	PhatFile phatJsonFile = new PhatFile("path/to/some/json/file.json");
	
	JsonNode jsonNode = phatJsonFile.getAsJsonNode();
	
	//...
	//Manipulate the JsonNode as you wish
	//...
	
} catch (IOException e) {
	//Handle IOException
} catch (ParserConfigurationException e) {
	//Handle ParserConfigurationException
}
```

Manipulating JSON content with org.json.JSONObject

```java
try{
	PhatFile phatJsonFile = new PhatFile("JsonFile.json");
	
	JSONObject jsonObject = phatJsonFile.getAsJsonObject();
	
	//...
	//Manipulate the JSONObject as you wish
	//...
	
} catch (IOException e) {
	//Handle IOException
} catch (ParserConfigurationException e) {
	//Handle ParserConfigurationException
}
```

Manipulating XML content with org.w3c.dom.Document

```java
try{
	PhatFile phatJsonFile = new PhatFile("JsonFile.json");
	
	Document document = phatJsonFile.getAsDocument();
	
	//...
	//Manipulate the Document as you wish
	//...
	
} catch (IOException e) {
	//Handle IOException
} catch (ParserConfigurationException e) {
	//Handle ParserConfigurationException
} catch (SAXException e) {
	//Handle ParserConfigurationException
}
```

## Maven Repository

The library is available on the Central Maven Repository [here].

## Official Documentation

Please see the [javadocs] for info.

## Version
1.0.0

## Contributing

Thank you for considering contributing to this library! Please see [the contribution documentation] for guidance on contributing.

## Bugs and Issues

If you discover any bugs within the library or disscover any issues, please log an [issue] or send an email to either [Madi] or [Chris], and we will check it out.

License
----

Apache 2.0

[here]:https://common-commons.github.io/file/
[javadocs]:https://common-commons.github.io/file/
[the contribution documentation]:https://common-commons.github.io/contribution/
[issue]:https://github.com/common-commons/file/issues
[Madi]:mailto:mmjshika@gmail.com?Subject=Common-Commons-File
[Chris]:mailto:christopher.mipi@gmail.com?Subject=Common-Commons-File