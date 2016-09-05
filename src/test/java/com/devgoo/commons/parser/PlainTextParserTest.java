package com.devgoo.commons.parser;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by madimetja on 2016/09/02.
 */
public class PlainTextParserTest {

	private ClassLoader classLoader;

	private Parser parser;

	private final String PATH_TO_TEXT_FILE = "files/text/textFile.txt";
	private final String PATH_TO_EMPTY_TEXT_FILE = "files/text/plainTextFileOne.txt";
	private final String PATH_TO_SIMPLE_TEXT_FILE = "files/text/plainTextFileThree";
	private final String PATH_TO_COMPLEX_TEXT_FILE = "files/text/plainTextFileTwo.txt";

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
		parser = new Parser();
	}

	/**
	 * Ensure that a plain text file on the file system can successfully be parsed,
	 * and that the parsed content is accurate.
	 */
	@Test
	public void testParseTextFile() throws UnknownFileFormatException, IOException, InvalidFileFormatException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_TEXT_FILE).getPath(), FileFormats.TXT);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "This is a random TeXt FilE WitH RanDoM Data.\n" +
			"It should be parsed well if the logic to parse\n" +
			"plain text files works properly....");
	}

	/**
	 *
	 * @throws IOException
	 * @throws UnknownFileFormatException
	 */
	@Test
	public void testThatEmptyPlainTextFileIsParsedCorrectly() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_EMPTY_TEXT_FILE).getPath());
		assertNotNull(testFile);
		assertEquals(testFile.getContentAsString(), "");
	}

	/**
	 *
	 * @throws IOException
	 * @throws UnknownFileFormatException
	 */
	@Test
	public void testThatSimplePlainTextFileIsParsedCorrectly() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_SIMPLE_TEXT_FILE).getPath());
		assertNotNull(testFile);
		assertEquals(testFile.getContentAsString(), "Hello, My name is World.");
	}

	/**
	 *
	 * @throws IOException
	 * @throws UnknownFileFormatException
	 */
	@Test
	public void testThatComplexPlainTextFileIsParsedCorrectly() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_COMPLEX_TEXT_FILE).getPath());
		assertNotNull(testFile);
		assertEquals(testFile.getContentAsString(), "Lorem ipsum dolor sit amet, diam adversarium vel ea, velit virtute ut vis, porro aeque sed eu. Sed et consul quidam lucilius, option contentiones eam ad. Ex porro commune vel, solet populo aliquip cu cum. No eum habeo oporteat adipiscing. Fabulas concludaturque eu ius, vis ex illum prompta nostrud. An duo modo animal.\n" +
			"\n" +
			"Pro vidisse percipit deserunt et, te quod sale assueverit vis, qui ut fugit moderatius. Eu nobis utinam minimum qui, at eum inermis constituto. Dictas regione necessitatibus eu cum. In minim dicit adolescens vim, ferri mutat nam te, sit postea copiosae at. Pro ne hinc populo definitionem, at justo dicta consequuntur sit, delenit laoreet reprimique est ea. Eum at laoreet eligendi dissentiet, ex tale aeterno petentium sit, ei vis vidit persecuti conceptam.\n" +
			"\n" +
			"Te noster adipisci ius, usu id adhuc iuvaret definitiones, ius elit natum imperdiet in. Et ius nemore deserunt lobortis, sit et corpora assentior, ei hinc percipit ius. Has modo volumus ut, liber aeterno voluptatum mel no. Unum concludaturque pro eu, in cibo aliquam posidonium nec. Ne vim vero integre scaevola.\n" +
			"\n" +
			"Habeo dicant meliore ad usu. Ad vim decore intellegam, no erant eirmod necessitatibus nam. Per posse tantas mentitum eu. Sint consequat ei vix. Nam eripuit saperet ea, sea ei omnium reprimique.\n" +
			"\n" +
			"Mel fabellas molestiae in. Per in eros tota postulant. No his modus lucilius. Ea vel elit indoctum corrumpit. Aperiam vivendo quo ex, oporteat inimicus pertinacia quo cu. Et melius legimus pertinax duo, at offendit expetendis per, ius solum iriure propriae te.");
	}
}
