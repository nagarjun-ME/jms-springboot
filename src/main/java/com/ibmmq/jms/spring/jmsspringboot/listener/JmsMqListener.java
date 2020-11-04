package com.ibmmq.jms.spring.jmsspringboot.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;




@Component
public class JmsMqListener {

	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	
	@JmsListener(destination = "REQ.OUTQ")
    public void receive(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("### 4 ### Order Service received message response : {} with correlation id: {}",
                textMessage.getText(), textMessage.getJMSCorrelationID());

        // do some business logic here, like updating the order in the database
    }
}
