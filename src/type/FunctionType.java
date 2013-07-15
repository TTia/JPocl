package type;
import java.util.Collection;

public class FunctionType implements Type {
	private Type returnType;
	private String id;
	private FunctionTypeDec dec;

	public FunctionType(String id, Type returnType, FunctionTypeDec dec) {
		this.id = id;
		this.returnType = returnType;
		this.dec = dec;
	}

	public Collection<Type> getParameters(){
		return dec.getParameters().values();
	}
	
	public Type getReturnType() {
		return returnType;
	}

	@Override
	public boolean equalsTo(Type t) {
		return this.returnType.equals(t);
	}

	@Override
	public String toString() {
		return id + "(" + dec.toString() + ")";
	}

	@Override
	public String getLoader() {
		throw new Error("Debug: Can't load a function)");
	}

	@Override
	public String getStorer() {
		throw new Error("Debug: Can't store a value in a function)");
	}

	public String getParametersSignature(String packageDst) {
		String parametersSignature = "";
		for(Type type :getParameters()){
			parametersSignature += type.getObjectType(packageDst);
		}
		return parametersSignature;
	}

	@Override
	public String getObjectType(String packageDst) {
		return getReturnType().getObjectType(packageDst);
	}

	@Override
	public String getId() {
		return id;
	}

}
