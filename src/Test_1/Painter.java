package Test_1;

public class Painter {
	
	//内部类
	protected class InnerShape implements Shape{

		public InnerShape() {
		}

		@Override
		public void paint() {
			System.out.println("painter paint() method");
		}
	}

	public static void main(String[] args) {
		Painter painter = new Painter();  
	    Shape shape = painter.new InnerShape();  
	    shape.paint();
	}

}