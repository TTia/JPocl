package type;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FunctionTypeDec {
	private HashMap<String,Type> parameters = new LinkedHashMap<>();

	public boolean addParameter(String pname, Type ptype){
		return parameters.put(pname, ptype) == null;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("(");
		String[] keySet = parameters.keySet().toArray(new String[0]);
		int i = 0;
		for (; i < keySet.length-1; i++) {
			res.append(keySet[i]+" ");
			res.append(parameters.get(keySet[i]).toString());
			res.append(", ");
		}
		res.append(keySet[i]+" ");
		res.append(parameters.get(keySet[i]).toString());
		res.append(" )");
		return res.toString();
	}
	
	public HashMap<String, Type> getParameters() {
		return parameters;
	}
}
