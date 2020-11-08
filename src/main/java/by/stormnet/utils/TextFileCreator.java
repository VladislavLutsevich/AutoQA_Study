package by.stormnet.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileCreator {

    public File textFileCreate() throws IOException {
        File file = new File("file_" + CharsGenerator.generateRandomCharsForName() + ".txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(CharsGenerator.generateRandomCharsForContent());
        fileWriter.close();
        return file;
    }
}
