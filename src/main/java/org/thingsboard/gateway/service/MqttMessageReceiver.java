package org.thingsboard.gateway.service;

import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * Created by Valerii Sosliuk on 3/10/2018.
 */
@Slf4j
public class MqttMessageReceiver implements Runnable {

    private static final int INCOMING_QUEUE_DEFAULT_WARNING_THRESHOLD = 1000;

    private PersistentFileService persistentFileService;
    private BlockingQueue<MessageFuturePair> incomingQueue;

    private Consumer<Void> defaultSuccessCallback = message -> log.debug("Successfully sent message: [{}]", message);
    private Consumer<Throwable> defaultFailureCallback = e -> log.warn("Failed to send message: [{}]", e);

    private int incomingQueueWarningThreshold;

    public MqttMessageReceiver(PersistentFileService persistentFileService,
                               BlockingQueue<MessageFuturePair> incomingQueue,
                               int warningThreshold) {
        this.persistentFileService = persistentFileService;
        this.incomingQueue = incomingQueue;
        this.incomingQueueWarningThreshold = warningThreshold == 0 ? INCOMING_QUEUE_DEFAULT_WARNING_THRESHOLD : warningThreshold;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            checkIncomingQueueSize();
            try {
                MessageFuturePair messageFuturePair = incomingQueue.take();
                Future<?> future = messageFuturePair.getFuture();
                MqttPersistentMessage message = messageFuturePair.getMessage();
                if (future.isSuccess()) {
                    Consumer<Void> successCallback = persistentFileService.getSuccessCallback(message.getId()).orElse(defaultSuccessCallback);
                    successCallback.accept(null);
                    persistentFileService.resolveFutureSuccess(message.getId());
                } else {
                    persistentFileService.saveForResend(message);
                    persistentFileService.getFailureCallback(message.getId()).orElse(defaultFailureCallback).accept(future.cause());
                    persistentFileService.resolveFutureFailed(message.getId(), future.cause());
                    log.warn("Failed to send message [{}] due to [{}]", message, future.cause());
                }
            } catch (InterruptedException e) {
                log.info(e.getMessage());
                Thread.currentThread().interrupt();
                break;
            } catch (Throwable e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void checkIncomingQueueSize() {
        if (incomingQueue.size() > incomingQueueWarningThreshold) {
            log.warn("Incoming queue has [{}] messages which is more than thee specified threshold of [{}]", incomingQueue.size(), incomingQueueWarningThreshold);
        }
    }
}
