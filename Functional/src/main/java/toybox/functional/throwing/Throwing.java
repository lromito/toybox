package toybox.functional.throwing;

import java.util.function.IntConsumer;

import  toybox.functional.throwing.bifunctions.BiFunctionChainer;
import  toybox.functional.throwing.bifunctions.IntBinaryOperatorChainer;
import  toybox.functional.throwing.bifunctions.ThrowingBiFunction;
import  toybox.functional.throwing.bifunctions.ThrowingIntBinaryOperator;
import  toybox.functional.throwing.consumers.BiConsumerChainer;
import  toybox.functional.throwing.consumers.ConsumerChainer;
import  toybox.functional.throwing.consumers.IntConsumerChainer;
import  toybox.functional.throwing.consumers.ThrowingBiConsumer;
import  toybox.functional.throwing.consumers.ThrowingConsumer;
import  toybox.functional.throwing.consumers.ThrowingIntConsumer;
import  toybox.functional.throwing.functions.FunctionChainer;
import  toybox.functional.throwing.functions.ThrowingFunction;
import  toybox.functional.throwing.functions.ThrowingUnaryOperator;
import  toybox.functional.throwing.functions.UnaryOperatorChainer;
import  toybox.functional.throwing.predicates.IntPredicateChainer;
import  toybox.functional.throwing.predicates.PredicateChainer;
import  toybox.functional.throwing.predicates.ThrowingIntPredicate;
import  toybox.functional.throwing.predicates.ThrowingPredicate;
import  toybox.functional.throwing.runnables.RunnableChainer;
import  toybox.functional.throwing.runnables.ThrowingRunnable;
import  toybox.functional.throwing.suppliers.BooleanSupplierChainer;
import  toybox.functional.throwing.suppliers.IntSupplierChainer;
import  toybox.functional.throwing.suppliers.SupplierChainer;
import  toybox.functional.throwing.suppliers.ThrowingBooleanSupplier;
import  toybox.functional.throwing.suppliers.ThrowingIntSupplier;

public final class Throwing {

	public static BooleanSupplierChainer throwingBooleanSupplier(ThrowingBooleanSupplier func) {
		return new BooleanSupplierChainer(func);
	}
	
	public static <T> SupplierChainer<T> throwingBooleanSupplier(SupplierChainer<T> func) {
		return new SupplierChainer<>(func);
	}
	
	public static <T, R> FunctionChainer<T, R> throwingFunction(ThrowingFunction<T, R> func) {
		return new FunctionChainer<>(func);
	}
	
	public static <T> UnaryOperatorChainer<T> throwingUnaryOperator(ThrowingUnaryOperator<T> func) {
		return new UnaryOperatorChainer<>(func);
	}
	
	public static <T, U, R> BiFunctionChainer<T, U, R> throwingBiFunction(ThrowingBiFunction<T, U, R> func) {
		return new BiFunctionChainer<>(func);
	}
	
	public static RunnableChainer throwingRunnable(ThrowingRunnable func) {
		return new RunnableChainer(func);
	}
	
	public static <T> PredicateChainer<T> throwingPredicate(ThrowingPredicate<T> func) {
		return new PredicateChainer<>(func);
	}
	
	public static <T, U> BiConsumerChainer<T, U> throwingBiConsumer(ThrowingBiConsumer<T, U> func) {
		return new BiConsumerChainer<>(func);
	}
	
	public static <T> ConsumerChainer<T> throwingConsumer(ThrowingConsumer<T> func) {
		return new ConsumerChainer<>(func);
	}
	
	public static IntSupplierChainer throwingIntSupplier(ThrowingIntSupplier func) {
		return new IntSupplierChainer(func);
	}
	
	public static IntConsumer throwingIntConsumer(ThrowingIntConsumer func) {
		return new IntConsumerChainer(func);
	}
	
	public static IntPredicateChainer throwingIntPredicate(ThrowingIntPredicate func) {
		return new IntPredicateChainer(func);
	}
	
	public static IntBinaryOperatorChainer throwingIntBinaryOperator(ThrowingIntBinaryOperator func) {
		return new IntBinaryOperatorChainer(func);
	}
	
	private Throwing() {
		//This class is not meant to be instantiated
	}
}
