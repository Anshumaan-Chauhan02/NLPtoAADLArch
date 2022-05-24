// Generated from .\New_grammar.g4 by ANTLR 4.9.2

    package lib;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link New_grammarParser}.
 */
public interface New_grammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#nlparch}.
	 * @param ctx the parse tree
	 */
	void enterNlparch(New_grammarParser.NlparchContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#nlparch}.
	 * @param ctx the parse tree
	 */
	void exitNlparch(New_grammarParser.NlparchContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#sentences}.
	 * @param ctx the parse tree
	 */
	void enterSentences(New_grammarParser.SentencesContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#sentences}.
	 * @param ctx the parse tree
	 */
	void exitSentences(New_grammarParser.SentencesContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(New_grammarParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(New_grammarParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#structural_stmts}.
	 * @param ctx the parse tree
	 */
	void enterStructural_stmts(New_grammarParser.Structural_stmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#structural_stmts}.
	 * @param ctx the parse tree
	 */
	void exitStructural_stmts(New_grammarParser.Structural_stmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#structural_stmt}.
	 * @param ctx the parse tree
	 */
	void enterStructural_stmt(New_grammarParser.Structural_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#structural_stmt}.
	 * @param ctx the parse tree
	 */
	void exitStructural_stmt(New_grammarParser.Structural_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#connection_stmt}.
	 * @param ctx the parse tree
	 */
	void enterConnection_stmt(New_grammarParser.Connection_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#connection_stmt}.
	 * @param ctx the parse tree
	 */
	void exitConnection_stmt(New_grammarParser.Connection_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#functional_stmts}.
	 * @param ctx the parse tree
	 */
	void enterFunctional_stmts(New_grammarParser.Functional_stmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#functional_stmts}.
	 * @param ctx the parse tree
	 */
	void exitFunctional_stmts(New_grammarParser.Functional_stmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#functional_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFunctional_stmt(New_grammarParser.Functional_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#functional_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFunctional_stmt(New_grammarParser.Functional_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#struct_multinoun}.
	 * @param ctx the parse tree
	 */
	void enterStruct_multinoun(New_grammarParser.Struct_multinounContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#struct_multinoun}.
	 * @param ctx the parse tree
	 */
	void exitStruct_multinoun(New_grammarParser.Struct_multinounContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#multi_flow}.
	 * @param ctx the parse tree
	 */
	void enterMulti_flow(New_grammarParser.Multi_flowContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#multi_flow}.
	 * @param ctx the parse tree
	 */
	void exitMulti_flow(New_grammarParser.Multi_flowContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#flow}.
	 * @param ctx the parse tree
	 */
	void enterFlow(New_grammarParser.FlowContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#flow}.
	 * @param ctx the parse tree
	 */
	void exitFlow(New_grammarParser.FlowContext ctx);
	/**
	 * Enter a parse tree produced by {@link New_grammarParser#states}.
	 * @param ctx the parse tree
	 */
	void enterStates(New_grammarParser.StatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link New_grammarParser#states}.
	 * @param ctx the parse tree
	 */
	void exitStates(New_grammarParser.StatesContext ctx);
}