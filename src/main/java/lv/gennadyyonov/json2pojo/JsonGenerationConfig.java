package lv.gennadyyonov.json2pojo;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.SourceType;

public class JsonGenerationConfig extends DefaultGenerationConfig {

    @Override
    public boolean isGenerateBuilders() {
        return true;
    }

    public SourceType getSourceType() {
        return SourceType.JSON;
    }
}
