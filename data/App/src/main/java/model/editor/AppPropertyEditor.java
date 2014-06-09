package model.editor;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import model.App;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class AppPropertyEditor extends PropertyEditorSupport {

    public AppPropertyEditor() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(objectMapper.readValue(text, App.class));
        } catch (IOException ex) {
            throw new IllegalArgumentException(String.valueOf(ex));
        }
    }

    @Override
    public String getAsText() {
        try {
            return objectMapper.writeValueAsString(getValue());
        } catch (IOException ex) {
            return null;
        }
    }

    private final ObjectMapper objectMapper;
}
