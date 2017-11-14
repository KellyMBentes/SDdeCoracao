package src.lib;

public class IllegalArgumentIntException extends java.lang.Exception {
	private static final String EXC_MSG_NULL_PARAM_INT = "{:className}: O atributo {:attribute} não pode ser negativo";
	
	public IllegalArgumentIntException(String className, String attrName){
		super(EXC_MSG_NULL_PARAM_INT.replace("{:className}", className).replace("{:attribute}", attrName));
	}
}