package type;



public enum PrimType implements Type {
	BOOL {
		@Override
		public String getId() {
			return "bool";
		}
		
		@Override
		public String toString() {
			return "Z";
		}

		@Override
		public String getLoader() {
			return "iload";
		}

		@Override
		public String getStorer() {
			return "istore";
		}
		
	},
	INT {
		@Override
		public String toString() {
			return "I";
		}

		@Override
		public String getLoader() {
			return "iload";
		}
		
		@Override
		public String getStorer() {
			return "istore";
		}

		@Override
		public String getId() {
			return "int";
		}
	},
	VOID {
		@Override
		public String toString() {
			return "V";
		}
		@Override
		public String getLoader() {
			throw new Error("Debug: load a VOID value?!");
		}
		
		@Override
		public String getId() {
			return "void";
		}
		
		@Override
		public String getStorer() {
			throw new Error("Debug: store a value in a VOID variable?!");
		}
	};
	
	@Override
	public boolean equalsTo(Type t) {
		return this == t;
	}
	
	@Override
	public String getObjectType(String packageDst) {
		return toString();
	}

}
