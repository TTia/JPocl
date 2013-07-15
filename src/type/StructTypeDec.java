package type;



import java.util.Collection;
import java.util.LinkedHashMap;

public final class StructTypeDec {
	final private LinkedHashMap<String, Type> type = new LinkedHashMap<>();

	public boolean addField(String fname, Type ftype) {
		return type.put(fname, ftype) == null;
	}
	
	public String getFieldByID(int i){
		return type.keySet().toArray(new String[0])[i];
	}

	public Type getType(String fname) {
		Type t = type.get(fname);
		if (t == null)
			throw new TypeException("Undeclared field " + fname);
		return t;
	}

	public Collection<Type> types() {
		return type.values();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("{ ");
		String[] keySet = type.keySet().toArray(new String[0]);
		int i = 0;
		for (; i < keySet.length-1; i++) {
			res.append(type.get(keySet[i]).toString());
			res.append(", ");
		}
		res.append(type.get(keySet[i]).toString());
		res.append(" }");
		return res.toString();
	}
}
