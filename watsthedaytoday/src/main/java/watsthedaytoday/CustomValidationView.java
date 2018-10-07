package watsthedaytoday;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CustomValidationView {
     
    private String text;
 
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    } 
}
