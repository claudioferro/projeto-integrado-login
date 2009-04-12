package com.pucpr.br.command;

import java.util.Map;

public interface Command {

	public void execute(Map<String, Object> data);

}
