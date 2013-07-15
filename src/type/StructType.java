package type;

import java.util.Collection;

public final class StructType implements Type {

	final private String id;
	public final StructTypeDec dec;

	public StructType(String id, StructTypeDec dec) {
		this.id = id;
		this.dec = dec;
	}

	public Type getType(String fname) {
		return dec.getType(fname);
	}

	public Collection<Type> types() {
		return dec.types();
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equalsTo(Type t) {
		return t instanceof StructType && dec == ((StructType) t).dec;
	}

	@Override
	public String toString() {
		return String.format("jpocl/%s", id);
	}

	@Override
	public String getLoader() {
		return "aload";
	}

	@Override
	public String getStorer() {
		return "astore";
	}

	public String buildConstructor(String visibility, String packageDst) {
		String constructor = "";
		String signature = getFieldsSignature(packageDst);
		constructor += String.format(".method %s <init>(%s)V\n", visibility,
				signature);
		constructor += "  .limit stack 2\n";
		constructor += String
				.format("  .limit locals %s\n", types().size() + 1);
		constructor += "aload 0\n";
		constructor += "invokespecial java/lang/Object.<init>()V\n";
		int i = 0;
		for (Type fieldType : types()) {
			constructor += "aload 0\n";
			constructor += String.format("%s %s\n", fieldType.getLoader(),
					i + 1);
			constructor += String.format("putfield %s/%s.%s %s\n", packageDst,
					id, dec.getFieldByID(i),
					fieldType.getObjectType(packageDst));
			i++;
		}
		constructor += "return\n";
		constructor += ".end method\n";
		return constructor;
	}

	public String buildToString(String packageDst) {
		String toString = "";
		toString += ".method public toString()Ljava/lang/String;\n";
		toString += "  .limit stack 3\n";
		toString += "  .limit locals 1\n";
		toString += "new java/lang/StringBuilder\n";
		toString += "dup\n";
		toString += String.format("ldc \"%s[\"\n", id);
		toString += "invokespecial java/lang/StringBuilder.<init>(Ljava/lang/String;)V\n";

		int i = 0;
		for (Type fieldType : types()) {
			toString += "aload 0\n";
			toString += String.format("getfield %s/%s.%s %s\n", packageDst, id,
					dec.getFieldByID(i), fieldType.getObjectType(packageDst));
			if (dec.getType(dec.getFieldByID(i)) instanceof StructType) {
				toString += "invokevirtual java/lang/Object.toString()Ljava/lang/String;\n";
			} else if (dec.getType(dec.getFieldByID(i)).equalsTo(PrimType.INT)) {
				toString += String
						.format("invokestatic java/lang/Integer.toString(%s)Ljava/lang/String;\n",
								fieldType);
			} else {
				toString += String
						.format("invokestatic java/lang/Boolean.toString(%s)Ljava/lang/String;\n",
								fieldType);
			}
			toString += "invokevirtual java/lang/StringBuilder.append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n";

			if (i != types().size() - 1) {
				toString += "ldc \",\"\n";
				toString += "invokevirtual java/lang/StringBuilder.append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n";
			}
			i++;
		}

		toString += "ldc \"]\"\n";
		toString += "invokevirtual java/lang/StringBuilder.append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n";
		toString += "invokevirtual java/lang/StringBuilder.toString()Ljava/lang/String;\n";
		toString += "areturn\n";
		toString += ".end method\n";

		return toString;
	}

	public String buildEquals(String packageDst) {
		String equals = "";
		equals += ".method public equals(Ljava/lang/Object;)Z\n";
		equals += "  .limit stack 2\n";
		equals += "  .limit locals 3\n";
		equals += "aload 1\n";
		equals += String.format("instanceof %s/%s\n", packageDst, id);
		equals += "ifne L1\n";
		equals += "L2:\n";
		equals += "iconst_0\n";
		equals += "ireturn\n";
		equals += "L1:\n";

		equals += "aload 1\n";
		equals += String.format("checkcast %s/%s\n", packageDst, id);
		equals += "astore 2\n";

		int i = 0;
		for (Type fieldType : types()) {
			equals += "aload 2\n";
			equals += String.format("getfield %s/%s.%s %s\n", packageDst, id,
					dec.getFieldByID(i), fieldType.getObjectType(packageDst));
			equals += "aload 0\n";
			equals += String.format("getfield %s/%s.%s %s\n", packageDst, id,
					dec.getFieldByID(i), fieldType.getObjectType(packageDst));

			if (fieldType instanceof StructType) {
				equals += "invokevirtual java/lang/Object.equals(Ljava/lang/Object;)Z\n";
				equals += "ifeq L2\n";
			} else {
				equals += "if_icmpne L2\n";
			}
			equals += "\n";
			i++;
		}

		equals += "iconst_1\n";
		equals += "ireturn\n";
		equals += ".end method\n";
		return equals;
	}

	@Override
	public String getObjectType(String packageDst) {
		return "L" + packageDst + "/" + id + ";";
	}

	public String getFieldsSignature(String packageDst) {
		String fieldsSignature = "";
		for (Type type : types()) {
			fieldsSignature += type.getObjectType(packageDst);
		}
		return fieldsSignature;
	}

}
