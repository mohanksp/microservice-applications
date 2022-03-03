package com.devtechnical.productservice.controller.command.interceptor;

import com.devtechnical.productservice.controller.command.CreateProductCommand;
import com.devtechnical.productservice.core.model.ProductLookup;
import com.devtechnical.productservice.core.service.ProductLookupService;
import java.util.List;
import java.util.function.BiFunction;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author Mohanksp
 */
@Component
public class CreateProductCommandInterceptor implements
    MessageDispatchInterceptor<CommandMessage<?>> {

  @Autowired
  private ProductLookupService productLookupService;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
      List<? extends CommandMessage<?>> list) {
    return (index, command) -> {
      logger.info("Interceptor command : " + command.getPayloadType());
      if (CreateProductCommand.class.equals(command.getPayloadType())) {
        CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

        /*if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
          throw new IllegalArgumentException("price can't be less or equal then zero");
        }
        if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isEmpty()) {
          throw new IllegalArgumentException("title can't be empty");
        }*/

        ProductLookup productLookup = productLookupService
            .findByProductIdOrTitle(createProductCommand.getProductId(),
                createProductCommand.getTitle());
        if (productLookup != null) {
          throw new IllegalStateException(
              String.format("Product with product id %s or title %s already exist",
                  createProductCommand.getProductId(), createProductCommand.getTitle())
          );
        }
      }
      return command;
    };
  }
}
