package matapp.expreval;
import java.util.HashMap;
import java.util.Map;
import matapp.expreval.Param;
import static matapp.expreval.ExprEval.*;
public class EjemploDeUso {
	//ejemplo de uso de expresiones evaluables 
	
		public static void main(String[] args) {
			sample1();
			sample2();
			sample3();
			sample4();
		}
		private static void sample1() {
			Map<String, Object> params = new HashMap<>();
			params.put("ancho", 15);
			params.put("alto", 6);
			Double result = (Double) eval("Math.sqrt(ancho / alto)", params);
			System.out.println(result);
		}

		private static void sample2() {
			Double result = (Double) eval("Math.sqrt(ancho / alto)", param("ancho", 15), param("alto", 6));
			System.out.println(result);
		}

		private static void sample3() {
			Param<?> [] params = { param("ancho", 15), param("alto", 6) };
			Double result = (Double) eval("Math.sqrt(ancho / alto)", params);
			System.out.println(result);
		}	
		private static void sample4() {
			Param<?> [] params = { param("masa", 15), param("gravedad", 9.8) };
			Double result = (Double) eval("masa * gravedad", params);
			System.out.println(result);
		}
	}
