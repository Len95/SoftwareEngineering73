package Parser;

public class Expression {
	Type type;
	Expression leftChild;
	Expression rightChild;
	
	public Expression(Type t) {
		type = t;
	}
	
	public boolean isValid() {
		if(type == Type.BOOLEAN_OP) {
			if(leftChild != null && rightChild != null && leftChild.isValid() && rightChild.isValid()) {
				return true;
			} else {
				return false;
			}
		} else {
			if(leftChild == null && rightChild == null) {
				return true;
			} else {
				return false;
			}
		}
	}
}
