package cz.upce.boop.ex.logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemoryLoggerWithHandlersTest {

    @Test
    public void test1() {
        LocalDateTime date = LocalDateTime.of(2026, 3, 18, 22, 17);
        DateTimeProvider provider = mock(DateTimeProvider.class);
        when(provider.getDateTime()).thenReturn(date);
        LogMessageHandler handler = mock(LogMessageHandler.class);
        MemoryLoggerWithHandlers logger = new MemoryLoggerWithHandlers(provider);
        logger.addLogMessageHandler(LogMessageSeverity.Debug, handler);
        logger.logMessage(LogMessageSeverity.Error, "message");
        LogMessage last = logger.getLastLogMessage();
        assertEquals(date, last.occurence());
        assertEquals(LogMessageSeverity.Error, last.severity());
        assertEquals("message", last.message());
        verify(handler).handle(logger.getLastLogMessage());
    }

    @Test
    public void test2() {
        LocalDateTime date1 = LocalDateTime.of(2026, Month.MARCH, 18, 23, 7);
        LocalDateTime date2 = LocalDateTime.of(2026, Month.MARCH, 18, 23, 8);
        DateTimeProvider provider = mock(DateTimeProvider.class);
        when(provider.getDateTime()).thenReturn(date1, date2);
        MemoryLoggerWithHandlers logger = new MemoryLoggerWithHandlers(provider);
        logger.logMessage(LogMessageSeverity.Error, "message1");
        logger.logMessage(LogMessageSeverity.Error, "message2");
        assertEquals(date1, logger.getLoggedMessages().get(0).occurence());
        assertEquals(date2, logger.getLoggedMessages().get(1).occurence());
        
    }
    @Test
    public void test3 (){
        LocalDateTime date = LocalDateTime.of(2026, Month.MARCH, 18, 23, 15);
        DateTimeProvider provider = mock(DateTimeProvider.class) ;
        when(provider.getDateTime()).thenReturn(date);
        
        LogMessageHandler handler1 = mock(LogMessageHandler.class);
        LogMessageHandler handler2 = mock(LogMessageHandler.class);
        
        MemoryLoggerWithHandlers logger = new MemoryLoggerWithHandlers(provider);
        logger.addLogMessageHandler(LogMessageSeverity.Warning, handler1);
        logger.addLogMessageHandler(LogMessageSeverity.Error, handler2);
        
        logger.logMessage(LogMessageSeverity.Warning, "message");
        
        verify(handler1).handle(any(LogMessage.class));
        verify(handler2, never()).handle(any(LogMessage.class));
        
    }
    @Test
    public void test4 (){
        LocalDateTime date = LocalDateTime.of(2026, 3, 18, 23, 33);
        DateTimeProvider provider = mock(DateTimeProvider.class);
        when(provider.getDateTime()).thenReturn(date);
        
        LogMessageHandler handler1 = mock(LogMessageHandler.class);
        LogMessageHandler handler2 = mock(LogMessageHandler.class);
        
        MemoryLoggerWithHandlers logger = new MemoryLoggerWithHandlers(provider);
        logger.addLogMessageHandler(LogMessageSeverity.Warning, handler1);
        logger.addLogMessageHandler(LogMessageSeverity.Error, handler2);
        
        logger.logMessage(LogMessageSeverity.Error, "message");
        
        verify(handler1).handle(any(LogMessage.class));
        verify(handler2).handle(any(LogMessage.class));

        
    }

}
