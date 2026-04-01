package cz.upce.boop.ex;

import cz.upce.boop.ex.di.DIContainer;
import cz.upce.boop.ex.di.Scope;
import cz.upce.boop.ex.logger.ConsoleLogMessageHandler;
import cz.upce.boop.ex.logger.DateTimeProvider;
import cz.upce.boop.ex.logger.LocalNowDateTimeProvider;
import cz.upce.boop.ex.logger.LogMessageSeverity;
import cz.upce.boop.ex.logger.Logger;
import cz.upce.boop.ex.logger.MemoryLoggerWithHandlers;

public class Ex07 {

    public static void main(String[] args) {
        DIContainer container = new DIContainer();

        container.register(
                DateTimeProvider.class,
                LocalNowDateTimeProvider.class,
                Scope.SINGLETON
        );

        container.register(
                Logger.class,
                MemoryLoggerWithHandlers.class,
                c -> {
                    MemoryLoggerWithHandlers logger
                    = new MemoryLoggerWithHandlers(c.getInstance(DateTimeProvider.class));
                    logger.addLogMessageHandler(
                            LogMessageSeverity.Information,
                            new ConsoleLogMessageHandler()
                    );
                    return logger;
                },
                Scope.SINGLETON
        );

        Logger logger = container.getInstance(Logger.class);
        logger.logMessage(LogMessageSeverity.Information, "Ahoj z DI kontejneru.");
    }
}
