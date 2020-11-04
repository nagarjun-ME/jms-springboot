package com.ibmmq.jms.spring.jmsspringboot;

import java.nio.charset.StandardCharsets;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mq.jms.MQQueue;
import com.ibmmq.jms.spring.jmsspringboot.model.Item;


@RestController
@RequestMapping("/item")
public class JmsMqRestApi {

	 @Autowired
	 private JmsTemplate jmsTemplate;

		private Logger log=LoggerFactory.getLogger(this.getClass()); 
	 
	 
	    @PostMapping
	    public ResponseEntity<String> createOrder(@RequestBody Item item) throws JMSException {
	    	
	        log.info("### 1 ### Order Service sending order message '{}' to the queue", item.getItemName());

	        MQQueue orderRequestQueue = new MQQueue("REQ.INQ");

	        jmsTemplate.convertAndSend(orderRequestQueue, item.getItemName(), textMessage -> {
	            textMessage.setJMSCorrelationID(item.getItemsId());
	            return textMessage;
	        });

	        return new ResponseEntity(item, HttpStatus.ACCEPTED);
	    }
	    
	    
	    
		/*
		 * @Deprecated // this was just to show how to find a message by correlation Id
		 * 
		 * @GetMapping public ResponseEntity<Item>
		 * findOrderByCorrelationId(@RequestParam String correlationId) throws
		 * JMSException { log.info("Looking for message '{}'", correlationId); String
		 * convertedId = bytesToHex(correlationId.getBytes()); final String
		 * selectorExpression = String.format("JMSCorrelationID='ID:%s'", convertedId);
		 * final TextMessage responseMessage = (TextMessage)
		 * jmsTemplate.receiveSelected("REQ.INQ", selectorExpression); Item response =
		 * Item.builder.message(responseMessage.getText()) .identifier(correlationId)
		 * .build(); return new ResponseEntity(response, HttpStatus.OK); }
		 * 
		 * // You could use Apache Commons Codec library instead private static final
		 * byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(); public static String
		 * bytesToHex(byte[] bytes) { byte[] hexChars = new byte[bytes.length * 2]; for
		 * (int j = 0; j < bytes.length; j++) { int v = bytes[j] & 0xFF; hexChars[j * 2]
		 * = HEX_ARRAY[v >>> 4]; hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F]; } return new
		 * String(hexChars, StandardCharsets.UTF_8); }
		 */
}
