package com.desempenho.aulas.detranrj.api.util;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static <T> CompletableFuture<ResponseEntity<? extends Object>> handleAsyncRequest(
            Supplier<CompletableFuture<T>> serviceMethod) {
        return serviceMethod.get()
                .thenApply(result -> {
                    if (result == null || (result instanceof Collection && ((Collection<?>) result).isEmpty())) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                    }
                    return ResponseEntity.ok(result);
                })
                .exceptionally(ex -> {
                    System.err.println("Erro ao processar a requisição: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Erro ao processar a requisição");
                });
    }

    public static <T, R> CompletableFuture<R> handleAsyncRequestService(Supplier<CompletableFuture<T>> requestMethod,
            Function<T, R> responseConverter) {
        return requestMethod.get()
                .thenApply(response -> {
                    try {
                        return responseConverter.apply(response);
                    } catch (Exception e) {
                        System.err.println("Erro ao converter a resposta: " + e.getMessage());
                        return null;
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("Erro ao realizar a requisição assíncrona: " + ex.getMessage());
                    return null;
                });
    }
}
