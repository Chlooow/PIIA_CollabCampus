package Models;

import java.io.*;

public class FileModel {
    private File openedFile;

    public void openFile(File file) {
        openedFile = file;
    }

    public String readFileContent() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(openedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public void saveFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(openedFile))) {
            writer.write(content);
        }
    }
}
