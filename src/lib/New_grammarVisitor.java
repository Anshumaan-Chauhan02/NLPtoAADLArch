// Generated from New_grammar.g4 by ANTLR 4.9.2

    package lib;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link New_grammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface New_grammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#nlparch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNlparch(New_grammarParser.NlparchContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#sentences}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentences(New_grammarParser.SentencesContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(New_grammarParser.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#structural_stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructural_stmts(New_grammarParser.Structural_stmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#structural_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructural_stmt(New_grammarParser.Structural_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#connection_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnection_stmt(New_grammarParser.Connection_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#functional_stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctional_stmts(New_grammarParser.Functional_stmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#functional_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctional_stmt(New_grammarParser.Functional_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#struct_multinoun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct_multinoun(New_grammarParser.Struct_multinounContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#multi_flow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_flow(New_grammarParser.Multi_flowContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#flow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow(New_grammarParser.FlowContext ctx);
	/**
	 * Visit a parse tree produced by {@link New_grammarParser#states}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStates(New_grammarParser.StatesContext ctx);
}