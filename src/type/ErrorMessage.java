package type;

public enum ErrorMessage{
	DUPLICATED_PARAMETER ("Duplicated parameter %s"),
	DUPLICATED_FIELD("Duplicated field %s"),
	VOID_EXPRESSION("Invalid void expression"),
	INCOMPATIBLE_TYPES("%s and %s are incompatible"),
	INVALID_RETURN("Invalid return type in function %s, %s and %s are incompatible"),
	FUNCTION_CALL("The function %s is not applicable for the arguments"),
	FUNCTION_REDECLARATION("The function %s is already declared"),
	STRUCT_REDECLARATION("The struct %s is already declared"),
	UNREACHBLE_CODE("Unreachable code"),
	RETURN_MISSING("The function %s must return a result of type %s"),
	TYPE_UNRESOLVED("%s cannot be resolved to a type"),
	FUNCTION_UNDEFINED("The function %s is undefined"),
	ID_FIELD_UNRESOLVED("%s cannot be resolved or is not a field");

	private String message;
	
	ErrorMessage(String message) {
		this.message = message;
	}
	
	public String buildMessage(int line, int column, Object... args){
		return String.format(message, args)+
				String.format(" @[%s,%s]", line, column);
	}
	
	public String buildMessage(Object... args){
		return String.format(message, args);
	}
	
	public String buildMessage(int line, int column){
		return String.format(message+" @[%s,%s]", line, column);
	}
}
