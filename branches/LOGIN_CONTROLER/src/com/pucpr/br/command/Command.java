package com.pucpr.br.command;

import java.util.Map;

public interface Command {

	public Map<String, Object> execute(Map<String, Object> data);

}
