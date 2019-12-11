package com.abc.springsession.exception;
import lombok.*;

import java.util.function.Supplier;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceNotFoundError extends RuntimeException implements Supplier<RuntimeException> {
    private String message;

    @Override
    public RuntimeException get() {
        return ResourceNotFoundError.builder().message(Message.RESOURCE_NOT_FOUND.getValue()).build();
    }
}
