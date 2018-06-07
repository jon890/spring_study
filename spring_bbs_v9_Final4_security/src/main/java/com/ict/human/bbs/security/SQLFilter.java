package com.ict.human.bbs.security;

import java.util.regex.Pattern;

public class SQLFilter {

	public static String sqlFiltering(String inputString) {
		
		// inputString에 있는 특수문자 제거
		Pattern pattern = Pattern.compile("['\"-#()@;=*/+]");
		inputString = pattern.matcher(inputString).replaceAll("");
		
		// inputString에 있는 sql구문 제거
		inputString = inputString.toLowerCase();
		inputString = inputString.replaceAll("union", "q-union")
								 .replaceAll("select", "q-select")
								 .replaceAll("insert", "q-insert")
								 .replaceAll("delete", "q-delete").replaceAll("update", "q-update")
								 .replaceAll("and", "q-and").replaceAll("or", "q-or")
								 .replaceAll("join", "q-join")
								 .replaceAll("substr", "q-substr")
								 .replaceAll("where", "q-where").replaceAll("from", "q-from")
								 .replaceAll("declare", "q-declare")
								 .replaceAll("openrowset", "q-openrowset")
								 .replaceAll("user_tables", "q-user_tables")
								 .replaceAll("user_tab_columns", "q-user_tab_columns")
								 .replaceAll("column_name", "q-column_name")
								 .replaceAll("row_num", "q-row_num");	
		
		return inputString;
	}
	
}