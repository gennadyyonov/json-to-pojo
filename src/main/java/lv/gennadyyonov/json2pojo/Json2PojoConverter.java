package lv.gennadyyonov.json2pojo;

import com.sun.codemodel.JCodeModel;
import lombok.SneakyThrows;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.net.URL;

public class Json2PojoConverter {

    private final URL schemaUrl;
    private final String className;
    private final String packageName;
    private final File outputDir;

    @SneakyThrows
    public Json2PojoConverter(File inputFile, String className, String packageName, File outputDir) {
        this.schemaUrl = inputFile.toURI().toURL();
        this.className = className;
        this.packageName = packageName;
        this.outputDir = outputDir;
    }

    @SneakyThrows
    public void convert() {
        JCodeModel codeModel = new JCodeModel();
        GenerationConfig config = new JsonGenerationConfig();
        SchemaMapper mapper = new SchemaMapper(
                new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()),
                new SchemaGenerator()
        );
        mapper.generate(codeModel, className, packageName, schemaUrl);
        codeModel.build(outputDir);
    }
}
