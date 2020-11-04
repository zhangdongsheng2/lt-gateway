package org.thingsboard.gateway.service.gateway;

import org.thingsboard.gateway.service.MqttDeliveryFuture;
import org.thingsboard.gateway.service.conf.TbExtensionConfiguration;
import org.thingsboard.gateway.service.data.*;
import org.thingsboard.server.common.data.kv.KvEntry;
import org.thingsboard.server.common.data.kv.TsKvEntry;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by ashvayka on 16.01.17.
 */
public interface GatewayService {

    void init() throws Exception;

    void destroy() throws Exception;

    String getTenantLabel();

    /**
     * Inform gateway service that device is connected
     *
     * @param deviceName
     * @param deviceType
     */
    MqttDeliveryFuture onDeviceConnect(String deviceName, String deviceType);

    /**
     * Inform gateway service that device is disconnected
     *
     * @param deviceName
     */
    Optional<MqttDeliveryFuture> onDeviceDisconnect(String deviceName);

    /**
     * Report device attributes change to Thingsboard
     *
     * @param deviceName - the device name
     * @param attributes - the attribute values list
     */
    MqttDeliveryFuture onDeviceAttributesUpdate(String deviceName, List<KvEntry> attributes);

    /**
     * Report device telemetry to Thingsboard
     *
     * @param deviceName - the device name
     * @param telemetry  - the telemetry values list
     */
    MqttDeliveryFuture onDeviceTelemetry(String deviceName, List<TsKvEntry> telemetry);

    /**
     * Report attributes request to Thingsboard
     *
     * @param attributeRequest - attributes request
     * @param listener         - attributes response
     */
    void onDeviceAttributeRequest(AttributeRequest attributeRequest, Consumer<AttributeResponse> listener);

    /**
     * Report response from device to the server-side RPC call from Thingsboard
     *
     * @param response - the device response to RPC call
     */
    void onDeviceRpcResponse(RpcCommandResponse response);

    /**
     * Subscribe to attribute updates from Thingsboard
     *
     * @param subscription - the subscription
     * @return true if successful, false if already subscribed
     */
    boolean subscribe(AttributesUpdateSubscription subscription);

    /**
     * Subscribe to server-side rpc commands from Thingsboard
     *
     * @param subscription - the subscription
     * @return true if successful, false if already subscribed
     */
    boolean subscribe(RpcCommandSubscription subscription);

    /**
     * Unsubscribe to attribute updates from Thingsboard
     *
     * @param subscription - the subscription
     * @return true if successful, false if already unsubscribed
     */
    boolean unsubscribe(AttributesUpdateSubscription subscription);

    /**
     * Unsubscribe to server-side rpc commands from Thingsboard
     *
     * @param subscription - the subscription
     * @return true if successful, false if already unsubscribed
     */
    boolean unsubscribe(RpcCommandSubscription subscription);

    /**
     * Report generic error from one of gateway components
     *
     * @param e - the error
     */
    void onError(Exception e);

    /**
     * Report error related to device
     *
     * @param deviceName - the device name
     * @param e          - the error
     */
    void onError(String deviceName, Exception e);

    /**
     * Report applied configuration
     *
     * @param configuration - extension configuration
     */
    void onAppliedConfiguration(String configuration);

    /**
     * Report extension configuration error
     *
     * @param e             - the error
     * @param configuration - extension configuration
     */
    void onConfigurationError(Exception e, TbExtensionConfiguration configuration);

    /**
     * Report extension configuration status
     *
     * @param id     - extension id
     * @param status - extension status
     */
    void onConfigurationStatus(String id, String status);
}
