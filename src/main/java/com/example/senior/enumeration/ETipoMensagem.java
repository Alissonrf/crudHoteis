package com.example.senior.enumeration;

public enum ETipoMensagem {
	INFO, WARNING, ERROR, APPROVE;

	public static boolean isError(ETipoMensagem tipo) {
		return ERROR.equals(tipo);
	}
}
