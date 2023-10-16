package ru.sartfoms.applgar.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Info implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;
	private Type type;
	private String style;

	public Info() {
		type = Type.WARNING;
	}

	public Info(Type type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if (message != null)
			this.message = message.trim();
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getStyle() {
		switch (type) {
		case PRIMARY:
			style = "alert-primary";
			break;
		case SECONDARY:
			style = "alert-secondary";
			break;
		case SUCCESS:
			style = "alert-success";
			break;
		case DANGER:
			style = "alert-danger";
			break;
		case WARNING:
			style = "alert-warning";
			break;
		case INFO:
			style = "alert-info";
			break;
		case LIGHT:
			style = "alert-light";
			break;
		case DARK:
			style = "alert-dark";
			break;

		default:
			style = "alert-warning";
			break;
		}

		return style;
	}

	public Icon getIcon() {
		return new Icon();
	}

	public enum Type {
		PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK
	}

	class Icon implements Serializable {
		private static final long serialVersionUID = 1L;
		private String path;
		private String style;

		public Icon() {
			switch (type) {
			case PRIMARY:
				path = infoFill;
				style = "text-primary";
				break;
			case SUCCESS:
				path = checkCircleFill;
				style = "text-success";
				break;
			case DANGER:
				path = exclamationTriangleFill;
				style = "text-danger";
				break;
			case WARNING:
				path = exclamationTriangleFill;
				style = "text-warning";
				break;
			case INFO:
				path = infoFill;
				style = "text-info";
				break;

			default:
				break;
			}
		}

		public String getPath() {
			return path;
		}

		public String getStyle() {
			return style;
		}

		protected String exclamationTriangleFill = "M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z";
		protected String infoFill = "M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z";
		protected String checkCircleFill = "M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z";
	}
}
