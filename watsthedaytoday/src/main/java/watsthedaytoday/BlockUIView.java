package watsthedaytoday;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

	@ManagedBean
	public class BlockUIView {
	     
	    private String firstname;
	    private String lastname;
	    private List<String> cars;
	     
	    @PostConstruct
	    public void init() {
	        cars = new ArrayList<String>();
	        cars.add(new String("Y25YIH5"));
	        cars.add(new String("JHF261G"));
	    }
	 
	    public String getFirstname() {
	        return firstname;
	    }
	 
	    public void setFirstname(String firstname) {
	        this.firstname = firstname;
	    }
	 
	    public String getLastname() {
	        return lastname;
	    }
	 
	    public void setLastname(String lastname) {
	        this.lastname = lastname;
	    }
	     
	    public void save() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You've registered"));
	    }
	 
	    public List<String> getCars() {
	        return cars;
	    }
	 
	    public void setCars(List<String> cars) {
	        this.cars = cars;
	    }
}