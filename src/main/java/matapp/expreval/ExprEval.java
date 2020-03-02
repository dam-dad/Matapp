package matapp.expreval;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;

public class ExprEval {
	/**
	 * 
	 * @author Francisco Vargas, Kilian González
	 * 
	 *Calcula la expresión matemtica que se le pase
	 *
	 */

	private static Context context; 
	
	static {
		context = Context.enter();
		context.setLanguageVersion(Context.VERSION_ES6);
	}
	
	public static Object eval(String expr, Map<String, Object> params) {
		ScriptableObject scope = context.initStandardObjects();
		for (String key : params.keySet()) {
			scope.put(key, scope, params.get(key));
		}
		Script script = context.compileString(expr, null, 0, null);
		return script.exec(context, scope);
	}
	
	public static Object eval(String expr, Param<?> ... params) {
		Map<String,Object> map = 
				Arrays
					.asList(params)
					.stream()
					.collect(
							Collectors.toMap(Param::getName, Param::getValue)
					);
		return eval(expr, map);
	}
	
	public static <T> Param<T> param(String name, T value) {
		return new Param<T>(name, value);
	}

}