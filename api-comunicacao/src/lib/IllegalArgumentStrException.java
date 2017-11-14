package lib;

public class IllegalArgumentStrException extends java.lang.Exception {
	private static final String EXC_MSG_NULL_PARAM_STR = "{:className}: O atributo {:attribute} não pode ser nulo ou vazio";
	
	public IllegalArgumentStrException(String className, String attrName){
		super(EXC_MSG_NULL_PARAM_STR.replace("{:className}", className).replace("{:attribute}", attrName));
	}
}