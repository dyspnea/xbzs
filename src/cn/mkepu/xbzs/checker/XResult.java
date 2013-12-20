package cn.mkepu.xbzs.checker;

public class XResult {

	/**
	 * 检测出来的关键词
	 */
	protected String key_word = null;

	/**
	 * 检测类型
	 */
	protected String rule_type = null;

	/**
	 * 规则名
	 */
	protected String rule_name = null;

	/**
	 * 检测建议
	 */
	protected String suggestion = null;

	/**
	 * 起始位置
	 */
	protected int star_pos = -1;

	/**
	 * 截止位置
	 */
	protected int end_pos = -1;

	public XResult(String keyword, String type, String rulename,
			String suggestion, int startpos, int endpos) {
		this.key_word = keyword;
		this.rule_type=type;
		this.rule_name=rulename;
		this.suggestion=suggestion;
		this.star_pos=startpos;
		this.end_pos=endpos;
	}
}
