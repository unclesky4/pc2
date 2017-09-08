package edu.csus.ecs.pc2.api;

import Test_1.Painter;

public class Test_Painter extends Painter {
	
	public static void main(String srgs[]) {
		//Test_Painter test_Painter = new Test_Painter();
		InnerShape innerShape = new Test_Painter().new InnerShape();
		innerShape.paint();
	}
}
