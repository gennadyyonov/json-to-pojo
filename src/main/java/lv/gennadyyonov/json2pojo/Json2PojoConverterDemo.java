package lv.gennadyyonov.json2pojo;

import lombok.SneakyThrows;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class Json2PojoConverterDemo {

    private static final String PROPERTIES_FILE = "/demo.properties";

    public static void main(String[] args) {
        Properties properties = loadProperties();
        String inputFilename = properties.getProperty("inputFilename");
        String outputDirname = properties.getProperty("outputDirname");
        String className = properties.getProperty("className");
        String packageName = properties.getProperty("packageName");
        convertJson2Pojo(inputFilename, className, packageName, outputDirname);
    }

    @SneakyThrows
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream in = Json2PojoConverterDemo.class.getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(in);
            return properties;
        }
    }

    private static void convertJson2Pojo(String inputFilename, String className, String packageName, String outputDirname) {
        File inputFile = new File(inputFilename);
        File outputDir = new File(outputDirname);
        outputDir.mkdirs();
        Json2PojoConverter converter = new Json2PojoConverter(inputFile, className, packageName, outputDir);
        converter.convert();
    }
}
