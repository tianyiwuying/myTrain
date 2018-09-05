package myTrain;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;

public class CalcClass {
	
	public static void main(String[] args) {
		String sum="";
		sum=sum+".0";//double类型
		String exmple="Ap*A+Bp*B+Cp*C+Dp*D+Ep*Avg";
		String exmple1="(az/sum)*A+(bz/sum)*B+(cz/sum)*C+(dz/sum)*D+(ez/sum)*Avg";
		String exmple2="(az/sum)*A+(bz/sum)*B+(cz/sum)*C+(dz/sum)*D+(ez/sum)*(Ap*A+Bp*B+Cp*C+Dp*D)/(1-Ep)";
		String exmple3="(az/sum)*A+(bz/sum)*B+(cz/sum)*C+(dz/sum)*D+(ez/sum)*(az/sum*A+bz/sum*B+cz/sum*C+dz/sum*D)/(1-ez/sum)";
		String expression="1/2.0*100+1/4.0*80+1/10.0*60+1/10.0*0+1/20.0*((1/2.0*100+1/4.0*80+1/10.0*60+1/10.0*0)/(1-1/20.0))";
		System.out.println(expression);
		JexlEngine objJex=new JexlBuilder().create();
		JexlExpression jexlExpression=objJex.createExpression(expression);
		Object value = jexlExpression.evaluate(null);
		System.out.println(value); 
	}  
  
}
