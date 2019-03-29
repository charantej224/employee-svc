package com.employee.service.service;

import com.employee.service.domain.EventMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class EventMessageServiceTest {

    @Mock
    private RabbitTemplate mockRabbitTemplate;

    @InjectMocks
    private EventMessageService eventMessageServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testSendMessage_exceutionOfRabbitTemplate() {
        final EventMessage eventMessage = new EventMessage("testEmail", "CREATE");
        eventMessageServiceUnderTest.sendMessage(eventMessage);
        Mockito.verify(mockRabbitTemplate, Mockito.times(1)).convertAndSend(Mockito.isNull(), any(EventMessage.class));
    }

    @Test(expected = RuntimeException.class)
    public void testSendMessage_throwsRuntimeException() {
        final EventMessage eventMessage = new EventMessage("testEmail", "CREATE");
        doThrow(new RuntimeException()).when(mockRabbitTemplate).convertAndSend(Mockito.isNull(), any(EventMessage.class));
        eventMessageServiceUnderTest.sendMessage(eventMessage);
    }
}
