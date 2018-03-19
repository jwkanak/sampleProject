package roster.bean;

import java.io.Serializable;
import java.util.List;

/**
 * BaseBean
 * @author Kanak
 *
 */
public class BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String statusMessage;
	private List<ErrorBean> errors;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<ErrorBean> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorBean> errors) {
		this.errors = errors;
	}

}
