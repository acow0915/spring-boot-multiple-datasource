package com.winterframework.firefrog;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class DataPartitioner implements Partitioner {
	
	private static final Logger logger = LoggerFactory.getLogger(DataPartitioner.class);

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
		Long fromId = 1L;
		Long range = 10L; //以一千筆資料進行分割
		Long toId = fromId + range;
		
		for (int i = 1 ; i <= gridSize ; i++) {
			ExecutionContext value = new ExecutionContext();

			logger.info("\nStarting : Thread" + i);
			logger.info("fromId : " + fromId);
			logger.info("toId : " + toId);

			value.putLong("fromId", fromId);
			value.putLong("toId", toId);

			// give each thread a name, thread 1,2,3
			value.putString("name", "Thread" + i);

			result.put("partition" + i, value);

			fromId = toId + 1;
			toId = fromId + range;
		}
		return result;
	}

}
