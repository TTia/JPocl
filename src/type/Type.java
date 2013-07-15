package type;
public interface Type {
    boolean equalsTo(Type t);
    String getLoader();
    String getStorer();
    String getObjectType(String packageDst);
    String getId();
}
