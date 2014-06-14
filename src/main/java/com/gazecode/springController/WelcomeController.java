package com.gazecode.springController;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 * Welcome Controller
 * Handles requests for the application Welcome page.
 */
@Controller
public class WelcomeController 
{
	/**
	 * Simply selects the Welcome view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Locale locale, Model model) 
	{	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "welcome";
	}	

	@RequestMapping(value = "/evaluate", method = RequestMethod.GET)
	public @ResponseBody String EvaluateExpression(
			@RequestParam(value="expression", required=false, defaultValue="1") String expression
			){
		String ans = "Failed to calculate";
		
		Calculable calc;
		try {
			//calc = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)").build();
			calc = new ExpressionBuilder(expression).build();
			Double result1=calc.calculate();
			ans = result1.toString();
		} catch (UnknownFunctionException e) {
			e.printStackTrace();
		} catch (UnparsableExpressionException e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	@RequestMapping(value = "/evaluate/{expression}", method = RequestMethod.GET)
	public @ResponseBody String EvaluateExpression2(
			@PathVariable String expression
			){
		String ans = "Failed to calculate";
		
		System.out.println(expression);
		Calculable calc;
		try {
			//calc = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)").build();
			calc = new ExpressionBuilder(expression).build();
			Double result1=calc.calculate();
			ans = result1.toString();
		} catch (UnknownFunctionException e) {
			e.printStackTrace();
		} catch (UnparsableExpressionException e) {
			e.printStackTrace();
		}
		
		return ans;
	}
	
	@RequestMapping(value = "ajax/evaluate")
	public @ResponseBody String AjaxEvaluateExpression(
			@RequestParam(value="expression", required=false, defaultValue="1") String expression
			){
		String ans = "Failed to calculate";
		
		System.out.println(expression);
		Calculable calc;
		try {
			calc = new ExpressionBuilder(expression).build();
			Double result1=calc.calculate();
			ans = result1.toString();
		} catch (UnknownFunctionException e) {
			e.printStackTrace();
		} catch (UnparsableExpressionException e) {
			e.printStackTrace();
		}
		
		return ans;
	}
}