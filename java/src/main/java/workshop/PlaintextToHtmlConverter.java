package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlaintextToHtmlConverter {

    public String toHtml() throws Exception {
        String text = read();
        String htmlLines = basicHtmlEncode(text);
        return htmlLines;
    }

    protected String read() throws IOException {
        Path filePath = Paths.get("sample.txt");
        byte[] fileByteArray = Files.readAllBytes(filePath);
        return new String(fileByteArray);
    }

    private String basicHtmlEncode(String source) {

        int i = 0;
        List<String> result = new ArrayList<>();
        List<String> convertedLine = new ArrayList<>();
        for (char characterToConvert : source.toCharArray()) {
            switch (characterToConvert) {
                case '<':
                    convertedLine.add("&lt;");
                    break;
                case '>':
                    convertedLine.add("&gt;");
                    break;
                case '&':
                    convertedLine.add("&amp;");
                    break;
                case '\n':
                    addANewLine(result,convertedLine);
                    convertedLine = new ArrayList<>();
                    break;
                default:
                    pushACharacterToTheOutput(convertedLine,String.valueOf(characterToConvert));
            }
        }
        addANewLine(result,convertedLine);
        String finalResult = String.join("<br />", result);
        return finalResult;
    }


    //stringfy convertedLine array and push into result
    //reset convertedLine
    private void addANewLine(List<String> result,List<String> convertedLine) {
        String line = String.join("", convertedLine);
        result.add(line);
    }

    private void pushACharacterToTheOutput(List<String> convertedLine,String characterToConvert) {
        convertedLine.add(characterToConvert);
    }
}
